

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.Inscription;
import beans.JeuVideo;

/**
 * Servlet implementation class SuppressionJeuListeJeuxPossedes
 */
public class SuppressionJeuListeJeuxPossedes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionJeuListeJeuxPossedes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Récupération des infos depuis Angular
		
				String requestData = request.getReader().lines().collect(Collectors.joining());
				//System.out.println(requestData);
				
				//Les transformer en JSON pour pouvoir extraire les infos plus facilement
				JsonObject objetRecu = new JsonParser().parse(requestData).getAsJsonObject();
				
				String nomJeu = objetRecu.get("nomJeu").getAsString();
				String genrePrincipal = objetRecu.get("genre").getAsString();
				String idUtilisateur = objetRecu.get("userId").getAsString();
				int idUser=Integer.parseInt(idUtilisateur);
		
				/* //Pour les tests
				String nomJeu="pcm";
				String genrePrincipal="sport"; 
				int idUser=1;
				*/
				
				String messageSuppressionJeu="";
				int idJeu=0;
				//Instanciation User 
				Inscription monCompte=new Inscription();
				
				//Ouverture session
				Configuration config = new Configuration();
				SessionFactory sessionFactory = config.configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				
				//Début Transaction
				session.beginTransaction();
				
				boolean verifJeuPossede=false;
				monCompte=session.get(Inscription.class, idUser);
				for (int i=0;i<monCompte.getListeJeuxPossedes().size();i++)
				{
					if(monCompte.getListeJeuxPossedes().get(i).getNomJeu().equals(nomJeu))
						{
						verifJeuPossede=true;
						idJeu=monCompte.getListeJeuxPossedes().get(i).getIdJeu();
						}
				}
				if(!verifJeuPossede)
					messageSuppressionJeu="le jeu n'est pas dans votre liste";
				if(verifJeuPossede)
				{
					JeuVideo jeuSupprime=session.get(JeuVideo.class, idJeu);
					session.delete(jeuSupprime);
					monCompte.getListeJeuxPossedes().remove(jeuSupprime);
					messageSuppressionJeu="le jeu a été supprimé de votre liste";
				}
				
				//Fin de transaction et fermeture de session
				session.getTransaction().commit();
				session.close();
				
				System.out.println(messageSuppressionJeu);
				for (JeuVideo j: monCompte.getListeJeuxPossedes())
				{
					System.out.println(j.toString());
				}
				
		response.getWriter().append(messageSuppressionJeu);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
