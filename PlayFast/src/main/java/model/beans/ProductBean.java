package model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String indirizzo;
	private String nome;
	private String telefono;
	private String struttura;
	private GregorianCalendar dataCampo;
	private String tipo;
	private String email;
	private double prezzo;
	private String urlImmagine;
	
	

	public ProductBean() {
		id = 0;
		indirizzo = "";
		nome = "";
		telefono = "";
		struttura = "";
		dataCampo = new GregorianCalendar(0,0,0,0,0,0);
		tipo = "";
		email = "";
		prezzo = 0.0;
		urlImmagine = "";
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


	public GregorianCalendar getDataCampo() {
		return dataCampo;
	}


	public void setDataCampo(GregorianCalendar dataCampo) {
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


	@Override
	public String toString() {
		return "ProductBean [codice=" + id + ", indirizzo=" + indirizzo + ", nome=" + nome + ", telefono=" + telefono
				+ ", struttura=" + struttura + ", dataCampo=" + dataCampo.toString() + ", tipo=" + tipo + ", email="
				+ email + ", prezzo=" + prezzo + ", urlImmagine=" + urlImmagine + "]";
	}

}