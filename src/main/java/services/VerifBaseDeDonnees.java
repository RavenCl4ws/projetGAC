package services;

import java.util.List;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import beans.Inscription;
import beans.JeuVideo;
public class VerifBaseDeDonnees {

	
	
	
	
	
	
	public static boolean verifPseudoInscription(String pseudo,Session session){
		
			boolean checkPseudo=false;
		{
			
			Query query=session.createQuery("from Inscription where pseudo=:monpseudo");
			query.setParameter("monpseudo", pseudo);
			List <Inscription> resultat=query.list();
			if(resultat.size()!=0)
			{
				checkPseudo=true;
			}
			
			return checkPseudo;
		}
	}
	

	
	
	
	public static boolean verifMailInscription(String mail,Session session){
		
			boolean checkMail=false;
		{
			
			Query query=session.createQuery("from Inscription where mail=:monMail");
			query.setParameter("monMail", mail);
			List <Inscription> resultat=query.list();
			if(resultat.size()!=0)
			{
				checkMail=true;
			}
			
			return checkMail;
		}
	}
	
public static boolean verifJeu(String nomJeu,String genrePrincipal,Session session){
		
		boolean checkJeu=false;
	{
		
		Query query=session.createQuery("from JeuVideo where nomJeu=:nom AND genrePrincipal=:genre");
		query.setParameter("nom", nomJeu);
		query.setParameter("genre", genrePrincipal);
		List <JeuVideo> resultat=query.list();
		if(resultat.size()!=0)
		{
			checkJeu=true;
		}
		
		return checkJeu;
	}
}


public static boolean verifListeJeuxPossedes(JeuVideo monJeu,Inscription monCompte)
{
	boolean checkJeuPossede=false;
	int idJeu=monJeu.getIdJeu();
	//Vérif si jeu déjà dans liste jeux de utilisateur
	for (int i=0; i<monCompte.getListeJeuxPossedes().size();i++)
	{
		if (monCompte.getListeJeuxPossedes().get(i).getIdJeu()==idJeu)
			checkJeuPossede=true;
	}
	return checkJeuPossede;
}




public static Inscription verifUtilisateur(String pseudo,String motPasse,Session session)
{
	Inscription compte=new Inscription();
	Query<Inscription> query=session.createQuery("from Inscription where pseudo=:pseudo AND mot_passe=:motPasse");
	query.setParameter("pseudo", pseudo);
	query.setParameter("motPasse", motPasse);
		compte=query.uniqueResult();
	
	return compte;
	
}


public static double arrondiNDecimales(double x, int n) 
{ 
	double pow = Math.pow(10, n); 
	return (Math.floor(x * pow)) / pow;
}

}
