package oneMoreMinute;

import com.googlecode.objectify.annotation.*;

@Entity
public class Reveil {

	@Id private String identifiant;
	private String utilisateur;

	private Reveil(){
		
	}
	public Reveil(String id){
		this.identifiant = id;
		this.utilisateur = null;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
