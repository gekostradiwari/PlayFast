package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOS.FeedbackDM;
import model.DAOS.ProductModelDM;
import model.beans.Carrello;
import model.beans.FeedbackBean;
import model.beans.ProductBean;
import model.beans.UtenteBean;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ProductModelDM model = new ProductModelDM();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session2 = request.getSession();
		UtenteBean utente = (UtenteBean) session2.getAttribute("Utente");
		//CARRELLO
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if(carrello == null) {
			carrello = new Carrello();
			request.getSession().setAttribute("carrello", carrello);
		}
		//FINE CARRELLO
				
		String action = request.getParameter("action");
		System.out.println(action);
		if(action != null) {
			if(action.equals("ViewProdotti")) {
				ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
				try {
					prodotti = model.doRetrieveAll("id");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti", prodotti);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FirstPage.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("ViewProdotto")) {
				ProductBean prodotto = null;
				
				int cod = Integer.getInteger(request.getParameter("codice"));
				try {
					prodotto = model.doRetrieveByKey(cod);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("prodotto", prodotto);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/prodotto.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("AddToCarrello")) {
				try {
					carrello.addProduct(Integer.parseInt(request.getParameter("codice")));
					//System.out.println(Integer.valueOf(request.getParameter("codice")));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = null;
				if(utente == null) {
					request.getSession().setAttribute("carrello", carrello);
					dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
					dispatcher.forward(request, response);
					}
					else {
						request.getSession().setAttribute("carrello", carrello);
						dispatcher = getServletContext().getRequestDispatcher("/cart-utente.jsp");
						dispatcher.forward(request, response);
					}
					
			}
			
			if(action.equals("RemoveToCarrello")) {
				carrello.removeProduct(Integer.parseInt(request.getParameter("codice")));
				RequestDispatcher dispatcher = null;
				if(utente == null) {
				request.getSession().setAttribute("carrello", carrello);
				dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
				}
				else {
					request.getSession().setAttribute("carrello", carrello);
					dispatcher = getServletContext().getRequestDispatcher("/cart-utente.jsp");
				}
				dispatcher.forward(request, response);
				
			}	
			if(action.equals("getCampiPerData")) {
				ArrayList<ProductBean> prodotti = new ArrayList<ProductBean>();
				String citta = request.getParameter("Citta");
				Date data = null;
				SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
				try {
					data = inputFormat.parse(request.getParameter("Data"));
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
				String sport = request.getParameter("Sport");
				LocalTime ora = LocalTime.parse(request.getParameter("Ora"));
				

				try {
					prodotti = model.doRetriveByData(citta,finalDate, sport, ora);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti", prodotti);
				if(utente == null) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FirstPage.jsp");
					dispatcher.forward(request, response);
				}
				else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FirstPage-Utente.jsp");
					dispatcher.forward(request, response);
				}
				
				
			}
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}