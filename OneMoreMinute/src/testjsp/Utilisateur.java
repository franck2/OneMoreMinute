package testjsp;


import com.googlecode.objectify.annotation.*;

@Entity
public class Utilisateur {


		@Id private String utilisateur;
		
		private int douche;
		private int lever;
		private int manger;
		private String edt;

		public String getEdt() {
			return edt;
		}

		public void setEdt(String edt) {
			this.edt = edt;
		}

		private Trajet trajet;
		
		private Utilisateur(){}
		
		public Utilisateur(String user){
			this.douche = 0;
			this.lever = 0;
			this.manger = 0;
			this.utilisateur = user;
			trajet = new Trajet(user);
		}
		
		public int getDouche() {
			return douche;
		}
		
		public void setDouche(int douche) {
			this.douche = douche;
		}
		
		public int getLever() {
			return lever;
		}
		
		public void setLever(int lever) {
			this.lever = lever;
		}
		
		public int getManger() {
			return manger;
		}
		
		public void setManger(int manger) {
			this.manger = manger;
		}

		public Trajet getTrajet() {
			return trajet;
		}

		public void setTrajet(Trajet trajet) {
			this.trajet = trajet;
		}



		
}
