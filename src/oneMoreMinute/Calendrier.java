package oneMoreMinute;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TimeZone;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;

import org.apache.commons.codec.binary.Base64;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class Calendrier {
	
	//Le même id qu'utilisateur
	@Id String id;
	//url de l'emploi du temps en .ics
	private String url;
	//login utilise pour se connecter a l'edt, laisser null si pas besoin
	private String user;
	//mot de passe pour se connecter a l'edt, laisser null si pas besoin
	private String mdp;
	//date a laquelle le reveil sonnera pour la prochaine fois
	private String date_reveil;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getDate_reveil() {
		return date_reveil;
	}

	public void setDate_reveil(String date_reveil) {
		this.date_reveil = date_reveil;
	}
	
	public Calendrier(String user){
		this.url = null;
		this.user = null;
		this.date_reveil = null;
		this.mdp = null;
		this.id = user;
	}

	private Calendrier(){
		
	}
	
	//methode qui permet de recuperer l'edt et de le mettre dans un objet calendar
	public Calendar getCalendar(){

		Calendar cal = null;
		try {
			URL urlObj = new URL(this.url);
			URLConnection urlConnection = urlObj.openConnection();
			
			String userCredentials = this.user + ":" + this.mdp;
			String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
			urlConnection.setRequestProperty ("Authorization", basicAuth);
			
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			
			CalendarBuilder builder = new CalendarBuilder();
			cal =builder.build(in);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return cal;
	}
		
	//methode qui permet a partir d'un Calendar de trouver le premier evenement qui se deroulera au moins le jour suivant le jour courant
	public Component prochain_evenement(Calendar c, boolean auj) throws ParseException{
		
		Date date = new Date();
		Component evenement = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
		dateFormat.setTimeZone(TimeZone.getDefault());
		String date_aujourdhui = dateFormat.format(date);

		/* aujourdhui contient la date du jour courant, date_a_comparer contient une date qui est superieur a
		 * aujourdhui. Elle contiendra le premier evenement apres la date aujourdhui.
		 */
		Date aujourdhui, date_a_comparer = null;
		aujourdhui = dateFormat.parse(date_aujourdhui);
		if(!auj){
			aujourdhui.setDate(aujourdhui.getDate()-1);
		}
		for (Iterator i = c.getComponents().iterator(); i.hasNext();) {
				
			  Component component = (Component) i.next();
			  
			  if(component.getProperty("TRANSP") == null || !component.getProperty("TRANSP").getValue().equals("TRANSPARENT")){
				  
				  String a_comparer = component.getProperty("DTSTART").getValue();

				  if (a_comparer.compareTo(date_aujourdhui) > 0 && (evenement == null || a_comparer.compareTo(evenement.getProperty("DTSTART").getValue()) < 0)){

					  date_a_comparer = dateFormat.parse(component.getProperty("DTSTART").getValue());
					  if((aujourdhui.getMonth() == date_a_comparer.getMonth() && aujourdhui.getDate() < date_a_comparer.getDate()) || (aujourdhui.getMonth() < date_a_comparer.getMonth() && aujourdhui.getYear() == date_a_comparer.getYear()) || aujourdhui.getYear() < date_a_comparer.getYear()){
						  evenement = component;
					  }	
				  }
			 }
		}
			return evenement;
	}
	
	/*Methode qui permet en fonction du Calendar passe en parametre de trouver l'heure de debut du prochain evenement.
	 * Pour cela cette methode utilise la methode prochain_evenement.
	 */
	public String heure_de_debut(Calendar c, boolean auj) throws ParseException{
		
		if(prochain_evenement(c, auj) == null){
			return null;
		}
		else{	
			
			NumberFormat formater = new DecimalFormat("00");

			String date = prochain_evenement(c, auj).getProperty("DTSTART").getValue();
	
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+1:00"));
			Date prochaine_date = dateFormat.parse(date);
	
			java.util.Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(prochaine_date);
			date = calendar.get(calendar.YEAR)+"-"+formater.format(calendar.get(calendar.MONTH)+1)+"-"+formater.format(calendar.get(calendar.DATE));
			date += " "+formater.format(calendar.get(calendar.HOUR_OF_DAY))+":"+formater.format(calendar.get(calendar.MINUTE));
			return date;
		}
	}
	
	public void maj_date_reveil(boolean auj){
		if(this.url!=null){
			Calendar c = getCalendar();
			String heure = null;
			if(c!=null){
				try {
					heure = heure_de_debut(c, auj);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					heure = null;
				}
			}
		 	if(heure == null || !heure.equals(this.date_reveil)){
		 		this.date_reveil = heure;
		 	}
		}
	}
	
	/*
	 * Methode qui permet d'ajouter un emplois du temps ou de modifier les parametre qui y sont liees
	 */
	public String modifier_edt(String login, String mdp, String url){
		String message_edt = "";
		String heure = null;
		boolean modif = false;
		if(this.user == null || !login.equals(this.user)){
			this.user = login;
			modif = true;
		}
		
 	   if(this.mdp == null || !mdp.equals(this.mdp)){
 		   this.mdp = mdp;
 		   modif = true;
 	   }
 	   
 	   if(url == null || !url.equals(this.url) || modif){
 		   
 		   this.url = url;
 		   if(!url.endsWith("ics")){
 			   message_edt = "Cet emplois du temps n'est pas au format ics, veuillez en indiquer un autre";
 		   }
 		   else{
 			   try {
 				   Calendar c = getCalendar();
 				   if(c != null){
 					   heure = heure_de_debut(c, true);
 					   message_edt = "Ajout de l'emplois du temps OK";
 					   this.url = url;
 					   message_edt = "EDT ajouté avec success !";
 				   }
 				   else{
 					   message_edt = "Une erreur est survenue pendant l'ajout de votre edt, vérifiez l'url et vos identifiants";
 				   }
 			   }
 			   catch (ParseException e) {
 				   message_edt = "Une erreur est survenue pendant l'ajout de votre edt, vérifiez l'url et vos identifiants";
 			   }
 		   }
 		   
 		   if(heure == null || !heure.equals(this.date_reveil)){
 			   this.date_reveil = heure;
 		   }
 	   }
 	   return message_edt;
	}
}
