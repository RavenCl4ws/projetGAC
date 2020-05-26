

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import javax.json.JsonObject;

import beans.Inscription;
import beans.JeuVideo;

/**
 * Servlet implementation class ConnexionUtilisateur
 */
public class ConnexionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//A faire: récupérer infos angular pseudo+mdp : ok
		//Aller requeter en bdd pour savoir si existe : ok
		//si non: renvoyer un message si oui renvoyer infos utilisateur : ok
		
		// Récupération des infos depuis Angular
		
		String requestData = request.getReader().lines().collect(Collectors.joining());
		//System.out.println(requestData);
		
		//Les transformer en JSON pour pouvoir extraire les infos plus facilement
		JsonObject objetRecu = new JsonParser().parse(requestData).getAsJsonObject();
		
		String pseudo = objetRecu.get("pseudo").getAsString();
		String motPasse = objetRecu.get("motPasse").getAsString();
		
		//String pseudo="jr";
		//String motPasse="pass";
		
		String jsonInString;
		JsonObject jsonRetour;
		
		
		//Instanciation compte
		Inscription monCompte=new Inscription();
		
		//Ouverture session
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
				
		//Début Transaction
		session.beginTransaction();
		
		//Vérif existence du compte
		
		try {
		monCompte=services.VerifBaseDeDonnees.verifUtilisateur(pseudo, motPasse, session);
		System.out.println(monCompte);
		
		//Objectif: envoyer les données utilisateur en JSON
		 
			int id=monCompte.getId();
			String x= Integer.toString(id);
			String pseudoRecup=monCompte.getPseudo();
			
//			JsonObject jo=Json.createObjectBuilder();
					 
			
			
		    jsonInString = "{\"id\":\""+x+"\",\"pseudo\":\""+pseudoRecup+"\"}";
//		    jsonInString = new Gson().toJson("{id:"+x+",pseudo:"+pseudoRecup+"}");

	
		}
		catch(Exception e) {
		
			jsonInString=new Gson().toJson("texteErreur");
		}
		
		System.out.println(jsonInString);
		System.out.println(jsonInString.getClass().getName());
		
		session.getTransaction().commit();
		session.close();
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonInString);
		out.flush();
		
//		response.getWriter().append(jsonInString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
