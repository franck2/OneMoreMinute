package testjsp;

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
	}
	
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        
       Utilisateur utilisateur = ofy().load().type(Utilisateur.class).id(user.getEmail()).now();
       
       if(utilisateur == null){
		   utilisateur = new Utilisateur(user.getEmail());

       }
       
       //Une modification des informations du compte a ete faite
       if(req.getParameter("enregistrer")!=null){
    	   boolean choix_1=false;
    	   boolean choix_2=false;
    	   int laver, manger, lever;
			
    	   /*verification que les informations sont bien des entiers
    	    * Si ce n'ets pas le cas on met les valeurs a 0
    	    */
			try{
				laver =Integer.parseInt(req.getParameter("laver"));
			}
			catch(NumberFormatException e){
				laver = 0;
			}
			
			try{
				lever =Integer.parseInt(req.getParameter("lever"));
			}
			catch(NumberFormatException e){
				lever = 0;
			}
			
			try{
				manger =Integer.parseInt(req.getParameter("manger"));
			}
			catch(NumberFormatException e){
				manger = 0;
			}
			
			utilisateur.setDouche(laver);
			utilisateur.setLever(lever);
			utilisateur.setManger(manger);
			if(req.getParameter("handicape")!=null){
				utilisateur.getTrajet().setHandicape(true);
			}
			else{
				utilisateur.getTrajet().setHandicape(false);
			}
			/*
			 * Si l'utilisateur a eu le choix entre plusieurs proposition de depart et qu'il en a choisi un
			 */
			if(req.getParameter("depart_pa")!=null){
				utilisateur.getTrajet().enregistrer_depart(req.getParameter("depart_pa"), req.getParameter("depart"));
				choix_1=true;
			}
			/*
			 * Sinon on verifie si l'utilisateur à fait un changement ou s'il n'a pas encore de code valide
			 * On ne fait pas de requete si le nom de l'adresse depart stocke est egal a celle demandé et si il y a deja un code
			 * on fait une requete dans tous les autres cas
			 */
			else if(!(utilisateur.getTrajet().getNom_depart().equals(req.getParameter("depart"))) || utilisateur.getTrajet().getCode_depart()==null){
				String message_depart = utilisateur.getTrajet().requeteDepart(req.getParameter("depart"));

				if(message_depart.equals("ERROR")){
					message_depart = "Erreur, aucune adresse trouvé pour: "+req.getParameter("depart");
				}
				else if(message_depart.contains("OK")){
					choix_1=true;
				}
				req.setAttribute("message_depart", message_depart);
				
			}
			else{
				choix_1 = true;
			}
			
			/*
			 * Si l'utilisateur a eu le choix entre plusieurs proposition d'arrivee et qu'il en a choisi une
			 */
			if(req.getParameter("arrivee_pa")!=null){
				utilisateur.getTrajet().enregistrer_arrivee(req.getParameter("arrivee_pa"), req.getParameter("arrivee"));
				choix_2=true;
			}
			/*
			 * Sinon on verifie si l'utilisateur à fait un changement ou s'il n'a pas encore de code valide
			 * On ne fait pas de requete si le nom de l'adresse d'arrivee stocke est egal a celle demandé et si il y a deja un code
			 * on fait une requete dans tous les autres cas
			 */
			else if(!(utilisateur.getTrajet().getNom_arrivee().equals(req.getParameter("arrivee"))) || utilisateur.getTrajet().getCode_arrivee()==null){

				String message_arrivee = utilisateur.getTrajet().requeteArrivee(req.getParameter("arrivee"));
				if(message_arrivee.equals("ERROR")){
					message_arrivee = "Erreur, aucune adresse trouvé pour: "+req.getParameter("arrivee");
				}
				else if(message_arrivee.contains("OK")){
					choix_2=true;
				}
				req.setAttribute("message_arrivee", message_arrivee);

			}
			else{
				choix_2 = true;
			}
			if (choix_1 && choix_2){
				utilisateur.getTrajet().requeteTrajet("2014-11-25 10:11");
			}
       }
       
       ofy().save().entity(utilisateur).now();
       req.setAttribute("client", utilisateur);
       this.getServletContext().getRequestDispatcher( "/Compte.jsp" ).forward( req, resp );		
	}
}
