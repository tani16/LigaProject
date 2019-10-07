package com.dani.dao;

import com.dani.entidad.Equipos;
import com.dani.entidad.Resultados;

public interface ResultadosDao {

	public Resultados getResultadoById(int id);

	public void save(Resultados resultado);

	public void update(Resultados resultado);

	public Long getGolesFavorCasa();

	public Long getGolesFavorCasa(Equipos equipo);

	public Long getGolesContraFuera(Equipos equipo);

	public Long getGolesFavorFuera();

	public Long getGolesFavorFuera(Equipos equipo);

	public Long getGolesContraCasa(Equipos equipo);
	
}
