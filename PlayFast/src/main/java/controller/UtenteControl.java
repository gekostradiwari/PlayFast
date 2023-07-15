
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.DAOS.DatiPagamentoDM;
import model.DAOS.FeedbackDM;
import model.DAOS.IndirizzoSpedizioneDM;
import model.DAOS.ProductModelDM;
import model.DAOS.UtenteDM;
import model.beans.DatiPagamentoBean;
import model.beans.FeedbackBean;
import model.beans.IndirizzoSpedizioneBean;
import model.beans.ProductBean;
import model.beans.UtenteBean;


@WebServlet("/UtenteControl")
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IndirizzoSpedizioneDM indirizzoDAO= new IndirizzoSpedizioneDM();
	private DatiPagamentoDM datiDAO= new DatiPagamentoDM();
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			UtenteBean utente = (UtenteBean) session.getAttribute("Utente");
			if(utente == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login.jsp");
				dispatcher.forward(request, response);
			}

			String action = request.getParameter("action");
			
			if(action.equals("viewDatiPagamentoSpedizione")) {
				try {
					DatiPagamentoBean dati = datiDAO.doRetrieveByKey(utente.getDatiPagamento());
					request.setAttribute("datiPagamento", dati);
					ArrayList<IndirizzoSpedizioneBean> indirizzi = indirizzoDAO.doRetrieveByUtente(utente.getEmail());
					request.setAttribute("indirizzi", indirizzi);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/viewDatiPagamentoSpedizione.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("addIndirizzoSpedizione")) {
				String via=request.getParameter("via");
				String cap=request.getParameter("cap");
				String citta=request.getParameter("citta");
				IndirizzoSpedizioneBean indirizzo=new IndirizzoSpedizioneBean();
				indirizzo.setVia(via);
				indirizzo.setCap(cap);
				indirizzo.setCitta(citta);
				indirizzo.setUtente(utente.getEmail());
				try {
					indirizzoDAO.doSave(indirizzo);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("operazione", "L'aggiunta dell'indirizzo di spedizione � avvenuta correttamente");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("addDatiPagamento")) {
				String numeroCarta=request.getParameter("carta");
				int cvv=Integer.parseInt(request.getParameter("cvv"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dataScadenza=null;
				try {
					dataScadenza=dateFormat.parse(request.getParameter("data"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				DatiPagamentoBean datiPagamento=new DatiPagamentoBean();
				datiPagamento.setNumeroCarta(numeroCarta);;
				datiPagamento.setCvv(cvv);
				datiPagamento.setDataScadenza(dataScadenza);
				utente.setDatiPagamento(numeroCarta);
				try {
					datiDAO.doSave(datiPagamento);
					UtenteDM uDAO = new UtenteDM();
					uDAO.doUpdate(utente);
				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("operazione", "Si � verificato un errore. Ci scusiamo per il disagio.");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
					dispatcher.forward(request, response);
				}
				request.setAttribute("operazione", "L'aggiunta dei Dati di Pagamento � avvenuta correttamente");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("feedback")) {
				ProductModelDM prodottoDAO = new ProductModelDM();
				ArrayList<ProductBean> prodotti = prodottoDAO.doRetrieveProductBuy(utente.getEmail());
				request.setAttribute("prodottiBuy", prodotti);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/feedback.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("valutazione")) {
				FeedbackBean f = new FeedbackBean();
				f.setValutazione(Integer.parseInt(request.getParameter("val")));
				f.setCommento(request.getParameter("commento"));
				f.setEmail(request.getParameter("utente"));
				f.setProdotto(request.getParameter("prodotto"));
				
				FeedbackDM feedbackDAO = new FeedbackDM();
				try {
					feedbackDAO.doSave(f);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("operazione", "Grazie per il tuo feedback. Il tuo parere per noi � molto importante.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/operazione.jsp");
				dispatcher.forward(request, response);
			}
			
			if(action.equals("viewDatiAnagrafica")) {
				PersonaFisicaBean persona = null;
				
				if(utente.getTipo().equalsIgnoreCase("persona fisica")) {
					PersonaFisicaDM personaDAO = new PersonaFisicaDM();
					try {
						 persona = personaDAO.doRetrieveByEmail(utente.getEmail());
						 request.setAttribute("anagrafica", persona);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/datiAnagrafici.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}