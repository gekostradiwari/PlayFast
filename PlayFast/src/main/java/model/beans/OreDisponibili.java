package model.beans;

import java.io.Serializable;

public class OreDisponibili implements Serializable {
	private int id_campo;
	private double orario;
	private boolean disponibilita;
	
	public OreDisponibili(double orario) {
		this.orario = orario;
		this.disponibilita = true;		
	}

	public double getOrario() {
		return orario;
	}

	public void setOrario(double orario) {
		this.orario = orario;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	public int getId_campo() {
		return id_campo;
	}

	public void setId_campo(int id_campo) {
		this.id_campo = id_campo;
	}
	

}
