package testjsp;

import java.io.OutputStreamWriter;
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

	private Trajet(){
		
	}
	
	public Trajet(String user){
		this.nom_depart = "Arret, rue, ...";
		this.code_depart = null;
		this.transport = null;
		this.nom_arrivee = "Arret, rue, ...";
		this.code_arrivee = null;
		this.detail_Trajet="";
		this.id=user;
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
	
	/* Permet de faire une requete sur le site de la tan
	 * adresse contient l'adresse que l'on recherche
	 * lieu doit contenir les string "depart" ou "arrivee" (c'est un paramètre de la requete)
	 * return le code de l'adresse si elle est trouvee est trouvé
	 * return "ERREUR" si aucune adresse n'a été trouvée
	 * return du code html (balises input et label) si plusieurs adresses ont été trouvees et s l'utilisateur doit faire un choix)
	 */
	public String requete(String adresse, String lieu) throws IOException{
		
		String ur="https://www.tan.fr/ewp/mhv.php/itineraire/address.html";
		String post="nom="+adresse+"&prefix="+lieu;
		URL url = new URL(ur);
		URLConnection  conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(post);
		writer.flush();
		//recuperation du code html
		String reponse="",ligne = null;

		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		boolean v=false;
		boolean fini=false;
		/*
		 * Ce qui nous interesse se trouve entre "<h2>Mon espace</h2>" et "<!-- pied de page K-Portal -->"
		 */
		while ((ligne = reader.readLine()) != null && fini==false) {
			String s=ligne.trim();
			if(s.contains("<h2>Mon espace</h2>")){
				reponse="";
				v=true;
			}
			if(v){

				if(s.contains("ERROR")){
					reponse = "ERROR";
				}
				else if(s.contains("ID:")){
					reponse=s;
				}
				else if(s.contains("input")){
					reponse+=s+"\n";
					
				}
				else if(s.contains("label")){
					reponse+=s+"<br/>\n";
				}
			}			
			if(s.contains("<!-- pied de page K-Portal -->")){
				v=false;
				fini =true;
			}
		}
		return reponse;
	}
	
	/* Fait appel a la methode requete(..) et met à jour les attribut correspondant au depart
	 * return "OK" si le code st trouve et met a jour les attribut correspondant
	 * return "ERREUR"
	 * return code html
	 */
	public String requeteDepart(String adresse){
		String resultat;
		try {
			resultat = this.requete(adresse, "depart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			resultat = null;
		}
		if(resultat==null){
			resultat = "une erreur est survenu sur le site de la tan";
		}
		else if(resultat.contains("ID:")){
			resultat=resultat.substring( resultat.lastIndexOf("ID"), resultat.lastIndexOf("<!--"));
			String[] tab=resultat.split(":\\/");
			this.nom_depart=tab[2];
			this.code_depart=tab[1];
			resultat="OK";
		}	
		else if(resultat.contains("ERROR")){
			resultat = "Erreur, aucune adresse trouvé pour: "+adresse;
		}
		else{
			this.nom_depart = "Arret, rue, ...";
			this.code_depart = null;
		}
		return resultat;
	}
	
	/* Fait appel a la methode requete(..) et met à jour les attribut correspondant a l'arrivee
	 * return "OK" si le code st trouve et met a jour les attribut correspondant
	 * return "ERREUR"
	 * return code html
	 */
	public String requeteArrivee(String adresse){
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
			resultat=resultat.substring( resultat.lastIndexOf("ID"), resultat.lastIndexOf("<!--"));
			String[] tab=resultat.split(":\\/");
			this.nom_arrivee=tab[2];
			this.code_arrivee=tab[1];
			resultat="OK";
		}
		else if(resultat.contains("ERROR")){
			resultat = "Erreur, aucune adresse trouvé pour: "+adresse;
		}
		else{
			this.nom_arrivee = "Arret, rue, ...";
			this.code_arrivee = null;
		}
		return resultat;
	}
	
	/* Methode qui permet de recuperer les 3 proposition de chemins 
	 * 
	 */
	public void requeteTrajet(String heure) throws IOException{
		String ur = "https://www.tan.fr/ewp/mhv.php/itineraire/resultat.html";
		String post = "depart="+this.code_depart+"&arrive="+this.code_arrivee+"&type="+1+"&accessible="+0+"&temps="+heure+"&retour=0";
		/**
		 * idee: tout garder entre <p></p> puis supprimer toutes les balises
		 * seul probleme: les deux premiers depart et arrivee, avec les heures. le faire séparément.
		 */
		URL url = new URL(ur);
		URLConnection  conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(post);
		writer.flush();
		//recuperation du code html
		String reponse="",ligne = null;
	
		InputStreamReader sr = new InputStreamReader(conn.getInputStream(),"ISO-8859-1");
		BufferedReader reader = new BufferedReader(sr);
		boolean trouve_iti=false;
		boolean fini=false;
		boolean resultat_iti=false;
		/*
		 * Ce qui nous interesse se trouve entre "<h2>Mon espace</h2>" et "<!-- pied de page K-Portal -->"
		 */
		while ((ligne = reader.readLine()) != null && fini==false) {
			ligne=ligne.trim();
			if(ligne.contains("ERRORA")){
				fini=true;
				reponse="<p>Aucun trajet acsessible pour personne handicapée</p>";
			}

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
					reponse+="</p><p>";
				}

				reponse+=ligne+"\n";

			}
			
			if(ligne.contains("<!-- pied de page K-Portal -->") || ligne.contains("Itinéraire 2 :")){
				trouve_iti=false;
				fini =true;
			}
			
			if(ligne.contains("Itinéraire 1 :")){
				trouve_iti=true;
			}			
			if(trouve_iti && ligne.contains("résultat d'itinéraire")){
				resultat_iti=true;
			}
		}
		reponse="<div>Départ à :"+this.heure_depart+"<p>"+reponse+"\n<p>Arrivée à : "+this.heure_arrivee+"</p></div>";
		this.detail_Trajet = reponse;
	}

	/*
	 *Est utiliser quand l'utilisateur a le choix entre plusieur adresses d'arrivee
	 *prend entree le code de l'adresse (qui contient notament le nom de l'adresse)
	 *modifie nom_arrivee et code_arrivee 
	 */
	public void enregistrer_arrivee(String code, String nom) {
		this.nom_arrivee=nom;
		this.code_arrivee=code;
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
		int fin = t.lastIndexOf(">")+1;
		while(debut>=0){
			String sous_chaine = t.substring(debut, fin);
			t = t.replace(sous_chaine, "");
			debut = t.lastIndexOf("<");
			fin = t.lastIndexOf(">")+1;
		}
		return t;
	}

	public boolean enregistrer_google_trajet(String transport, String depart, String arrivee, String itineraire, String heure) {
		this.code_arrivee = null;
		this.code_arrivee = null;
		this.nom_arrivee = arrivee;
		this.nom_depart = depart;
		this.transport = transport;
		return extraire_itineraire(itineraire);		
	}
	
	public boolean extraire_itineraire(String itineraire){
		String sous_chaine = itineraire.substring(itineraire.lastIndexOf(":iti:"), itineraire.lastIndexOf(":finiti:"));
		this.detail_Trajet = sous_chaine;
		return true;
	}
}
