

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;



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
		
		//A faire: récupérer infos angular mail+mdp ou pseudo+mdp
		//Aller requeter en bdd pour savoir si existe
		//si non: renvoyer un message si oui renvoyer infos utilisateur
		
		// Récupération des infos depuis Angular
		String pseudo="jr";
		String motPasse="pass";
		
		String jsonInString="";
		
		
		
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
		 
		    jsonInString = new Gson().toJson(monCompte);
	
		}
		catch(Exception e) {
		
			jsonInString=new Gson().toJson("le compte n'existe pas");
		}
		
		session.getTransaction().commit();
		session.close();
		
		response.getWriter().append(jsonInString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
