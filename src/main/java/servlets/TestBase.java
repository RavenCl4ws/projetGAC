

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import beans.Inscription;
import com.google.gson.*;


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
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		//Ouverture session
		Session session = sessionFactory.openSession();
		
		Inscription clement = new Inscription("clem", "clementjourdain14@gmail.com","pass", "jourdain", "clement", "17/03/1995","france", "0102030405");
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String motPasse = request.getParameter("motPasse");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String dateNaissance = request.getParameter("dateNaissance");
		String pays = request.getParameter("pays");
		String numeroTel = request.getParameter("numeroTel");
		
		
		Inscription monCompte=new Inscription(pseudo,email,motPasse,nom,prenom,dateNaissance,pays,numeroTel);
		
		
		String JSONretour="";
		
		session.save(monCompte);
		Inscription compte;
		int id;
		try {
			id=monCompte.getId();
			compte = session.get(Inscription.class,id);
			JSONretour = new Gson().toJson(compte.toString());
		}
		catch (Exception e)
		{
			JSONretour = new Gson().toJson("Erreur création compte");
		}
		session.close();
		//request.setAttribute("json",JSONretour);
		System.out.println(JSONretour);
//		response.getWriter().append(JSONretour);
		response.sendRedirect("http://localhost:4200/accueil");
//		this.getServletContext().getRequestDispatcher("http://localhost:4200/accueil").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
