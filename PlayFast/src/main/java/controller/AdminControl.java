package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DriverManagerConnectionPool;
import model.DAOS.ComposizioneDM;
import model.DAOS.FeedbackDM;
import model.DAOS.IndirizzoSpedizioneDM;
import model.DAOS.OrdineDM;
import model.DAOS.ProductModelDM;
import model.DAOS.UtenteDM;
import model.beans.ComposizioneBean;
import model.beans.FeedbackBean;
import model.beans.IndirizzoSpedizioneBean;
import model.beans.OrdineBean;
import model.beans.ProductBean;
import model.beans.UtenteBean;
import model.beans.*;

@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//ciaociao
	//ciaociao

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean utente = (UtenteBean) session.getAttribute("Utente");
		AdminBean admin = (AdminBean) session.getAttribute("Admin");
		if(utente == null && admin == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		else if(utente != null && admin == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		String action = request.getParameter("action");
		
		if(action.equals("listProdottiByAdmin")) {
			ProductModelDM search = new ProductModelDM();
			ArrayList<ProductBean> result = new ArrayList<ProductBean>();
			try {
				result = search.doRetrieveByAdmin(admin.getId());
				
			}catch (SQLException e1) {
				e1.printStackTrace();
				e1.getMessage();
			}
			request.setAttribute("listaProdottiAdmin", result);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/listaProdottiAdmin.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("gestioneClienti")) {
			ProductModelDM Search = new ProductModelDM();
			ArrayList<ProductBean> search = new ArrayList<ProductBean>();
			OrdineDM Search1 = new OrdineDM();
			ArrayList<OrdineBean> search1 = new ArrayList<OrdineBean>();
			UtenteDM utenti = new UtenteDM();
			ArrayList<UtenteBean> listaUtenti = new ArrayList<UtenteBean>();
			try {
				search = Search.doRetrieveByAdmin(admin.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
				e1.getMessage();
			}
			for(ProductBean a : search) {
				try {
					Search1.doRetrieveByCampo(a.getId()).stream().forEach(search1::add);		
				}catch(SQLException e2) {
					e2.printStackTrace();
					e2.getMessage();
				}
			}
			for(OrdineBean o : search1) {
				try {
					utenti.doRetrieveByKey(o.getUtente()).stream().forEach(listaUtenti::add);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("listaUtenti", listaUtenti);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/adminClienti.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("gestioneCatalogo")) {
			ProductModelDM search = new ProductModelDM();
			ArrayList <ProductBean> result	= new ArrayList <ProductBean>();
			try {
				result = search.doRetrieveByAdmin(admin.getId());
			} catch (SQLException e) {
				e.printStackTrace();
				e.getMessage();
			}
			
			request.setAttribute("listaProdotti", result);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/adminCatalogo.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("gestioneOrdini")) {
			ProductModelDM Search = new ProductModelDM();
			ArrayList<ProductBean> search = new ArrayList<ProductBean>();
			OrdineDM Search1 = new OrdineDM();
			ArrayList<OrdineBean> search1 = new ArrayList<OrdineBean>();
			try {
				search = Search.doRetrieveByAdmin(admin.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
				e1.getMessage();
			}
			for(ProductBean a : search) {
				try {
					Search1.doRetrieveByCampo(a.getId()).stream().forEach(search1::add);		
				}catch(SQLException e2) {
					e2.printStackTrace();
					e2.getMessage();
				}
			}
			request.setAttribute("ordini", search1);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/adminOrdini.jsp");
			dispatcher.forward(request, response);
		}
		
		/*if(action.equals("viewOrdinePerData")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date da = null;
			Date a = null;
			try {
				da = dateFormat.parse(request.getParameter("dataDa"));
				a = dateFormat.parse(request.getParameter("dataA"));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			OrdineDM search = new OrdineDM();
			ArrayList<OrdineBean> listor = new ArrayList<OrdineBean>();
			try {
				listor  = search.doRetrieveByDate(da, a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("ordini", listor);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/adminOrdini.jsp");
			dispatcher.forward(request, response);
		}*/ //DA VEDERE, SCEGLIERE SE IMPLEMENTARE LATO FRONT_END o BACK_END
		
		/*if(action.equals("viewOrdine")) {
			ComposizioneDM model = new ComposizioneDM();
			IndirizzoSpedizioneDM indirizzoDAO = new IndirizzoSpedizioneDM();
			OrdineDM ordineDAO = new OrdineDM();
			
			int id = Integer.parseInt(request.getParameter("idOrdine"));
			ArrayList<ProductBean> products = new ArrayList<ProductBean>();
			IndirizzoSpedizioneBean indirizzo = null;
			
			try {
				ArrayList<ComposizioneBean> composizioni = model.doRetrieveByOrdine(id);
				
				ProductModelDM productDAO = new ProductModelDM();
				for(ComposizioneBean composizione : composizioni) {
					ProductBean product = productDAO.doRetrieveByKey(composizione.getProdotto());
					product.setQtaCarrello(composizione.getQuantita());
					products.add(product);
				}
				OrdineBean o = ordineDAO.doRetrieveByKey(id);
				indirizzo = indirizzoDAO.doRetrieveByKey(Integer.parseInt(o.getIndirizzoSpedizione()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String indCompleto = indirizzo.getVia() + " Citt� " + indirizzo.getCitta() + " CAP " + indirizzo.getCap();
			request.setAttribute("dettagliOrdine", products);
			request.setAttribute("indirizzo", indCompleto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/dettagliOrdine.jsp");
			dispatcher.forward(request, response);
		}*/
		
		if(action.equals("ordiniUtente")) {
			OrdineDM model = new OrdineDM();
			int utente1 = Integer.parseInt(request.getParameter("utente"));
			try {
				ArrayList<OrdineBean> ordini = model.doRetrieveAllByUtente(utente1);
				request.setAttribute("ordini", ordini);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordiniUtente.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("eliminaProdotto")) {
			ProductModelDM model = new ProductModelDM();
			int codice = Integer.parseInt(request.getParameter("prodotto"));
			try {
				model.doDelete(codice);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("operazione", "L'eliminazione del prodotto � avvenuta con successo");
			response.sendRedirect("pages/operazione.jsp");
		}
		
		if(action.equals("aggiungiProdotto")) {
			ProductBean product = new ProductBean();
			product.setIndirizzo(request.getParameter("Indirizzo"));
			product.setNome(request.getParameter("Nome"));
			product.setTelefono(request.getParameter("Telefono"));
			product.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
			product.setStruttura(request.getParameter("Struttura"));
			Calendar calendar = new GregorianCalendar();
			LocalDate localDate = LocalDate.parse(request.getParameter("dataCampo"));
			GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()));
			product.setDataCampo(gregorianCalendar);
			product.setTipo(request.getParameter("Tipo"));
			product.setEmail(request.getParameter("Email"));
			product.setUrlImmagine("fotoProdotti/"+request.getParameter("urlImmagine"));
			
			ProductModelDM model = new ProductModelDM();
			try {
				model.doSave(product);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("operazione", "L'aggiunta del prodotto al catalogo � avvenuta con successo");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("modificaProdotto")) {
			ProductBean product = new ProductBean();
			product.setIndirizzo(request.getParameter("Indirizzo"));
			product.setNome(request.getParameter("Nome"));
			product.setTelefono(request.getParameter("Telefono"));
			product.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
			product.setStruttura(request.getParameter("Struttura"));
			Calendar calendar = new GregorianCalendar();
			LocalDate localDate = LocalDate.parse(request.getParameter("dataCampo"));
			GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()));
			product.setDataCampo(gregorianCalendar);
			product.setTipo(request.getParameter("Tipo"));
			product.setEmail(request.getParameter("Email"));
			product.setUrlImmagine("fotoProdotti/"+request.getParameter("urlImmagine"));
			
			ProductModelDM model = new ProductModelDM();
			try {
				model.doUpdate(product);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("operazione", "La modifica del prodotto al catalogo � avvenuta con successo");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equals("feedbackUtente")) {
			FeedbackDM feedbackDAO = new FeedbackDM();
			ArrayList<FeedbackBean> feedbacks = null;
			try {
				feedbacks = feedbackDAO.doRetrieveByUtente(request.getParameter("utente"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("feedbackUtente", feedbacks);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/feedbackUtente.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}