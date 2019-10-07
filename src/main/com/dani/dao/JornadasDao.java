package com.dani.dao;

import com.dani.entidad.Jornadas;

public interface JornadasDao {

	public void createJornada(Jornadas jornada);
	
	public Jornadas nextJornada();

	public void save(Jornadas jornada);
	
}
