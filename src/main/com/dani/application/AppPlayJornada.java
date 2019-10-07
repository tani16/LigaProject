package com.dani.application;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import com.dani.dao.ClasificacionDao;
import com.dani.daoimpl.ClasificacionDaoImpl;
import com.dani.entidad.Clasificacion;
import com.dani.entidad.Estadisticas;
import com.dani.entidad.Jornadas;
import com.dani.entidad.Partidos;
import com.dani.entidad.Resultados;
import com.dani.methods.BufferMethods;
import com.dani.methods.ImageMethods;
import com.dani.methods.PlayMethods;
import com.dani.methods.TwitterMethods;
import com.dani.util.HibernateUtils;

import twitter4j.TwitterException;

class AppPlayJornada {

	private AppPlayJornada() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void execute() throws TwitterException, IOException {
		Session session = HibernateUtils.getTransaction();
		Jornadas jornada = PlayMethods.searchNextJornada();
		
		for(int i = 0; i < 10; i++) {	
			
			playMatch(jornada);
			
		}
		
		PlayMethods.saveJornadaPlayed(jornada);
		
		List<Clasificacion> clasificacion = PlayMethods.getClasificacion();
		ImageMethods.buildClasificacion(clasificacion);
		HibernateUtils.doCommit(session);
		
	}

	private static void playMatch(Jornadas jornada) {
		Partidos partido = PlayMethods.searchNextPartido(jornada);
			
		Estadisticas estadisticasC = PlayMethods.getStatics(partido.getEquipoC());				
		Estadisticas estadisticasF = PlayMethods.getStatics(partido.getEquipoF());
		
		double phiC = PlayMethods.analizeStaticsCasa(estadisticasC, estadisticasF, jornada);
		double phiF = PlayMethods.analizeStaticsFuera(estadisticasF, estadisticasC, jornada);
		
		Resultados resultado = new Resultados();
		resultado.setGolesC(PlayMethods.calculateGoals(phiC));
		resultado.setGolesF(PlayMethods.calculateGoals(phiF));
		
		partido.setIdResultado(resultado);
		PlayMethods.updateData(partido, resultado);
		
		String post = TwitterMethods.getPost(partido);
		PlayMethods.logConsoleResultado(post);

		try {
			BufferMethods.postOnBuffer(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
