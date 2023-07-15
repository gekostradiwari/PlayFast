package model.beans;

import java.io.Serializable;

public class AdminBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String cognome;
	private String nome;
	private String indirizzo;
	private String email; 
	private String password;
	public AdminBean() {
		id = 0;
		cognome = "";
		nome = "";
		indirizzo = "";
		email = "";
		password = "";		
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
