package com.dani.entidad;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clasificacion")
public class Clasificacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5744601541246284885L;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEquipo")
	private Equipos equipo;
	
	@Column(name = "puntos")
	private int puntos;
	
	@Column(name = "marcados")
	private int marcados;
	
	@Column(name = "recibidos")
	private int recibidos;
	
	@Column(name = "diferencia")
	private int diferencia;
	
	@Column(name = "puntosreal")
	private int puntosReal;
	

	public Clasificacion() {}
	
	public Clasificacion(Equipos equipo, int puntos, int marcados, int recibidos) {
		super();
		this.equipo = equipo;
		this.puntos = puntos;
		this.marcados = marcados;
		this.recibidos = recibidos;
	}

	public Equipos getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getMarcados() {
		return marcados;
	}

	public void setMarcados(int marcados) {
		this.marcados = marcados;
	}

	public int getRecibidos() {
		return recibidos;
	}

	public void setRecibidos(int recibidos) {
		this.recibidos = recibidos;
	}

	public int getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(int diferencia) {
		this.diferencia = diferencia;
	}

	public int getPuntosReal() {
		return puntosReal;
	}

	public void setPuntosReal(int puntosReal) {
		this.puntosReal = puntosReal;
	}

	
	
	
	
}
