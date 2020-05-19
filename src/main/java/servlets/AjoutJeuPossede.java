

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import beans.Inscription;
import beans.JeuVideo;
/**
 * Servlet implementation class LectureBase
 */
public class AjoutJeuPossede extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutJeuPossede() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		
		//Instanciation d'un compte et d'un jeu video
		Inscription monCompte=new Inscription();
		JeuVideo monJeu= new JeuVideo();
		
		//Ouverture de session
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		//Démarrage transaction (nécessaire pour écrire en base)
		session.beginTransaction();
		
		//A Changer(communication angular): recuperaton de l'id du compte et du jeu
		int idUser = 2;
		int idJeu=11;
		
		//Lecture en bdd des infos des id + enregistrement dans les instances 
		monCompte = session.get(Inscription.class,idUser);
		monJeu=session.get(JeuVideo.class, idJeu);
	
		//Ajout du jeu dans la liste des jeux possédés
		monCompte.getListeJeuxPossedes().add(monJeu);
	

		// A enlever à la fin des tests
		System.out.println(monCompte.getListeJeuxPossedes());
	
		
	
		//Fin de transaction et fermeture de session
		session.getTransaction().commit();
		session.close();
		
		//A modifier pour renvoyer la bonne info à angular
		response.getWriter().append(monCompte.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
