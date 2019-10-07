package com.dani.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jornadas")
public class Jornadas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8862464152388417939L;

	@Id
	@Column(name = "idjornada")
	private int idJornada;
	
	@Column(name = "fecha")
	private String fecha;
	
	@Column(name = "isjugado")
	private boolean isJugado;

	public Jornadas() {}
	
	public Jornadas(int idJornada, String fecha, boolean isJugado) {
		super();
		this.idJornada = idJornada;
		this.fecha = fecha;
		this.isJugado = isJugado;
	}


	public int getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(int idJornada) {
		this.idJornada = idJornada;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public boolean isJugado() {
		return isJugado;
	}

	public void setJugado(boolean isJugado) {
		this.isJugado = isJugado;
	}
	
	
}
