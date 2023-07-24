package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import model.DAOS.UtenteDM;
import model.DAOS.adminDM;
import model.beans.UtenteBean;
import model.beans.AdminBean;

@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static UtenteDM model = new UtenteDM();
	private static adminDM modelA = new adminDM();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//password = toHash(password);
		
		
		try {
			UtenteBean utente = model.doRetrieve(email, password);
			AdminBean admin = modelA.doRetrieve(email, password); 
			
			if(utente == null && admin != null) {
				// Admin login successful
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*35);
				session.setAttribute("Admin", admin);
				
				Cookie adminCookie = new Cookie("Admin", "true");
				adminCookie.setSecure(true);
				adminCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days (change as needed)
				adminCookie.setPath("/");
				response.addCookie(adminCookie);
			
				response.getWriter().write("1");
				response.sendRedirect(request.getContextPath() + "/Profilo.jsp");
			}
			else if(utente != null && admin == null){
				// Regular user login successful
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*35);
				session.setAttribute("Utente", utente);
				
				Cookie userCookie = new Cookie("User", "true");
				userCookie.setSecure(true);
				userCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days (change as needed)
				userCookie.setPath("/");
				response.addCookie(userCookie);
				
				response.getWriter().write("1");
				response.sendRedirect(request.getContextPath() + "/Profilo-Utente.jsp");
			}
			else {
				// Login failed
				response.getWriter().write("0");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String toHash(String password)
	{
		String hashString = null;
		try
		{
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
			byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			hashString = "";
			for (int i=0; i<hash.length; i++)
			{
				hashString += Integer.toHexString((hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3);
			}
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
			System.err.println(e);
		}
		
		return hashString;
	}
}

/*package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import model.DAOS.UtenteDM;
import model.DAOS.adminDM;
import model.beans.UtenteBean;
import model.beans.AdminBean;

@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static UtenteDM model = new UtenteDM();
	private static adminDM modelA = new adminDM();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		try {
			UtenteBean utente = model.doRetrieve(email, password);
			AdminBean admin = modelA.doRetrieve(email, password); 
			if(utente == null && admin != null) {
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*35);
				session.setAttribute("Admin", admin);
				Cookie c = new Cookie("Admin","true");		
				c.setSecure(true);
				c.setMaxAge(-1);
				c.setPath("/");
				response.addCookie(c);	
				response.getWriter().write("1");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Profilo.jsp");
				dispatcher.forward(request, response);
			}
			else if(utente != null && admin == null){
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*35);
				session.setAttribute("Utente", utente);
				Cookie c = new Cookie("User","true");
				c.setSecure(true);
				c.setMaxAge(-1);
				c.setPath("/");
				response.addCookie(c);
				response.getWriter().write("1");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Profilo-Utente.jsp");
				dispatcher.forward(request, response);
			}
			else {
				response.getWriter().write("0");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}*/