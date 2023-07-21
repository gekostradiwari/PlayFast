package model.beans;

import java.util.Date;


public class OrdineBean {
	
	private int ID;
	private String stato;
	private Date dataPrenotazione;
	private int utente;
	private String modPagamento;
	
	public OrdineBean() {
		ID = 0;
		stato = "";
		utente = 0;
		modPagamento = "";
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Date getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public int getUtente() {
		return utente;
	}

	public void setUtente(int utente) {
		this.utente = utente;
	}

	public String getModPagamento() {
		return modPagamento;
	}

	public void setModPagamento(String modPagamento) {
		this.modPagamento = modPagamento;
	}


	
	

	
	
	

	
}