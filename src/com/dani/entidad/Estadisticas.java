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
@Table(name = "estadisticas")
public class Estadisticas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 981457801132134570L;
	
	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idequipo")
	private Equipos equipo;
	
	@Column(name = "gfcasa")
	private double gfCasa;
	
	@Column(name = "gffuera")
	private double gfFuera;
	
	@Column(name = "gccasa")
	private double gcCasa;
	
	@Column(name = "gcfuera")
	private double gcFuera;
	
	@Column(name = "racha")
	private double racha;
	
	@Column(name = "presupuesto")
	private double presupuesto;
	
	@Column(name = "posicionanterior")
	private double posicionAnterior;
	
	@Column(name = "valormercado")
	private double valorMercado;

	public Estadisticas() {}
	
	public Estadisticas(Equipos equipo, double gfCasa, double gfFuera, double gcCasa, 
			double gcFuera, double racha, double presupuesto, 
			double posicionAnterior, double valorMercado) {
		super();
		this.equipo = equipo;
		this.gfCasa = gfCasa;
		this.gfFuera = gfFuera;
		this.gcCasa = gcCasa;
		this.gcFuera = gcFuera;
		this.racha = racha;
		this.presupuesto = presupuesto;
		this.posicionAnterior = posicionAnterior;
		this.valorMercado = valorMercado;
	}

	public Equipos getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}

	public double getGfCasa() {
		return gfCasa;
	}

	public void setGfCasa(double gfCasa) {
		this.gfCasa = gfCasa;
	}

	public double getGfFuera() {
		return gfFuera;
	}

	public void setGfFuera(double gfFuera) {
		this.gfFuera = gfFuera;
	}

	public double getGcCasa() {
		return gcCasa;
	}

	public void setGcCasa(double gcCasa) {
		this.gcCasa = gcCasa;
	}

	public double getGcFuera() {
		return gcFuera;
	}

	public void setGcFuera(double gcFuera) {
		this.gcFuera = gcFuera;
	}

	public double getRacha() {
		return racha;
	}

	public void setRacha(double racha) {
		this.racha = racha;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public double getPosicionAnterior() {
		return posicionAnterior;
	}

	public void setPosicionAnterior(double posicionAnterior) {
		this.posicionAnterior = posicionAnterior;
	}

	public double getValorMercado() {
		return valorMercado;
	}

	public void setValorMercado(double valorMercado) {
		this.valorMercado = valorMercado;
	}
}
