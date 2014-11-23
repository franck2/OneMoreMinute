package oneMoreMinute;


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
		private String heure_reveil;


		public String getHeure_reveil() {
			return heure_reveil;
		}

		public void setHeure_reveil(String heure_reveil) {
			this.heure_reveil = heure_reveil;
		}

		public int getLaver() {
			return laver;
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
			this.heure_reveil = null;
			this.utilisateur = user;
			this.trajet = new Trajet(user);
			this.calendrier = new Calendrier(user);
		}

		
}
