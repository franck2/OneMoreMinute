package testjsp;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class Accueil extends HttpServlet {
	
	
	static{
		ObjectifyService.register(Utilisateur.class);
		ObjectifyService.register(Trajet.class);
		ObjectifyService.register(Calendrier.class);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        Utilisateur utilisateur = ofy().load().type(Utilisateur.class).id(user.getEmail()).now();
        if(utilisateur == null){
        	utilisateur = new Utilisateur(user.getEmail());
            ofy().save().entity(utilisateur).now();
        }
        req.setAttribute("client", utilisateur);
        this.getServletContext().getRequestDispatcher( "/Accueil.jsp" ).forward( req, resp );	
	}
}
