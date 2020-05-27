

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.Inscription;
import beans.JeuVideo;


/**
 * Servlet implementation class CalculNoteMoyenneJeu
 */
public class CalculNoteMoyenneJeu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculNoteMoyenneJeu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Objectif: récupérer un nom de jeu depuis angular et calculer la note moyenne donnée à ce jeu par les utilisateurs 
		
		
		// Récupération depuis angular
		
		 
		String requestData = request.getReader().lines().collect(Collectors.joining());
		
		
		//Les transformer en JSON pour pouvoir extraire les infos plus facilement
		JsonObject objetRecu = new JsonParser().parse(requestData).getAsJsonObject();
		
		String nomJeu = objetRecu.get("nomJeu").getAsString();
		
		
			
		/*//Pour les tests
		String nomJeu="call of duty";
		*/
		
		double somme=0;
		double noteMoyenne=0;
		
		String jsonRetour="";
		
		//Ouverture session
				Configuration config = new Configuration();
				SessionFactory sessionFactory = config.configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				
		//Début Transaction
				session.beginTransaction();
		
				Query query=session.createQuery("from JeuVideo where nomJeu=:nom AND note>=0");
				query.setParameter("nom", nomJeu);
				List <JeuVideo> resultat=query.list();
				
				for (int i=0;i<resultat.size();i++)
				{
					somme+=resultat.get(i).getNote();
				}
				System.out.println(somme);
				noteMoyenne=somme/resultat.size();
				System.out.println(noteMoyenne);
		
				jsonRetour="{\"noteMoyenne\":\""+noteMoyenne+"\"}";
				
				System.out.println(jsonRetour);
				response.getWriter().append(jsonRetour);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
