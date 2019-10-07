package com.dani.dao;

import com.dani.entidad.Equipos;
import com.dani.entidad.Estadisticas;

public interface EstadisticasDao {

	public Estadisticas getStatics(Equipos equipo);

	public void updateRacha(Equipos equipo, String key);
	
	public void save(Estadisticas stats);

	public double getPresupuestoMedio();

	public double getValorMercadoMedio();
}
