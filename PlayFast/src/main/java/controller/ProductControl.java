package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOS.FeedbackDM;
import model.DAOS.ProductModelDM;
import model.beans.Carrello;
import model.beans.FeedbackBean;
import model.beans.ProductBean;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ProductModelDM model = new ProductModelDM();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CARRELLO
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		if(carrello == null) {
			carrello = new Carrello();
			request.getSession().setAttribute("carrello", carrello);
		}
		//FINE CARRELLO
				
		String action = request.getParameter("action");
		
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
					carrello.addProduct(Integer.getInteger(request.getParameter("codice")));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("carrello", carrello);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/carrello.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("RemoveToCarrello")) {
				carrello.removeProduct(Integer.getInteger(request.getParameter("codice")));
				request.getSession().setAttribute("carrello", carrello);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/carrello.jsp");
				dispatcher.forward(request, response);
			}			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}