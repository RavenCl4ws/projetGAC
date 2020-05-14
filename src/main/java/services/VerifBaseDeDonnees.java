package services;

import java.util.List;


import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import beans.Inscription;

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
}
