

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.JeuVideo;
import beans.Inscription;
/**
 * Servlet implementation class TestJeu
 */
public class TestJeu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestJeu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Servlet de test à supprimer: création de comptes et de jeux et ajout en base + à liste jeux possédés
		
		/*
		Inscription comptePrincipal=new Inscription("clem","clementjourdain14@gmail.com","pass","jourdain","clement","17/03/1995","France","0102030405");
		Inscription compteSecondaire=new Inscription("jr","clem.50basket@gmail.com","autrepass","jourd","clem","01/01/0000","Fr","0000000000");
		Inscription compteTest=new Inscription("pseudo","mail@test","mdp","nom","prenom","date","pays","tel");
		JeuVideo monJeu= new JeuVideo("2k","sport",14.5);
		JeuVideo unAutreJeu=new JeuVideo("gta4","aventure",18);
		JeuVideo unJeu=new JeuVideo("callofduty","guerre",17);
		JeuVideo ceJeu=new JeuVideo("2k","sport",14.5);
		
		List<JeuVideo> jeuxComptePrincipal=new ArrayList<JeuVideo>();
		jeuxComptePrincipal.add(monJeu);
		jeuxComptePrincipal.add(unAutreJeu);
		jeuxComptePrincipal.add(ceJeu);
		
		List<JeuVideo> jeuxCompteSecondaire=new ArrayList<JeuVideo>();
		jeuxCompteSecondaire.add(unJeu);
		jeuxCompteSecondaire.add(monJeu);
		
		comptePrincipal.setListeJeuxPossedes(jeuxComptePrincipal);
		compteSecondaire.setListeJeuxPossedes(jeuxCompteSecondaire);
		
		
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		System.out.println(comptePrincipal);
		System.out.println(compteSecondaire);
		//session.save(comptePrincipal);
		//session.save(compteSecondaire);
		//session.save(compteTest);
		session.getTransaction().commit();
		session.close();
		response.getWriter().append(comptePrincipal.toString());
		*/
		
		// Test de lecture de liste d'utilisateur pour un jeu
		
		JeuVideo monJeu= new JeuVideo();
		int id=3;
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		/*
		monJeu=session.get(JeuVideo.class, id);
		//System.out.println(monJeu.getListeUtilisateurs().get(0));
		for(int i=0;i<monJeu.getListeUtilisateurs().size();i++)
		{
			System.out.println(monJeu.getListeUtilisateurs().get(i));
		}
		*/
		session.getTransaction().commit();
		session.close();
		
		
		
		//response.getWriter().append("ceci est un test");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
