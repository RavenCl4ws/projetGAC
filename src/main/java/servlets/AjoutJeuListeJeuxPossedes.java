

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import beans.Inscription;
import beans.JeuVideo;


/**
 * Servlet implementation class AjoutJeuListeJeuxPossedes
 */
public class AjoutJeuListeJeuxPossedes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutJeuListeJeuxPossedes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Ce servlet doit être adapté pour recevoir un nom de jeu, un genre pour ce jeu et un id d'utilisateur
		// Il vérifie si le jeu existe dans la BDD JeuVideo, si il n'existe pas il l'ajoute.
		// Ensuite, il vérifie si le jeu existe dans la liste de jeux possédés par l'utilisateur, si il n'existe pas il l'ajoute
		// Décider du renvoi à faire à Angular: pour le moment, des messages sont affichés dans la console jee
		
		String messageCreationJeu="";
		String messageAjoutListeJeuxPossedes="";
		//Récupération paramètres depuis Angular??
		String nomJeu="pcm";
		String genrePrincipal="autregenre";
		double noteMoyenne=10.00;
		int idUser=1;
		
		//Instanciation User et Jeu à récupérer 
		Inscription monCompte=new Inscription();
		JeuVideo monJeu= new JeuVideo();
		
		//Ouverture session
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//Début Transaction
		session.beginTransaction();
		
		//Vérif existence 
				boolean succes=false;
				boolean checkJeu=services.VerifBaseDeDonnees.verifJeu(nomJeu,genrePrincipal,session);
				//Existe deja
				if (checkJeu)
					{
					messageCreationJeu="Le jeu existe déjà";
					}
				
				//Mise en base
				if (!checkJeu)
				{
					JeuVideo jeuAjoute= new JeuVideo(nomJeu,genrePrincipal,noteMoyenne);
					session.save(jeuAjoute);
					checkJeu=services.VerifBaseDeDonnees.verifJeu(nomJeu,genrePrincipal,session);
					if (checkJeu)
					{
						succes=true;
						messageCreationJeu="Le jeu a été ajouté à la BDD";
					}
				}
		
				//A enlever à la fin des tests
				System.out.println(messageCreationJeu);
			
				
				//Récupération du jeu depuis la BDD
				Query<JeuVideo> query=session.createQuery("from JeuVideo where nomJeu=:nom AND genrePrincipal=:genre");
				query.setParameter("nom", nomJeu);
				query.setParameter("genre", genrePrincipal);
				//Retour unique plutot que sous forme de liste
				monJeu = query.uniqueResult();
				monCompte=session.get(Inscription.class, idUser);
				
				boolean checkJeuPossede=services.VerifBaseDeDonnees.verifListeJeuxPossedes(monJeu,monCompte);
				
				if (checkJeuPossede)
					messageAjoutListeJeuxPossedes="Le jeu est déjà dans votre liste de jeux possédés";
				if(!checkJeuPossede)
				{
					//Ajout du jeu dans la liste des jeux possédés
					monCompte.getListeJeuxPossedes().add(monJeu);
					messageAjoutListeJeuxPossedes="Le jeu a été ajouté à votre liste de jeux possédés";
				}
				
				// A enlever à la fin des tests
				System.out.println(messageAjoutListeJeuxPossedes);
				
				// A enlever à la fin des tests
				System.out.println(monCompte.getListeJeuxPossedes());
			
				//infos utilisateur
				System.out.println(monCompte.toString());
			
				//Fin de transaction et fermeture de session
				session.getTransaction().commit();
				session.close();
				
				//A modifier pour renvoyer la bonne info à angular: pour le moment affiche les éléments de la liste de jeux possédés sur servlet
				for (JeuVideo j : monCompte.getListeJeuxPossedes())
				{
					response.getWriter().append(j.toString());
					response.getWriter().append("<br>");
				}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
