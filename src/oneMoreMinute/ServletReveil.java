package oneMoreMinute;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class ServletReveil extends HttpServlet {
	
	
	static{
		ObjectifyService.register(Utilisateur.class);
		ObjectifyService.register(Reveil.class);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        Reveil reveil = ofy().load().type(Reveil.class).id(req.getParameter("id")).now();

        if(reveil == null){
        	reveil = new Reveil(req.getParameter("id"));
            ofy().save().entity(reveil).now();
        }
        String adresse = reveil.getUtilisateur();
        Utilisateur utilisateur = null;
        if(adresse != null){
        	utilisateur = ofy().load().type(Utilisateur.class).id(reveil.getUtilisateur()).now();
        	utilisateur.setHeure_reveil();
        	System.out.println(utilisateur.getHeure_reveil());

            req.setAttribute("heure_reveil", utilisateur.getHeure_reveil());

        }
        else{
        	utilisateur.setHeure_reveil();
        	req.setAttribute("erreur", "aucune addresse gmail liée avec cet appareil");
        	req.setAttribute("id", reveil.getIdentifiant());

        }
        this.getServletContext().getRequestDispatcher( "/Reveil.jsp" ).forward( req, resp );	
	}
}
