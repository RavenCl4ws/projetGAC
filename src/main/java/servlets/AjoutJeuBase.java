

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.JeuVideo;

/**
 * Servlet implementation class AjoutJeuBase
 */
public class AjoutJeuBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutJeuBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				
		
				String message="";
				//Ouverture session
				Configuration config = new Configuration();
				SessionFactory sessionFactory = config.configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				
				//Début Transaction
				session.beginTransaction();
				
				//Récupération paramètres depuis Angular??
				String nomJeu="nomTest";
				String genrePrincipal="genreTest";
				double noteMoyenne=10.00;
				
				//Vérif existence 
				boolean succes=false;
				boolean checkJeu=services.VerifBaseDeDonnees.verifJeu(nomJeu,genrePrincipal,session);
				//Existe deja
				if (checkJeu)
					message="Le jeu existe déjà";
				
				//Mise en base
				if (!checkJeu)
				{
					JeuVideo jeuAjoute= new JeuVideo(nomJeu,genrePrincipal,noteMoyenne);
					session.save(jeuAjoute);
					checkJeu=services.VerifBaseDeDonnees.verifJeu(nomJeu,genrePrincipal,session);
					if (checkJeu)
					{
						succes=true;
						message="Le jeu a été ajouté à la BDD";
					}
				}

				//Fin de transaction et fermeture de session
				session.getTransaction().commit();
				session.close();		
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
