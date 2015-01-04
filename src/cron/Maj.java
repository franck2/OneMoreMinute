package cron;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import oneMoreMinute.Calendrier;
import oneMoreMinute.Trajet;
import oneMoreMinute.Utilisateur;

@SuppressWarnings("serial")
public class Maj extends HttpServlet {

	static{
		ObjectifyService.register(Utilisateur.class);
		ObjectifyService.register(Trajet.class);
		ObjectifyService.register(Calendrier.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		List<Utilisateur> ut = ofy().load().type(Utilisateur.class).list();
		for(int i = 0; i<ut.size(); i++){
			ut.get(i).maj(false);
            ofy().save().entity(ut.get(i));

		}

	}
}
