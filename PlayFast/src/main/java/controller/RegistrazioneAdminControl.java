package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import model.beans.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.DAOS.UtenteDM;
import model.DAOS.*;

import model.beans.UtenteBean;

@WebServlet("/RegistrazioneAdminControl")
public class RegistrazioneAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminDM adminDAO = new adminDM();
		AdminBean admin = null;
		try {
			admin = adminDAO.doRetrieveByMail(request.getParameter("email"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(admin == null) {			
			admin = new AdminBean();
			admin.setEmail(request.getParameter("email"));
			admin.setPassword(request.getParameter("password"));
			admin.setNome(request.getParameter("nome"));
			admin.setCognome(request.getParameter("cognome"));
			admin.setIndirizzo(request.getParameter("indirizzo"));
			
			boolean flag = true;
			try {
				adminDAO.doSave(admin);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
		
	}
}