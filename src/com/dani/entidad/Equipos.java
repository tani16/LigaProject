package com.dani.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "equipos")
public class Equipos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -620777844074142655L;
	
	@Id
	@Column(name = "idEquipo")
	private int idEquipo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "abreviatura")
	private String abreviatura;
	
	@Column(name = "twitter")
	private String twitter;
	
	@Column(name = "nameas")
	private String nameAs;

	public Equipos() {}
	
	public Equipos(int idEquipo, String nombre, String abreviatura) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getNameAs() {
		return nameAs;
	}

	public void setNameAs(String nameAs) {
		this.nameAs = nameAs;
	}

}
