package controller;

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

@WebServlet("/Login")
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
			}
			else if(utente != null && admin == null){
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(60*35);
				session.setAttribute("Utente", utente);
				Cookie c = new Cookie("User","true");
				c.setSecure(true);
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				response.getWriter().write("1");
			}
			else {
				response.getWriter().write("0");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}