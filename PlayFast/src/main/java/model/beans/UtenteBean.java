package model.beans;

import java.io.Serializable;
import java.util.Date;

public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String mail;
	private String password;
	private String nome;
	private String cognome;
	private Date dataNascita;
	
	public UtenteBean() {
		id = 0;
		mail = "";
		password = "";
		nome = "";
		cognome = "";
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

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	
	
	

}