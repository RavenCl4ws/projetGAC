

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


import beans.Inscription;

/**
 * Servlet implementation class TestConnexion
 */
public class TestInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		//Ouverture session
		Session session = sessionFactory.openSession();
		
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String motPasse = request.getParameter("motPasse");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String dateNaissance = request.getParameter("dateNaissance");
		String pays = request.getParameter("pays");
		String numeroTel = request.getParameter("numeroTel");
		
		boolean succes=false;
		String message="";
		
		if(pseudo!=null && email!=null && motPasse!=null)
		{
			boolean checkPseudo=services.VerifBaseDeDonnees.verifPseudoInscription(pseudo,session);
			if (checkPseudo)
				message="Le pseudo existe déjà";
			boolean checkMail=services.VerifBaseDeDonnees.verifMailInscription(email,session);
			if (checkMail)
				message="L'email existe déjà";
			if (!checkPseudo && !checkMail)
			{
				Inscription monCompte=new Inscription(pseudo,email,motPasse,nom,prenom,dateNaissance,pays,numeroTel);
				session.save(monCompte);
				checkPseudo=services.VerifBaseDeDonnees.verifPseudoInscription(pseudo,session);
				if(checkPseudo)
				{
					message="L'inscrition a réussi";
					succes=true;
				}	
			}
		}
			session.close();
			
			System.out.println(message);	
			String JSONretour = new Gson().toJson(message);
			request.setAttribute("json",JSONretour);
			if (succes) {
				response.sendRedirect("http://localhost:4200/accueil?message="+message);
			}
			else {
				response.sendRedirect("http://localhost:4200/test?message="+message);
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
