package testjsp;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

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
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        Utilisateur utilisateur = ofy().load().type(Utilisateur.class).id(user.getEmail()).now();
        

        if(utilisateur == null){
        	utilisateur = new Utilisateur(user.getEmail());
        }
        resp.setContentType("text/html");
		resp.getWriter().println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
				+ "<html>"
				+ "<head>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
				+ "	<title>Données du compte</title>"
				+ "</head>"
				+ "<body> ");		
		resp.getWriter().println(utilisateur.getTrajet().getDetail_Trajet());
		resp.getWriter().println("<form action=\"/Compte\" method=\"GET\">");
		resp.getWriter().println("<input type=\"submit\" value=\"Modifier Compte\">");
		resp.getWriter().println("</form>");
		resp.getWriter().println(" </body></html> ");		
	}
}
