

import java.io.IOException;
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
 * Servlet implementation class NoteUtilisateur
 */
public class AjoutNoteUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutNoteUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Ce servlet récupère les infos d'angular(note,nomjeu,iduser) et modifie la note donnée par l'utilisateur pour le jeu spécifié en BDD
		// Retravailler sur le retour vers Angular
		
		// Récupération id et note utilisateur via Angular
		
		//String idRentre;
		//String noteRentree;
		//String nomJeu;
		
		
		// double note=Double.parseDouble(noteRentree);
		//int idUser=Integer.parseInt(idRentre);
		
		double note=-1;
		String nomJeu="call of duty";
		int idUser=2;
		int idJeu=0;      //Pas envoyé par angular car présent uniquement dans la bdd
		
		String messageRetour="";
		
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
		
				if(idJeu!=0)
				{
				monJeu=session.get(JeuVideo.class, idJeu);
				monJeu.setNote(note);
				messageRetour="la note a été modifiée";
				}
				
				else
					messageRetour="le jeu n'est pas dans votre liste de jeux";
				
				
				//Fin de transaction et fermeture de session
				session.getTransaction().commit();
				session.close();
				
				
				System.out.println(messageRetour);
				
				for (JeuVideo j : monCompte.getListeJeuxPossedes())
				{
					System.out.println(j.toString());
				}
				
				//A modifier pour renvoyer la bonne info à angular: pour le moment affiche les éléments de la liste de jeux possédés sur servlet
				for (JeuVideo j : monCompte.getListeJeuxPossedes())
				{
					response.getWriter().append(j.toString());
					response.getWriter().append("<br>");
				}
				
				response.getWriter().append(messageRetour);
				
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
