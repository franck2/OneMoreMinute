package oneMoreMinute;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
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
import com.sun.org.apache.bcel.internal.generic.NEW;


@Entity
public class Calendrier {
	@Id String id;
	private String url;
	private String user;
	private String mdp;
	//plutot le mettre en date
	private String heure_reveil;
	

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

	public String getHeure_reveil() {
		return heure_reveil;
	}

	public void setHeure_reveil(String heure_reveil) {
		this.heure_reveil = heure_reveil;
	}
	
	private Calendrier(){
		
	}
	
	public Calendrier(String user){
		this.url = null;
		this.user = null;
		this.heure_reveil = null;
		this.mdp = null;
		this.id=user;
	}


	public Calendar getCalendar(){

		Calendar cal=null;
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
		
	public Component prochain_evenement(Calendar c) throws ParseException{
		Date date = new Date();
		Component evenement = null;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
		dateFormat.setTimeZone(TimeZone.getDefault());
		String date_aujourdhui = dateFormat.format(date);
		Date aujourdhui, date_a_comparer = null;
		aujourdhui = dateFormat.parse(date_aujourdhui);

		for (Iterator i = c.getComponents().iterator(); i.hasNext();) {
				
			  Component component = (Component) i.next();
			  
			  if(component.getProperty("TRANSP") ==null || !component.getProperty("TRANSP").getValue().equals("TRANSPARENT")){
				  
				  String a_comparer = component.getProperty("DTSTART").getValue();
				  
				  if (a_comparer.compareTo(date_aujourdhui) > 0 && (evenement == null || a_comparer.compareTo(evenement.getProperty("DTSTART").getValue()) < 0)){

					  date_a_comparer=dateFormat.parse(component.getProperty("DTSTART").getValue());
					  
					  if((aujourdhui.getMonth() == date_a_comparer.getMonth() && aujourdhui.getDate()<date_a_comparer.getDate()) || (aujourdhui.getMonth()<date_a_comparer.getMonth() && aujourdhui.getYear() == date_a_comparer.getYear()) || aujourdhui.getYear() < date_a_comparer.getYear()){
						  evenement = component;
					  }	
				  }
			  }
		}

			return evenement;
	}
	
	public String heure_de_debut(Calendar c) throws ParseException{
		if(prochain_evenement(c)==null){
			System.out.println("rien demain");
		}
		else{
			System.out.println(prochain_evenement(c).getProperty("DTSTART").getValue());
		}
		System.out.println("jjjjjj     "+prochain_evenement(c).getProperty("DTSTART").getValue());
		String date = prochain_evenement(c).getProperty("DTSTART").getValue();
		System.out.println("kghvkjgvkgvk");

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+1:00"));
		Date prochaine_date = dateFormat.parse(date);
		System.out.println("ppppppppppppppppppppppppppp");

		java.util.Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(prochaine_date);
		date = calendar.get(calendar.YEAR)+"-";
		int mois = (calendar.get(calendar.MONTH)+1);
		if(mois<10){
			date += "0" + mois + "-";
		}
		else{
			date += mois + "-";

		}
		int date_jour = calendar.get(calendar.DATE);
		if(date_jour<10){
			date += "0" + date_jour;
		}
		else{
			date += date_jour ;

		}
		
		date += " "+calendar.get(calendar.HOUR_OF_DAY)+":"+calendar.get(calendar.MINUTE);
		System.out.println("date         "+date);
		return date;
	}
	
	public String modifier_edt(String login, String mdp, String url){
		String message_edt = "";
		String heure = null;
		
		if(this.user==null || !login.equals(this.user)){
			this.user = login;
		}
		
 	   if(this.mdp==null || !mdp.equals(this.mdp)){
 		   this.mdp = mdp;
 	   }
 	   
 	   if(url==null || !url.equals(this.url)){
 		   this.url = url;
 		   if(!url.endsWith("ics")){
 			   message_edt = "Cet emplois du temps n'est pas au format ics, veuillez en indiquer un autre";
 		   }
 		   else{
 			   try {
 				   Calendar c = getCalendar();
 				   if(c!=null){
 					   heure = heure_de_debut(c);
 					   System.out.println(heure);
 					   message_edt = "Ajout de l'emplois du temps OK";
 					   this.url = url;
 					   message_edt = "EDT ajouté avec succès !";
 				   }
 				   else{
 					   message_edt = "Une erreur est survenue pendant l'ajout de votre edt, vérifiez l'url et vos identifiants";
 				   }
 			   }
 			   catch (ParseException e) {
 				   message_edt = "Une erreur est survenue pendant l'ajout de votre edt, vérifiez l'url et vos identifiants";
 			   }
 		   }
 		   
 		   if(heure==null || !heure.equals(this.heure_reveil)){
 			   this.heure_reveil = heure;
 		   }
 	   }
 	   return message_edt;
	}
}
