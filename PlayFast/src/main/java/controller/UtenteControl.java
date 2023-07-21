
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import model.beans.OrdineBean;
import model.DAOS.OrdineDM;


@WebServlet("/UtenteControl")
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
					ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();
					ArrayList<String> datiPagamento = new ArrayList<String>();
					OrdineDM Ordini = new OrdineDM();
					ordini = Ordini.doRetrieveAllByUtente(utente.getId());
					for(OrdineBean i : ordini) {
						datiPagamento.add(i.getModPagamento());
					}
					request.setAttribute("datiPagamento", datiPagamento);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/viewDatiPagamentoSpedizione.jsp");
				dispatcher.forward(request, response);
			}
			
			
			/*if(action.equals("addDatiPagamento")) {
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
			*/
			
			if(action.equals("viewDatiAnagrafica")) {
				UtenteBean persona = null;
					UtenteDM personaDAO = new UtenteDM();
					try {
						 persona = personaDAO.doRetrieve(utente.getMail(), utente.getPassword());
						 request.setAttribute("anagrafica", persona);
					} catch (SQLException e) {
						e.printStackTrace();
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