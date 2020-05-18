


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * Servlet implementation class TestBase
 */
public class TestBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

//		String pseudo =  request.getParameter("pseudo");
//		String email = request.getParameter("email");
//		String motPasse = request.getParameter("motPasse");
//		String nom = request.getParameter("nom");
//		String prenom = request.getParameter("prenom");
//		String dateNaissance = request.getParameter("dateNaissance");
//		String pays = request.getParameter("pays");
//System.out.println("pseudo:"+pseudo);
//		String numeroTel = request.getParameter("numeroTel");
//		System.out.println("email:"+email);
	    	BufferedReader in = request.getReader();
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
			System.out.println(content);
			String JSONretour = new Gson().toJson(content.toString());
			System.out.println(JSONretour);
			JsonParser parser = new JsonParser();
//			JsonObject realjsonretour = parser.parse(JSONretour);
//			String pseudo = (String) realjsonretour.get("pseudo");
//			String email = JSONretour.getParameter("email").toString();
//			String motPasse = JSONretour.getParameter("motPasse");
//			String nom = JSONretour.getParameter("nom");
//			String prenom = JSONretour.getParameter("prenom");
//			String dateNaissance = JSONretour.getParameter("dateNaissance");
//			String pays = JSONretour.getParameter("pays");
//			String numeroTel = JSONretour.getParameter("numeroTel");
//			
//	    
//			System.out.println(content);		
//			response.getWriter().append(content);
//	    
//	 
//	    String user = (String) joUser.get("name");
//	 
//	    response.setContentType("text/html");
//	    PrintWriter out = response.getWriter();
//	    out.write("A new user " + user + " has been created.");
//	    out.flush();
//	    out.close();
//	  }
//	
//		Configuration config = new Configuration();
//		SessionFactory sessionFactory = config.configure().buildSessionFactory();
//		//Ouverture session
//		Session session = sessionFactory.openSession();
//		
//		String pseudo = request.getParameter("pseudo");
//		String email = request.getParameter("email");
//		String motPasse = request.getParameter("motPasse");
//		String nom = request.getParameter("nom");
//		String prenom = request.getParameter("prenom");
//		String dateNaissance = request.getParameter("dateNaissance");
//		String pays = request.getParameter("pays");
//		String numeroTel = request.getParameter("numeroTel");
//		
//		boolean succes=false;
//		String message="";
//		
//		if(pseudo!=null && email!=null && motPasse!=null)
//		{
//			boolean checkPseudo=services.VerifBaseDeDonnees.verifPseudoInscription(pseudo,session);
//			if (checkPseudo)
//				message="Le pseudo existe déjà ";
//			boolean checkMail=services.VerifBaseDeDonnees.verifMailInscription(email,session);
//			if (checkMail)
//				message="L'email existe déjà ";
//			if (!checkPseudo && !checkMail)
//			{
//				Inscription monCompte=new Inscription(pseudo,email,motPasse,nom,prenom,dateNaissance,pays,numeroTel);
//				session.save(monCompte);
//				checkPseudo=services.VerifBaseDeDonnees.verifPseudoInscription(pseudo,session);
//				if(checkPseudo)
//				{
//					message="L'inscrition a réussi";
//					succes=true;
//				}	
//			}
//		}
//			session.close();
//			
//			System.out.println(message);	
//			String JSONretour = new Gson().toJson(message);
//			request.setAttribute("json",JSONretour);
//			if (succes) {
//				response.sendRedirect("http://localhost:4200/accueil?message="+message);
//			}
//			else {
//				response.sendRedirect("http://localhost:4200/test?message="+message);
//			}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
