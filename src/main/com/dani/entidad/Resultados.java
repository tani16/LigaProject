package com.dani.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resultados")
public class Resultados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8808226062002238832L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idResultado")
	private int idResultado;
	
	@Column(name = "golesC")
	private int golesC;
	
	@Column(name = "golesF")
	private int golesF;
	
	@Column(name = "golescreal")
	private int golesCReal;
	
	@Column(name = "golesfreal")
	private int golesFReal;

	public Resultados() {}
	
	public Resultados(int idResultado, int golesC, int golesF) {
		super();
		this.idResultado = idResultado;
		this.golesC = golesC;
		this.golesF = golesF;
	}

	public int getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}

	public int getGolesC() {
		return golesC;
	}

	public void setGolesC(int golesC) {
		this.golesC = golesC;
	}

	public int getGolesF() {
		return golesF;
	}

	public void setGolesF(int golesF) {
		this.golesF = golesF;
	}

	public int getGolesCReal() {
		return golesCReal;
	}

	public void setGolesCReal(int golesCReal) {
		this.golesCReal = golesCReal;
	}

	public int getGolesFReal() {
		return golesFReal;
	}

	public void setGolesFReal(int golesFReal) {
		this.golesFReal = golesFReal;
	}
	
	
	

}
