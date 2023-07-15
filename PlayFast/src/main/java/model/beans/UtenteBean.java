package model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String mail;
	private String password;
	private String nome;
	private String cognome;
	private GregorianCalendar dataNascita;
	
	public UtenteBean() {
		id = 0;
		mail = "";
		password = "";
		nome = "";
		cognome = "";
		dataNascita = new GregorianCalendar(0,0,0,0,0,0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public GregorianCalendar getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(GregorianCalendar dataNascita) {
		this.dataNascita = dataNascita;
	}

	
	
	

}