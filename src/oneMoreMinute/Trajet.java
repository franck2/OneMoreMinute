package oneMoreMinute;

import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.googlecode.objectify.annotation.*;

@Entity
public class Trajet{
	
	@Id String id;
	private String nom_depart;
	private String heure_depart, heure_arrivee;
	private String code_depart;
	private String nom_arrivee;
	private String code_arrivee;
	private String detail_Trajet;
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
		this.detail_Trajet = "";
		this.id = user;
	}
	
	/* Permet de faire une requete sur le site de la tan
	 * adresse contient l'adresse que l'on recherche
	 * lieu doit contenir les string "depart" ou "arrivee" (c'est un paramètre de la requete)
	 * return le code de l'adresse si elle est trouvee est trouvé
	 * return "ERREUR" si aucune adresse n'a été trouvée
	 * return du code html (balises input et label) si plusieurs adresses ont été trouvees et si l'utilisateur doit faire un choix)
	 */
	public String requete(String adresse, String lieu) throws IOException{
		
		String ur = "https://www.tan.fr/ewp/mhv.php/itineraire/address.html";
		String post = "nom=" + adresse + "&prefix=" + lieu;
		URL url = new URL(ur);
		URLConnection  conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(post);
		writer.flush();

		String reponse = "", ligne = null;

		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		boolean chercher_information = false;
		boolean fini = false;
		/*
		 * Ce qui nous interesse se trouve entre les balises "<h2>Mon espace</h2>" et "<!-- pied de page K-Portal -->"
		 */
		while ((ligne = reader.readLine()) != null && fini == false) {
			String ligne_courante = ligne.trim();
			if(ligne_courante.contains("<h2>Mon espace</h2>")){
				reponse = "";
				chercher_information = true;
			}
			if(chercher_information){

				if(ligne_courante.contains("ERROR")){
					reponse = "ERROR";
				}
				else if(ligne_courante.contains("ID:")){
					reponse = ligne_courante;
				}
				else if(ligne_courante.contains("input")){
					reponse += ligne_courante + "\n";
					
				}
				else if(ligne_courante.contains("label")){
					reponse += ligne_courante + "<br/>\n";
				}
			}			
			if(ligne_courante.contains("<!-- pied de page K-Portal -->")){
				chercher_information = false;
				fini = true;
			}
		}
		return reponse;
	}
	
	/* Fait appel a la methode requete(..) et met à jour les attribut correspondant au depart
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
		
		try {
			resultat = this.requete(adresse, "depart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			resultat = null;
		}
		
		if(resultat == null){
			resultat = "une erreur est survenu sur le site de la tan";
		}
		else if(resultat.contains("ID:")){
			resultat = resultat.substring( resultat.lastIndexOf("ID:"), resultat.lastIndexOf("<!--"));
			String[] tab = resultat.split(":\\/");
			this.nom_depart = tab[2];
			this.code_depart = tab[1];
			resultat = "OK";
		}	
		else if(resultat.contains("ERROR")){
			resultat = "Erreur, aucune adresse trouvé pour: " + adresse;
		}

		return resultat;
	}
	
	/* Fait appel a la methode requete(..) et met à jour les attribut correspondant a l'arrivee
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
		try {
			resultat = this.requete(adresse, "arrivee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			resultat = null;
		}

		if(resultat==null){
			resultat = "une erreur est survenu sur le site de la tan";
		}
		else if(resultat.contains("ID:")){
			resultat = resultat.substring( resultat.lastIndexOf("ID:"), resultat.lastIndexOf("<!--"));
			String[] tab = resultat.split(":\\/");
			this.nom_arrivee = tab[2];
			this.code_arrivee = tab[1];
			
			resultat = "OK";
		}
		else if(resultat.contains("ERROR")){
			resultat = "Erreur, aucune adresse trouvé pour: " + adresse;
		}

		return resultat;
	}
	
	/* 
	 * Methode qui permet d'avoir un itineraire Tan 
	 */
	public String requeteTrajet(String heure){
		
		String message_iti;
		String ur = "https://www.tan.fr/ewp/mhv.php/itineraire/resultat.html";
		String post = "depart=" + this.code_depart + "&arrive=" + this.code_arrivee + "&type=1" + "&accessible=0" + "&temps=" + heure + "&retour=0";
		URL url;
		
		try {
			url = new URL(ur);
		
			URLConnection  conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(post);
			writer.flush();

			String reponse = "",ligne = null;
	
			InputStreamReader sr = new InputStreamReader(conn.getInputStream(),"ISO-8859-1");
			BufferedReader reader = new BufferedReader(sr);
			boolean trouve_iti = false;
			boolean fini = false;
			boolean resultat_iti = false;
			
		/*
		 * Ce qui nous interesse se trouve entre "<h2>Mon espace</h2>" et "<!-- pied de page K-Portal -->"
		 */

			while ((ligne = reader.readLine()) != null && fini==false) {
				
				ligne = ligne.trim();

				if(trouve_iti && ligne.contains("<li>Départ")){
					Pattern p = Pattern .compile("([0-9][0-9]:[0-9][0-9])");
					Matcher m = p.matcher(ligne);
					if(m.find()){
						heure_depart = ligne.substring(m.start(), m.end());
					}
				}
				else if(trouve_iti && ligne.contains("<li>Arrivée")){
					Pattern p = Pattern .compile("([0-9][0-9]:[0-9][0-9])");
					Matcher m = p.matcher(ligne);
					if(m.find()){
						heure_arrivee = ligne.substring(m.start(), m.end());
					}
				}
				
				/**
				 * Les informations qui nous interessent sont dans
				 * des balises p.
				 */
				else if(resultat_iti && ligne.contains("<p")){
					ligne = supprimer_balise(ligne);

					Pattern p = Pattern .compile("([0-9][0-9]h[0-9][0-9])");
					Matcher m = p.matcher(ligne);
					if(m.find()){
						reponse += "<br/>";
					}

					reponse += ligne+"\n";

				}
				
				if(ligne.contains("<!-- pied de page K-Portal -->") || ligne.contains("Itinéraire 2 :")){
					trouve_iti = false;
					fini = true;
				}
				
				if(ligne.contains("Itinéraire 1 :")){
					trouve_iti = true;
				}			
				if(trouve_iti && ligne.contains("résultat d'itinéraire")){
					resultat_iti = true;
				}
			}
			this.detail_Trajet = reponse;
			message_iti = "Itinéraire ajouté avec succès";
		}
		catch (IOException e2) {
			message_iti = "ERREUR un problème est survenu, veuillez reesayer";
		}
		return message_iti;
	}

	/*
	 *Est utiliser quand l'utilisateur a le choix entre plusieur adresses d'arrivee
	 *prend entree le code de l'adresse (qui contient notament le nom de l'adresse)
	 *modifie nom_arrivee et code_arrivee 
	 */
	public void enregistrer_arrivee(String code, String nom) {
		this.nom_arrivee = nom;
		this.code_arrivee = code;
	}	
	
	/*
	 *Est utiliser quand l'utilisateur a le choix entre plusieur adresses de depart
	 *prend entree le code de l'adresse (qui contient notament le nom de l'adresse)
	 *modifie nom_depart et code_depart 
	 */
	public void enregistrer_depart(String code, String nom) {
		this.nom_depart = nom;
		this.code_depart = code;
	}
	
	public String supprimer_balise(String t){

		int debut = t.lastIndexOf("<");
		int fin = t.lastIndexOf(">") + 1;
		
		while(debut >= 0){
			String sous_chaine = t.substring(debut, fin);
			t = t.replace(sous_chaine, " ");
			debut = t.lastIndexOf("<");
			fin = t.lastIndexOf(">") + 1;
		}
		fin = t.lastIndexOf("\n");
		while(fin >= 0){
			String sous_chaine = t.substring(fin);
			t = t.replace(sous_chaine, "<br/>");
			fin = t.lastIndexOf("\n");
		}
		return t;
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
	

	public void calculer_heure_depart(String duree, String heure){
		int duree_seconde;
		
		try{
			duree_seconde = Integer.parseInt(duree)+5*60;
		}
		catch(NumberFormatException e){
			duree_seconde = 0;
		}

		int heure_seconde = ((Integer.parseInt(heure.substring(heure.lastIndexOf(" ")+1, heure.lastIndexOf(":")))*60*60) + Integer.parseInt(heure.substring(heure.lastIndexOf(":")+1))*60);
		heure_seconde = heure_seconde - duree_seconde;

		double heure_h = heure_seconde/60/60;
		Double d = new Double(heure_h);
		
		this.heure_depart = d.intValue() + "h";
		int g = Math.round((heure_seconde-d.intValue()*60*60)/60);
		
		if(g<10){
			this.heure_depart += "0" + g;
		}
		else{
			this.heure_depart += g;
		}
		
		heure_seconde += duree_seconde - 5*60;
		
		this.heure_arrivee = heure_seconde/60/60 + "h" + (heure_seconde/60 - (heure_seconde/60/60)*60);
	}
}
