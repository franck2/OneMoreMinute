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
	
        Reveil reveil = ofy().load().type(Reveil.class).id(req.getParameter("id")).now();

        if(reveil == null){
        	reveil = new Reveil(req.getParameter("id"));
            ofy().save().entity(reveil).now();
        }
    	req.setAttribute("id", reveil.getIdentifiant());

        String adresse = reveil.getUtilisateur();
        Utilisateur utilisateur = null;

        if(adresse != null){
        	utilisateur = ofy().load().type(Utilisateur.class).id(reveil.getUtilisateur()).now();
            if(req.getParameter("maj") !=null && req.getParameter("maj").equals("true")){
            	utilisateur.maj(true);
                ofy().save().entity(utilisateur).now();
            }
            req.setAttribute("heure_reveil", utilisateur.getDate_reveil());
            req.setAttribute("musique", utilisateur.getMusique());
        }
        else{
        	req.setAttribute("erreur", "Aucune addresse gmail liée avec cet appareil");
        }

        this.getServletContext().getRequestDispatcher( "/Reveil.jsp" ).forward( req, resp );	
	}
}
