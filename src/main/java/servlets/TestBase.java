


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;



import beans.Inscription;


/**
 * Servlet implementation class TestBase
 */
public class TestBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message="";
		
		//recuperation des infos du formaulaire Angular
		String requestData = request.getReader().lines().collect(Collectors.joining());
		//System.out.println(requestData);
		
		//Les transformer en JSON pour pouvoir extraire les infos plus facilement
		JsonObject objetRecu = new JsonParser().parse(requestData).getAsJsonObject();
		
		//Ouverture session
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//Enregistrement des infos dans des variables locales
		String pseudo = objetRecu.get("pseudo").getAsString();
		String email = objetRecu.get("email").getAsString();
		String motPasse = objetRecu.get("motPasse").getAsString();
		String nom = objetRecu.get("nom").getAsString();
		String prenom = objetRecu.get("prenom").getAsString();
		String dateNaissance = objetRecu.get("dateNaissance").getAsString();
		String pays = objetRecu.get("pays").getAsString();
		String numeroTel = objetRecu.get("numeroTel").getAsString();
		
//		System.out.println(pseudo);
//		System.out.println(email);
		
		//Differents tests sur l'existence en base de l'utilisateur
		boolean succes=false;
		
		
		if(pseudo!=null && email!=null && motPasse!=null)
		{
			boolean checkPseudo=services.VerifBaseDeDonnees.verifPseudoInscription(pseudo,session);
			if (checkPseudo) {
				message="Le pseudo existe déjà";
//			System.out.println(message);
			}
			boolean checkMail=services.VerifBaseDeDonnees.verifMailInscription(email,session);
			if (checkMail) {
				message="L'email existe déjà";
//			System.out.println(message);
			}
			if (!checkPseudo && !checkMail)
			{	
				//Enregistrement des infos dans la BDD
				Inscription monCompte=new Inscription(pseudo,email,motPasse,nom,prenom,dateNaissance,pays,numeroTel);
				session.save(monCompte);
				checkPseudo=services.VerifBaseDeDonnees.verifPseudoInscription(pseudo,session);
				
				//Verification que l'enregistrement s'est bien effectué
				if(checkPseudo)
				{	
					message="L'inscrition a réussi";
//					System.out.println(message);
					succes=true;
				}	
			}
		}
		
			session.close();
			
			//Retour du message à Angular
			response.getWriter().append(message);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
