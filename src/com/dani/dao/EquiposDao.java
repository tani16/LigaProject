package com.dani.dao;

import com.dani.entidad.Equipos;

public interface EquiposDao {

	public Equipos getEquipoById(int id);

	public Equipos getEquipoByAsName(String nameC);
	
	
}
