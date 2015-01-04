package oneMoreMinute;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.googlecode.objectify.annotation.*;

@Entity
public class Trajet{
	
	@Id String id;
	/*
	 * Nom des adresses
	 */
	private String nom_depart;
	private String nom_arrivee;

	private String heure_depart, heure_arrivee;
	
	/*
	 * null si l'utilisateur n'utilise pas la tan, code specifique a la tan
	 */
	private String code_depart;
	private String code_arrivee;
	
	/*
	 * informations sur le trajet
	 */
	private String detail_Trajet;
	
	/*
	 * ta, voiture, velo ...
	 */
	private String transport;

	public String getDetail_Trajet() {
		return detail_Trajet;
	}

	public void setDetail_Trajet(String detail_Trajet) {
		this.detail_Trajet = detail_Trajet;
	}
	
	public String getHeure_depart() {
		return heure_depart;
	}

	public void setHeure_depart(String heure_depart) {
		this.heure_depart = heure_depart;
	}

	public String getHeure_arrivee() {
		return heure_arrivee;
	}

	public void setHeure_arrivee(String heure_arrivee) {
		this.heure_arrivee = heure_arrivee;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public void setNom_depart(String nom_depart) {
		this.nom_depart = nom_depart;
	}

	public void setNom_arrivee(String nom_arrivee) {
		this.nom_arrivee = nom_arrivee;
	}

	public String getNom_depart() {
		return nom_depart;
	}
	
	public void setDepart(String depart) {
		this.nom_depart = depart;
	}
	
	public String getNom_arrivee() {
		return this.nom_arrivee;
	}
	
	public void setArrivee(String arrivee) {
		this.nom_arrivee = arrivee;
	}
	
	public String getCode_depart() {
		return code_depart;
	}
	public void setCode_depart(String code_depart) {
		this.code_depart = code_depart;
	}
	public String getCode_arrivee() {
		return code_arrivee;
	}
	public void setCode_arrivee(String code_arrivee) {
		this.code_arrivee = code_arrivee;
	}
	
	private Trajet(){
		
	}
	
	public Trajet(String user){
		this.nom_depart = null;
		this.code_depart = null;
		this.transport = "tan";
		this.heure_arrivee = null;
		this.heure_depart = null;
		this.nom_arrivee = null;
		this.code_arrivee = null;
		this.detail_Trajet = null;
		this.id = user;
	}
	
	/* Permet de faire une requete sur le site de la tan
	 * adresse contient l'adresse que l'on recherche
	 * lieu doit contenir les string "depart" ou "arrivee" (c'est un paramÃ¨tre de la requete)
	 * return le code de l'adresse si elle est trouvee est trouvÃ©
	 * return "ERREUR" si aucune adresse n'a Ã©tÃ© trouvÃ©e
	 * return du code html (balises input et label) si plusieurs adresses ont Ã©tÃ© trouvees et si l'utilisateur doit faire un choix)
	 */
	public String requete(String adresse, String point){
		
		String uri = "https://www.tan.fr/ewp/mhv.php/itineraire/address.json";
		String post = "nom=" + adresse + "&prefix=" + point;
		String reponse = "";
		
		try {
			
			URL url = new URL(uri);
			URLConnection  conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(post);
			writer.flush();
	
			String ligne = null;
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
			boolean fini = false;
			/*
			 * Ce qui nous interesse se trouve entre les balises "<h2>Mon espace</h2>" et "<!-- pied de page K-Portal -->"
			 */
			while ((ligne = reader.readLine()) != null && fini == false) {
				String ligne_courante = ligne.trim();
				reponse += ligne_courante;
			}
	
			JSONParser g = new JSONParser();
			JSONArray reponses;
			/*requete simple*/

			reponses = (JSONArray) g.parse(reponse);
			
			if(reponses.size() == 0){
				reponse = "Aucune adresse trouvée pour "+adresse;
			}
			else{
				JSONObject rep = (JSONObject) reponses.get(0);
				JSONArray lieux = (JSONArray) rep.get("lieux");

				if(reponses.size() == 1 && lieux.size() == 1){
					rep = (JSONObject) lieux.get(0);
					if(point.equals("depart")){
						this.code_depart = rep.get("id").toString();
						this.nom_depart = rep.get("nom").toString() + " " + rep.get("cp").toString() + " " + rep.get("ville").toString();
					}
					else{
						this.code_arrivee = rep.get("id").toString();
						this.nom_arrivee = rep.get("nom").toString() + " " + rep.get("cp").toString() + " " + rep.get("ville").toString();
					}
					reponse = "OK";
				}
				else{
					reponse = "";
					for(int j = 0; j < reponses.size(); j++){
						rep = (JSONObject) reponses.get(j);
						lieux = (JSONArray) rep.get("lieux");
						for(int i =0; i<lieux.size(); i++){
							rep = (JSONObject) lieux.get(i);
							String nom = rep.get("nom") + " " + rep.get("cp") + " " + rep.get("ville");
							reponse += "<input type=\"radio\" name=\"pts_" + point +"\" id=\"" + rep.get("id") + "\" value=\"" +  rep.get("id") + ":|:" + nom + "\" />\n";
							reponse += "<label for=\"" + rep.get("id") + "\" >" + nom + "</label><br/>\n";
						}

					}
					
				}
			}

		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			reponse="Une erreur est survenue sur le site de la tan, veuillez réesayer ou recommencer plus tard.";
		}
		return reponse;
	}
	
	/* Fait appel a la methode requete(..) et met Ã  jour les attribut correspondant au depart
	 * return "OK" si le code st trouve et met a jour les attribut correspondant
	 * return "ERREUR" si rien de trouve
	 * return code html si plusieurs choix
	 */
	public String requeteDepart(String adresse){
		
		this.nom_depart = null;
		this.code_depart = null;
		this.detail_Trajet = null;
		this.heure_arrivee = null;
		this.heure_depart = null;
		
		String resultat;
		resultat = this.requete(adresse, "depart");
		return resultat;
	}
	
	/* Fait appel a la methode requete(..) et met Ã  jour les attribut correspondant a l'arrivee
	 * return "OK" si le code est trouve et met a jour les attribut correspondant
	 * return "ERREUR" si rien de trouve
	 * return code html si plusieurs choix
	 */
	public String requeteArrivee(String adresse){
		
		this.nom_arrivee = null;
		this.code_arrivee = null;
		this.detail_Trajet = null;
		this.heure_arrivee = null;
		this.heure_depart = null;
		
		String resultat;
		resultat = this.requete(adresse, "arrivee");
		
		return resultat;
	}
	
	/* 
	 * Methode qui permet d'avoir un itineraire Tan 
	 */
	public String requeteTrajet(String heure){
		String message_iti = "";

		if(heure==null){
			this.heure_arrivee = null;
			this.heure_depart = null;
			this.detail_Trajet = null;
		}
		else{
		String uri = "https://www.tan.fr/ewp/mhv.php/itineraire/resultat.json";
		String post = "depart=" + this.code_depart + "&arrive=" + this.code_arrivee + "&type=1" + "&accessible=0" + "&temps=" + heure + "&retour=0";
		URL url;
		this.heure_arrivee = heure.substring(0, heure.lastIndexOf(" "));
		try {
			url = new URL(uri);
		
			URLConnection  conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(post);
			writer.flush();

			String ligne = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			boolean fini = false;
	
			while ((ligne = reader.readLine()) != null && fini == false) {
				message_iti += ligne.trim();
			}
			

			JSONParser g = new JSONParser();
			JSONArray reponses;

			reponses = (JSONArray) g.parse(message_iti);
			
			if(reponses.size() == 0){
				message_iti = "Une erreur s'est produite";
			}
			else{
				JSONObject rep = (JSONObject) reponses.get(0);
				
				Calendar cal = new GregorianCalendar();
				int diff = 0;
				
				//si le depart doit se faire le jour d'avant l'arriver on met diff a -1
				if((rep.get("heureDepart").toString()).compareTo(rep.get("heureArrivee").toString()) > 0){
					diff = -1;
				}
				
				cal.set(Integer.parseInt(this.heure_arrivee.substring(0, 4)), (Integer.parseInt(this.heure_arrivee.substring(5, 7))-1), (Integer.parseInt(this.heure_arrivee.substring(8))+diff));
				NumberFormat formatter = new DecimalFormat("00");
				
				this.heure_depart = (cal.get(cal.YEAR)) + "-" + formatter.format((cal.get(cal.MONTH)+1)) + "-" + formatter.format(cal.get(cal.DATE))+" - "+rep.get("heureDepart").toString();

				this.heure_arrivee += " - " + rep.get("heureArrivee").toString();
				this.detail_Trajet = "Durée du trajet: " + rep.get("duree").toString() + "\n<br/>";
				
				JSONArray etapes = (JSONArray) rep.get("etapes");
				
				for(int i = 0; i < etapes.size(); i++){
					JSONObject x = (JSONObject) etapes.get(i);
					if((boolean) x.get("marche")){
						this.detail_Trajet += x.get("heureDepart") + " Rejoindre à  pied: " + ((JSONObject) x.get("arretStop")).get("libelle") + " " + x.get("duree") + "\n<br/>";
					}
						
					else{
						this.detail_Trajet += x.get("heureDepart") +" Prendre la ligne : " + ((JSONObject) x.get("ligne")).get("numLigne") + " en direction de " + ((JSONObject) x.get("ligne")).get("terminus") + ". Descendre à  l'arret " + ((JSONObject) x.get("arretStop")).get("libelle") + " " + x.get("duree") + "\n<br/>";
					}
				}

			}
			message_iti = "Itinéraire ajouté avec succès";
		}
		catch (IOException | ParseException e2) {
			message_iti = "ERREUR un problème est survenu, veuillez reesayer";
		} 
		}
		return message_iti;
	}

	/*
	 *Est utilise quand l'utilisateur a le choix entre plusieur adresses d'arrivee
	 *prend entree le code de l'adresse (qui contient notament le nom de l'adresse)
	 *modifie nom_arrivee et code_arrivee 
	 */
	public void enregistrer_arrivee(String valeur) {
		this.nom_arrivee = valeur.substring(valeur.lastIndexOf(":|:") + 3);
		this.code_arrivee = valeur.substring(0, valeur.lastIndexOf(":|:"));
	}	
	
	/*
	 *Est utilise quand l'utilisateur a le choix entre plusieur adresses de depart
	 *prend entree le code de l'adresse (qui contient notament le nom de l'adresse)
	 *modifie nom_depart et code_depart 
	 */
	public void enregistrer_depart(String valeur) {
		this.nom_depart = valeur.substring(valeur.lastIndexOf(":|:") + 3);
		this.code_depart = valeur.substring(0, valeur.lastIndexOf(":|:"));
	}
	
	/*
	 * Methode utilisee pour supprimer les balises inutiles dans le detail d'itineraire de google
	 */
	public String supprimer_balise(String chaine){

		int debut = chaine.lastIndexOf("<");
		int fin = chaine.lastIndexOf(">") + 1;
		
		while(debut >= 0){
			String sous_chaine = chaine.substring(debut, fin);
			chaine = chaine.replace(sous_chaine, " ");
			debut = chaine.lastIndexOf("<");
			fin = chaine.lastIndexOf(">") + 1;
		}
		fin = chaine.lastIndexOf("\n");
		while(fin >= 0){
			String sous_chaine = chaine.substring(fin);
			chaine = chaine.replace(sous_chaine, "<br/>");
			fin = chaine.lastIndexOf("\n");
		}
		return chaine;
	}

	public String enregistrer_google_trajet(String transport, String depart, String arrivee, String itineraire, String heure) {
		this.transport = transport;
		this.code_arrivee = null;
		this.code_arrivee = null;
		
		if(!itineraire.contains("ERREUR")){
			this.nom_arrivee = arrivee;
			this.nom_depart = depart;
			String duree =  extraire_itineraire(itineraire);
			calculer_heure_depart(duree, heure);
			return "Itineraire bien enregistré";
		}
		else{
			this.nom_arrivee = null;
			this.nom_depart = null;
			this.heure_arrivee = null;
			this.heure_depart = null;
			this.detail_Trajet = null;
			return itineraire;
		}
	}
	
	public String extraire_itineraire(String itineraire){
		String sous_chaine = itineraire.substring((itineraire.lastIndexOf(":iti:") + 5), itineraire.lastIndexOf(":finiti:"));
		sous_chaine = supprimer_balise(sous_chaine);
		this.detail_Trajet = sous_chaine;
		sous_chaine = itineraire.substring((itineraire.lastIndexOf(":duree:") + 7), itineraire.lastIndexOf(":finduree:"));
		return sous_chaine;
	}
	

	/*
	 * Clacule une heure de depart en fonction d'une heure d'arrivee voulue, et de la duree du trajet
	 */
	public void calculer_heure_depart(String duree, String heure){

		NumberFormat formater = new DecimalFormat("00");
		int duree_seconde = Integer.parseInt(duree);

		Calendar cc = new GregorianCalendar();
		cc.set(Integer.parseInt(heure.substring(0, 4)), (Integer.parseInt(heure.substring(5, 7))-1), (Integer.parseInt(heure.substring(8,10))), Integer.parseInt(heure.substring(heure.lastIndexOf(" ")+1, heure.lastIndexOf(":"))), (Integer.parseInt(heure.substring(heure.lastIndexOf(":")+1))-10));

		this.heure_arrivee = cc.get(cc.YEAR) + "-" + cc.get(cc.MONTH) + "-" + cc.get(cc.DATE) + " - " + formater.format(cc.get(cc.HOUR_OF_DAY)) + ":" + formater.format(cc.get(cc.MINUTE));
		
		int h = duree_seconde/60/60;
		int m = (duree_seconde-h)/60;
				
		cc.add(cc.HOUR, -h);
		cc.add(cc.MINUTE, -m);
		this.heure_depart = (cc.get(cc.YEAR)) + "-" + formater.format((cc.get(cc.MONTH)+1)) + "-" + formater.format(cc.get(cc.DATE))+" - "+ formater.format(cc.get(cc.HOUR_OF_DAY)) + ":" + formater.format(cc.get(cc.MINUTE));
		
	}
}
