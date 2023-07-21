package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOS.ComposizioneDM;
import model.DAOS.DatiPagamento;
import model.DAOS.DatiPagamentoDM;
import model.DAOS.IndirizzoSpedizioneDM;
import model.DAOS.OrdineDM;
import model.DAOS.ProductModelDM;
import model.beans.Carrello;
import model.beans.ComposizioneBean;
import model.beans.DatiPagamentoBean;
import model.beans.IndirizzoSpedizioneBean;
import model.beans.OrdineBean;
import model.beans.ProductBean;
import model.beans.UtenteBean;

@WebServlet("/OrdineControl")
public class OrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrdineDM ordineDAO = new OrdineDM();
	private ComposizioneDM composizioneDM = new ComposizioneDM();
	
	
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			UtenteBean utente = (UtenteBean) session.getAttribute("Utente");
			OrdineBean ordine = (OrdineBean) session.getAttribute("idOrdine");
			if(utente == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
			
				String action = request.getParameter("action");
				
				if(action.equals("datiOrdineUtente")) {
					OrdineBean datiOrdini = new OrdineBean();
					try {
						datiOrdini = ordineDAO.doRetrieveByKey(ordine.getID());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("StatoOrdine", datiOrdini.getStato());
					request.setAttribute("datiPagamento", datiOrdini.getModPagamento());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/checkoutOrdine.jsp");
					dispatcher.forward(request, response);
				}
					
						
				if(action.equals("checkout")) {
					Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
					ArrayList<ProductBean> prodotti = carrello.getProducts();
					if(prodotti.size() == 0) {
						request.setAttribute("operazione", "Il carrello � vuoto, visita il nostro catalogo");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
						dispatcher.forward(request, response);
					}else {
						OrdineBean OrdineCheckOut = new OrdineBean();
						try {
							OrdineCheckOut.setID(ordineDAO.getIdCodice());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						OrdineCheckOut.setDataPrenotazione(ordine.getDataPrenotazione());
						OrdineCheckOut.setStato("Pagato");
						OrdineCheckOut.setModPagamento(ordine.getModPagamento());
						OrdineCheckOut.setUtente(utente.getId());
						try {
							ordineDAO.doSave(OrdineCheckOut);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						/*for(ProductBean product : prodotti) {
							ComposizioneBean composizione = new ComposizioneBean();
							composizione.setOrdine(ordine.getID());
							composizione.setProdotto(product.getCodice());
							composizione.setQuantita(product.getQtaCarrello());
							composizione.setPrezzoAcquisto(product.getPrezzo());
							composizione.setIvaAcquisto(product.getIva());
							try {
								composizioneDM.doSave(composizione);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}*/
						
						request.getSession().removeAttribute("carrello");
						carrello = new Carrello();
						request.getSession().setAttribute("carrello", carrello);
						request.setAttribute("operazione", "Il tuo ordine � stato preso in carico");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
						dispatcher.forward(request, response);
					}
				}
				
				if(action.equals("dettagliOrdine")) {
					int id = Integer.parseInt(request.getParameter("idOrdine"));
					OrdineDM ordineDAO = new OrdineDM();
					OrdineBean ordineCompleto = new OrdineBean();
					
					try {
						ordineCompleto = ordineDAO.doRetrieveByKey(id);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					request.setAttribute("dettagliOrdine", ordineCompleto);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/dettagliOrdine.jsp");
					dispatcher.forward(request, response);
				}
		
				if(action.equals("viewOrdini")) {
					try {
						ArrayList<OrdineBean> ordini = ordineDAO.doRetrieveAllByUtente(utente.getId());
						request.setAttribute("ordini", ordini);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ordiniUtente.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}