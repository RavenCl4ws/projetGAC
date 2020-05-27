

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
import beans.JeuVideo;

/**
 * Servlet implementation class LecturenoteUtilisateur
 */
public class LectureNoteUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureNoteUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Infos à récupérer via Angular
		
		
		
		String requestData = request.getReader().lines().collect(Collectors.joining());
		//System.out.println(requestData);
		
		//Les transformer en JSON pour pouvoir extraire les infos plus facilement
		JsonObject objetRecu = new JsonParser().parse(requestData).getAsJsonObject();
		
		String nomJeu = objetRecu.get("nomJeu").getAsString();
		String id = objetRecu.get("userId").getAsString();
		int idUser=Integer.parseInt(id);
		
		
		/* // Pour les tests
		String nomJeu="call of duty";
		int idUser=1;
		*/
		
		int idJeu=0;
		String jsonRetour="";
		
		
		
		double note;
		//Instanciation
		Inscription monCompte=new Inscription();
		JeuVideo monJeu=new JeuVideo();
		
		//Ouverture session
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//Début Transaction
		session.beginTransaction();
		
		monCompte=session.get(Inscription.class, idUser);
		
		for (int i=0;i<monCompte.getListeJeuxPossedes().size();i++)
		{
			if(monCompte.getListeJeuxPossedes().get(i).getNomJeu().equals(nomJeu))
				idJeu=monCompte.getListeJeuxPossedes().get(i).getIdJeu();
		}
		
		
		monJeu=session.get(JeuVideo.class, idJeu);
		note=monJeu.getNote();
		
		//Fin de transaction et fermeture de session
		session.getTransaction().commit();
		session.close();
		
		System.out.println(note);
		
		jsonRetour = "{\"note\":\""+note+"\"}";
		
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
