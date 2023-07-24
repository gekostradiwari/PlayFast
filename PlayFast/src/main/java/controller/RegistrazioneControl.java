package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			String password = request.getParameter("password");
			//password = toHash(password);
			utente.setPassword(password);
			utente.setNome(request.getParameter("nome"));
			utente.setCognome(request.getParameter("cognome"));
			Date data = null;
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				data = inputFormat.parse(request.getParameter("DataNascita"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = outputFormat.format(data);
			Date finalDate;
	        try {
	            finalDate = outputFormat.parse(formattedDate);
	        } catch (ParseException e) {
	            System.out.println("Errore nel parsing della data finale.");
	            return;
	        }
			utente.setDataNascita(finalDate);
			try {
				utente.setId(utenteDAO.getIdCodice());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			boolean flag = true;
			try {
				utenteDAO.doSave(utente);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			response.getWriter().write("1");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Sign-in.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().write("0");
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