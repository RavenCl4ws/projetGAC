

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		
		//Version 2: on ne vérifie plus si le jeu est dans la base car on doit l'ajouter pour chaque utiisateur
		
	// Récupération des infos depuis Angular
		
		String requestData = request.getReader().lines().collect(Collectors.joining());
		//System.out.println(requestData);
		
		//Les transformer en JSON pour pouvoir extraire les infos plus facilement
		JsonObject objetRecu = new JsonParser().parse(requestData).getAsJsonObject();
		
		String nomJeu = objetRecu.get("nomJeu").getAsString();
		String genrePrincipal = objetRecu.get("genre").getAsString();
		String idUtilisateur = objetRecu.get("userId").getAsString();
		int idUser=Integer.parseInt(idUtilisateur);
		
		String messageCreationJeu="";
		// Inutilisé pour one to many: String messageAjoutListeJeuxPossedes="";
		//Récupération paramètres depuis Angular??
		
		//Pour les tests
		//String nomJeu="call of duty";
		//String genrePrincipal="guerre";
		//int idUser=2;
		
		double note=-1;
		
		
		//Instanciation User 
		Inscription monCompte=new Inscription();
		// inutilisé pour one to many: JeuVideo monJeu= new JeuVideo();
		
		//Ouverture session
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		//Début Transaction
		session.beginTransaction();
		
		/* Version en manytomany
		
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
		
					JeuVideo jeuAjoute= new JeuVideo(nomJeu,genrePrincipal,note);
					session.save(jeuAjoute);
					boolean checkJeu=services.VerifBaseDeDonnees.verifJeu(nomJeu,genrePrincipal,session);
					if (checkJeu)
					{
						
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
			
			Fin version many to many */
		
			//début version one to many
		
			boolean verifJeuPossede=false;
			monCompte=session.get(Inscription.class, idUser);
			for (int i=0;i<monCompte.getListeJeuxPossedes().size();i++)
			{
				if(monCompte.getListeJeuxPossedes().get(i).getNomJeu().equals(nomJeu))
					verifJeuPossede=true;
			}
			if(verifJeuPossede)
				messageCreationJeu="le jeu est déjà dans votre liste";
			if(!verifJeuPossede)
			{
				JeuVideo jeuAjoute= new JeuVideo(nomJeu,genrePrincipal,note);
				session.save(jeuAjoute);
				monCompte.getListeJeuxPossedes().add(jeuAjoute);
				messageCreationJeu="le jeu a été ajouté à votre liste";
			}
			
			System.out.println(messageCreationJeu);
			
			//A enlever après les tests
			for (JeuVideo j : monCompte.getListeJeuxPossedes())
			{
				System.out.println(j.toString());
			}
			
			
			// Fin one to many
			
				//Fin de transaction et fermeture de session
				session.getTransaction().commit();
				session.close();
				
				response.getWriter().append(messageCreationJeu);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
