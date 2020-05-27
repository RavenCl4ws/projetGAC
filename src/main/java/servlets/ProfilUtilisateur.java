

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.Inscription;

/**
 * Servlet implementation class ProfilUtilisateur
 */
public class ProfilUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* // Infos Angular 
		
		String requestData = request.getReader().lines().collect(Collectors.joining());
		//System.out.println(requestData);
		
		//Les transformer en JSON pour pouvoir extraire les infos plus facilement
		JsonObject objetRecu = new JsonParser().parse(requestData).getAsJsonObject();
		
		String Id = objetRecu.get("userId").getAsString();
		int idUser=Integer.parseInt(Id);
		*/
		
		// Test
		int idUser=1;
		
		//Instanciation
		Inscription monCompte=new Inscription();
		
		String jsonRetour;
		
		//Ouverture session
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
						
		//Début Transaction
		session.beginTransaction();
		
		monCompte=session.get(Inscription.class, idUser);
		
		 jsonRetour = "{\"id\":\""+monCompte.getId()+"\",\"pseudo\":\""+monCompte.getPseudo() + "\",\"mail\":\""+monCompte.getMail()+"\",\"motPasse\":\""+monCompte.getMotPasse()
	 		+ "\",\"nom\":\""+monCompte.getNom()+ "\",\"prenom\":\""+monCompte.getPrenom()+ "\",\"dateNaissance\":\""+monCompte.getDateNaissance()
	 		+ "\",\"pays\":\""+monCompte.getPays()+ "\",\"numeroTel\":\""+monCompte.getNumeroTel()+ "\",\"listeJeux\":\""+monCompte.getListeJeuxPossedes()+"\"}";
		
		
		//Fin Transaction et fermeture session
		session.getTransaction().commit();
		session.close();
		
		
		
		//jsonRetour=new Gson().toJson(monCompte.toString());
		
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
