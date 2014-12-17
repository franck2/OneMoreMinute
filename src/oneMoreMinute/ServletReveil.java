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
		ObjectifyService.register(Trajet.class);
		ObjectifyService.register(Calendrier.class);
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
        Utilisateur utilisateur = ofy().load().type(Utilisateur.class).id(reveil.getUtilisateur()).now();
        
        
        if(utilisateur == null){
        	req.setAttribute("erreur", "aucune addresse gmail liée avec cet appareil");
        	req.setAttribute("id", reveil.getIdentifiant());
        }
        req.setAttribute("client", utilisateur);
        this.getServletContext().getRequestDispatcher( "/Accueil.jsp" ).forward( req, resp );	
	}
}
