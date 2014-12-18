package oneMoreMinute;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.googlecode.objectify.annotation.*;

@Entity
public class Utilisateur {


		@Id private String utilisateur;
		
		private int laver;
		private int maquiller;
		private int geeker;
		private int lever;
		private int manger;
		private int divers;
		private Calendrier calendrier;
		private Trajet trajet;
		private String date_reveil;    	// => date_reveil
		private String reveil;
		//lien de la musique de reveil
		private String musique;

		public String getMusique() {
			return musique;
		}

		public void setMusique(String musique) {
			this.musique = musique;
		}

		public String getUtilisateur() {
			return utilisateur;
		}

		public void setUtilisateur(String utilisateur) {
			this.utilisateur = utilisateur;
		}
		
		public String getReveil() {
			return reveil;
		}

		public void setReveil(String reveil) {
			this.reveil = reveil;
		}

		public String getDate_reveil() {
			return date_reveil;
		}

		public void setDate_reveil() {
			NumberFormat formatter = new DecimalFormat("00");

			String heure_dep = this.getTrajet().getHeure_depart();
			if(heure_dep != null){
				int temps_preparation = (this.laver + this.lever + this.geeker + this.manger + this.maquiller + this.divers);
	
				int h = temps_preparation/60;
				int m = (temps_preparation-h*60);
				 Calendar cc = new GregorianCalendar();
				 cc.set(Integer.parseInt(heure_dep.substring(0, 4)), (Integer.parseInt(heure_dep.substring(5, 7))-1), (Integer.parseInt(heure_dep.substring(8,10))),Integer.parseInt(heure_dep.substring(heure_dep.lastIndexOf(":")-2, heure_dep.lastIndexOf(":"))) ,Integer.parseInt(heure_dep.substring(heure_dep.lastIndexOf(":")+1, heure_dep.lastIndexOf(":")+3)) );
	
				 cc.add(cc.HOUR, -h);
				 cc.add(cc.MINUTE, -m);
				 System.out.println("je suis ici");
				 date_reveil = formatter.format(cc.get(cc.DATE)) + "/"+formatter.format(cc.get(cc.MONTH) + 1) + "/" + cc.get(cc.YEAR) + " - " + formatter.format(cc.get(cc.HOUR)) + ":"+formatter.format(cc.get(cc.MINUTE));
			}
		}

		public int getLaver() {
			return laver;
		}

		public void maj(){
			this.getTrajet().requeteTrajet(this.getCalendrier().getDate_reveil());
			this.setDate_reveil();
		}
		
		public void setLaver(String laver) {
			try{
				this.laver = Integer.parseInt(laver);
			}
			catch(NumberFormatException e){
				this.laver = 0;
			}		
		}

		public int getMaquiller() {
			return maquiller;
		}

		public void setMaquiller(String maquiller) {
			try{
				this.maquiller = Integer.parseInt(maquiller);
			}
			catch(NumberFormatException e){
				this.maquiller = 0;
			}
		}

		public int getGeeker() {
			return geeker;
		}

		public void setGeeker(String geeker) {
			try{
				this.geeker = Integer.parseInt(geeker);
			}
			catch(NumberFormatException e){
				this.geeker = 0;
			}
		}

		public int getDivers() {
			return divers;
		}

		public void setDivers(String divers) {
			try{
				this.divers = Integer.parseInt(divers);
			}
			catch(NumberFormatException e){
				this.divers = 0;
			}
		}
		
		public Calendrier getCalendrier() {
			return calendrier;
		}

		public void setCalendrier(Calendrier calendrier) {
			this.calendrier = calendrier;
		}
		
		public int getLever() {
			return lever;
		}
		
		public void setLever(String lever) {
			try{
				this.lever = Integer.parseInt(lever);
			}
			catch(NumberFormatException e){
				this.lever = 0;
			}
		}
		
		public int getManger() {
			return manger;
		}
		
		public void setManger(String manger) {
			try{
				this.manger = Integer.parseInt(manger);
			}
			catch(NumberFormatException e){
				this.manger = 0;
			}
		}

		public Trajet getTrajet() {
			return trajet;
		}

		public void setTrajet(Trajet trajet) {
			this.trajet = trajet;
		}

		private Utilisateur(){}
		
		public Utilisateur(String user){
			this.laver = 0;
			this.lever = 0;
			this.manger = 0;
			this.maquiller = 0;
			this.geeker = 0;
			this.divers = 0;
			this.date_reveil = null;
			this.utilisateur = user;
			this.trajet = new Trajet(user);
			this.calendrier = new Calendrier(user);
			this.reveil = null;
		}

		
}
