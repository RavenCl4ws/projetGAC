

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class Connexion
 */
public class BaseConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String usernameEntered=request.getParameter("username");
		String passwordEntered=request.getParameter("password");
		String passwordEncrypted="";
		Configuration config = new Configuration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		// Ouverture session
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		//===========Opérations================
		
		session.getTransaction().commit();
		// Fermeture session
		session.close();
		try {
			 passwordEncrypted=new String(encrypt(passwordEntered));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int idUser=0;
		if(usernameEntered.contains("@")) {
			idUser=verifyMail(usernameEntered,passwordEncrypted,session);
			
		}else {
			idUser=verifyUsername(usernameEntered, passwordEncrypted, session);
		}
		if(idUser>0) {
			session.setAttribute("idUser", idUser);
			this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
		}else {this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);}
		
//		if(request.getParameter("username").equals("guillaume") && request.getParameter("password").equals("123") ) {
//			System.out.println("connexion reussie");
//			this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
//		}
//		else {
//			System.out.println("tu t'es trompé, recommence");
//			
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected int verifyUsername(String usernameEntered,String passwordEncrypted, HttpSession session) {
		int id=-1;
		Connection connexion=initialisation();
		try {
			Statement statement = connexion.createStatement();

			String req = "SELECT username, password, id FROM user WHERE username=\""+usernameEntered+"\";";
			ResultSet rs;
			rs = statement.executeQuery(req);
			if(rs.next()!=false) {
				System.out.println(passwordEncrypted);
				if(rs.getString("password").equals(passwordEncrypted)) {
					id=rs.getInt(id);
					session.setAttribute("name", rs.getString("name"));
					System.out.println("bravo t'es co!");
				}else {System.out.println("mauvais mdp");}
	
			}else {System.out.println("mauvais username");}
		}
		catch(Exception e) {System.out.println("Exception : " + e.getMessage());}
		return id;
	}
	protected int verifyMail(String mailEntered,String passwordEncrypted, HttpSession session) {
		int id=-1;
		Connection connexion=initialisation();
		try {
			Statement statement = connexion.createStatement();

			String req = "SELECT password, id FROM user WHERE mail=\""+mailEntered+"\";";
			ResultSet rs;
			rs = statement.executeQuery(req);
			if(rs.next()!=false) {
				if(rs.getString("password").equals(passwordEncrypted)) {
					id=rs.getInt(id);
					System.out.println("bravo t'es co!");
				}else {System.out.println("mauvais mdp");}
	
			}else {System.out.println("mauvais username");}
		}
		catch(Exception e) {System.out.println("Exception : " + e.getMessage());}
		return id;
	}
	protected static Connection initialisation() {
		try {
			Class c = Class.forName("com.mysql.cj.jdbc.Driver");
			Driver pilote = (Driver) c.newInstance();
			DriverManager.registerDriver(pilote);

			String protocole = "jdbc:mysql:";
			String ip = "127.0.0.1";
			String port = "3306";
			String nomBase = "projetlogin";
			String my8 = "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
			// Chane de connexion
			String conString = protocole + "//" + ip + ":" + port + "/" + nomBase + my8;
			//System.out.println(conString);
			// Identifiants de connexion et mot de passe
			String nomConnexion = "root"; // dpend du contexte
			String motDePasse = "pass"; // dpend du contexte
			// Connexion
			Connection connexion = DriverManager.getConnection(conString, nomConnexion, motDePasse);
			return connexion;
		}catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return null;
		}
	}
	
	private static byte[] encrypt(String x) throws Exception {
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

}
