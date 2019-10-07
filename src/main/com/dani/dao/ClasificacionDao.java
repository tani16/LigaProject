package com.dani.dao;

import java.util.List;

import com.dani.entidad.Clasificacion;
import com.dani.entidad.Equipos;

public interface ClasificacionDao {

	public Clasificacion getClasificacionByEquipo(Equipos equipo);

	public void save(Clasificacion clasificacion);
	
	public List<Clasificacion> getList();

	public void update(Clasificacion clasificacion);
	
	
}
