package model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int id_admin;
	private String indirizzo;
	private String nome;
	private String telefono;
	private String struttura;
	private Date dataCampo;
	private String tipo;
	private String email;
	private double prezzo;
	private String citta;
	private String urlImmagine;
	
	

	public ProductBean() {
		id = 0;
		id_admin = 0;
		indirizzo = "";
		nome = "";
		telefono = "";
		struttura = "";
		tipo = "";
		email = "";
		prezzo = 0.0;
		urlImmagine = "";
		citta = "";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getStruttura() {
		return struttura;
	}


	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}


	public Date getDataCampo() {
		return dataCampo;
	}


	public void setDataCampo(Date dataCampo) {
		this.dataCampo = dataCampo;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public String getUrlImmagine() {
		return urlImmagine;
	}


	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	


	public int getId_admin() {
		return id_admin;
	}


	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}


	@Override
	public String toString() {
		return "ProductBean [codice=" + id + ", indirizzo=" + indirizzo + ", nome=" + nome + ", telefono=" + telefono
				+ ", struttura=" + struttura + ", dataCampo=" + dataCampo.toString() + ", tipo=" + tipo + ", email="
				+ email + ", prezzo=" + prezzo + ", urlImmagine=" + urlImmagine + "]";
	}

}