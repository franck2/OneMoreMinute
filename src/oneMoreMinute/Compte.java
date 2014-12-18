package oneMoreMinute;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Compte  extends HttpServlet {
	static{
		ObjectifyService.register(Utilisateur.class);
		ObjectifyService.register(Trajet.class);
		ObjectifyService.register(Calendrier.class);
		ObjectifyService.register(Reveil.class);
	}
		
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        Utilisateur utilisateur = ofy().load().type(Utilisateur.class).id(user.getEmail()).now();
       //Une modification des informations du compte a ete faite
		if(req.getParameter("enregistrer") != null){
    	   System.out.println(req.getParameter("reveil"));
			if(req.getParameter("reveil")!=null){
		       Reveil reveil = ofy().load().type(Reveil.class).id(req.getParameter("reveil")).now();
	    	   System.out.println(reveil);

	    	   if(reveil !=null){
		        	reveil.setUtilisateur(utilisateur.getUtilisateur());
		        	utilisateur.setReveil(req.getParameter("reveil"));
		            ofy().save().entity(reveil).now();

		        }
			}
    	   //Tout d'abord l'emploi du temps
    	   String message_edt = utilisateur.getCalendrier().modifier_edt(req.getParameter("user"), req.getParameter("mdp"), req.getParameter("edt"));
    	   req.setAttribute("message_edt", message_edt);

			
    	   /*verification que les informations sont bien des entiers
    	    * Si ce n'ets pas le cas on met les valeurs a 0
    	    */
			utilisateur.setLaver(req.getParameter("laver"));
			utilisateur.setLever(req.getParameter("lever"));
			utilisateur.setManger(req.getParameter("manger"));
			utilisateur.setDivers(req.getParameter("divers"));
			utilisateur.setMaquiller(req.getParameter("maquiller"));
			utilisateur.setGeeker(req.getParameter("geeker"));
			
			if(req.getParameter("transport").equals("tan")){
				
				utilisateur.getTrajet().setTransport("tan");
				boolean choix_depart=false;
				boolean choix_arrivee=false;
			
				/*
				 * Si l'utilisateur a eu le choix entre plusieurs proposition de depart et qu'il en a choisi un
				 */
				if(req.getParameter("pts_depart") != null){
					utilisateur.getTrajet().enregistrer_depart(req.getParameter("pts_depart"));
					choix_depart = true;
				}
				/*
				 * Sinon on verifie si l'utilisateur à fait un changement ou s'il n'a pas encore de code valide
				 * On ne fait pas de requete si le nom de l'adresse depart stocke est egal a celle demandé et si il y a deja un code
				 * on fait une requete dans tous les autres cas
				 */
				else if(utilisateur.getTrajet().getCode_depart() == null || !(utilisateur.getTrajet().getNom_depart().equals(req.getParameter("depart")))){
					String message_depart = utilisateur.getTrajet().requeteDepart(req.getParameter("depart"));
	
					if(message_depart.equals("OK")){
						choix_depart = true;
					}
					req.setAttribute("message_depart", message_depart);
					
				}
				else{
					choix_depart = true;
				}
				
				/*
				 * Si l'utilisateur a eu le choix entre plusieurs proposition d'arrivee et qu'il en a choisi une
				 */
				if(req.getParameter("pts_arrivee") != null){
					utilisateur.getTrajet().enregistrer_arrivee(req.getParameter("pts_arrivee"));
					choix_arrivee = true;
				}
				/*
				 * Sinon on verifie si l'utilisateur à fait un changement ou s'il n'a pas encore de code valide
				 * On ne fait pas de requete si le nom de l'adresse d'arrivee stocke est egal a celle demandé et si il y a deja un code
				 * on fait une requete dans tous les autres cas
				 */
				else if(utilisateur.getTrajet().getCode_arrivee() == null || !(utilisateur.getTrajet().getNom_arrivee().equals(req.getParameter("arrivee")))){
					String message_arrivee = utilisateur.getTrajet().requeteArrivee(req.getParameter("arrivee"));
					if(message_arrivee.equals("OK")){
						choix_arrivee = true;
					}
					req.setAttribute("message_arrivee", message_arrivee);
				}
				else{
					choix_arrivee = true;
				}
				if (choix_depart && choix_arrivee && utilisateur.getCalendrier().getHeure_reveil() != null){
					String message_trajet = utilisateur.getTrajet().requeteTrajet(utilisateur.getCalendrier().getHeure_reveil());
					req.setAttribute("message_trajet", message_trajet);
				}
			}
			else{
				String message_trajet = utilisateur.getTrajet().enregistrer_google_trajet(req.getParameter("transport"), req.getParameter("depart"), req.getParameter("arrivee"), req.getParameter("itineraire"), utilisateur.getCalendrier().getHeure_reveil());
				req.setAttribute("message_trajet", message_trajet);
			}
       }

       ofy().save().entity(utilisateur).now();
       req.setAttribute("client", utilisateur);
       this.getServletContext().getRequestDispatcher( "/Compte.jsp" ).forward( req, resp );		
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}

}