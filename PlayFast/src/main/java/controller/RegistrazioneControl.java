package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.DAOS.UtenteDM;

import model.beans.UtenteBean;

@WebServlet("/RegistrazioneControl")
public class RegistrazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteDM utenteDAO = new UtenteDM();
		UtenteBean utente = null;
		try {
			utente = utenteDAO.doRetrieveByEmail(request.getParameter("email"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(utente == null) {			
			utente = new UtenteBean();
			utente.setMail(request.getParameter("email"));
			utente.setPassword(request.getParameter("password"));
			utente.setNome(request.getParameter("nome"));
			utente.setCognome(request.getParameter("cognome"));
			Calendar cal = new GregorianCalendar();
			cal.setTime(java.sql.Date.valueOf(request.getParameter("dataNascita")));
			utente.setDataNascita((GregorianCalendar) cal);
			
			boolean flag = true;
			try {
				utenteDAO.doSave(utente);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
		
	}
}