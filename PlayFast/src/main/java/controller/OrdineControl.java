package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.DAOS.ComposizioneDM;
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
			if(utente == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login.jsp");
				dispatcher.forward(request, response);
			} else {
			
				String action = request.getParameter("action");
				
				if(action.equals("datiOrdineUtente")) {
					IndirizzoSpedizioneDM indirizzoDAO=new IndirizzoSpedizioneDM();
					DatiPagamentoDM datiPagamentoDAO=new DatiPagamentoDM();
					try {
						ArrayList<IndirizzoSpedizioneBean> indirizzi= indirizzoDAO.doRetrieveByUtente(utente.getMail());
						DatiPagamentoBean datiPagamento= datiPagamentoDAO.doRetrieveByKey(utente.);
						request.setAttribute("indirizziSpedizione", indirizzi);
						request.setAttribute("datiPagamento", datiPagamento);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/checkoutOrdine.jsp");
						dispatcher.forward(request, response);	
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
					
						
				if(action.equals("checkout")) {
					Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
					ArrayList<ProductBean> prodotti = carrello.getProducts();
					if(prodotti.size() == 0) {
						request.setAttribute("operazione", "Il carrello � vuoto, visita il nostro catalogo");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
						dispatcher.forward(request, response);
					}else {
						OrdineBean ordine = new OrdineBean();
						try {
							ordine.setID(ordineDAO.getIdCodice());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						ordine.setData(new Date());
						ordine.setStato("Ordinato");
						ordine.setIndirizzoSpedizione(request.getParameter("indirizzo"));
						ordine.setUtente(utente.getEmail());
						ordine.setTotale(carrello.getTotale());
						try {
							ordineDAO.doSave(ordine);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						for(ProductBean product : prodotti) {
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
						}
						
						request.getSession().removeAttribute("carrello");
						carrello = new Carrello();
						request.getSession().setAttribute("carrello", carrello);
						request.setAttribute("operazione", "Il tuo ordine � stato preso in carico");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
						dispatcher.forward(request, response);
					}
				}
				
				if(action.equals("dettagliOrdine")) {
					int id = Integer.parseInt(request.getParameter("id"));
					ArrayList<ProductBean> products = new ArrayList<ProductBean>();
					IndirizzoSpedizioneDM indirizzoDAO = new IndirizzoSpedizioneDM();
					OrdineDM ordineDAO = new OrdineDM();
					IndirizzoSpedizioneBean indirizzo = null;
					
					try {
						ArrayList<ComposizioneBean> composizioni = composizioneDM.doRetrieveByOrdine(id);
						
						ProductModelDM productDAO = new ProductModelDM();
						for(ComposizioneBean composizione : composizioni) {
							ProductBean product = productDAO.doRetrieveByKey(composizione.getProdotto());
							product.setPrezzo(composizione.getPrezzoAcquisto());
							product.setIva(composizione.getIvaAcquisto());
							product.setQtaCarrello(composizione.getQuantita());
							products.add(product);
						}
						OrdineBean o = ordineDAO.doRetrieveByKey(id);
						indirizzo = indirizzoDAO.doRetrieveByKey(Integer.parseInt(o.getIndirizzoSpedizione()));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					String indCompleto = indirizzo.getVia() + " Citt� " + indirizzo.getCitta() + " CAP " + indirizzo.getCap();
					request.setAttribute("dettagliOrdine", products);
					request.setAttribute("indirizzo", indCompleto);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/dettagliOrdine.jsp");
					dispatcher.forward(request, response);
				}
		
				if(action.equals("viewOrdini")) {
					try {
						ArrayList<OrdineBean> ordini = ordineDAO.doRetrieveAllByUtente(utente.getEmail());
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