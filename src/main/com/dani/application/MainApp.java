package com.dani.application;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import com.dani.dao.ClasificacionDao;
import com.dani.daoimpl.ClasificacionDaoImpl;
import com.dani.daoimpl.EquiposDaoImpl;
import com.dani.daoimpl.EstadisticasDaoImpl;
import com.dani.daoimpl.JornadasDaoImpl;
import com.dani.daoimpl.PartidosDaoImpl;
import com.dani.daoimpl.ResultadosDaoImpl;
import com.dani.entidad.Clasificacion;
import com.dani.entidad.Equipos;
import com.dani.entidad.Estadisticas;
import com.dani.entidad.Jornadas;
import com.dani.methods.BufferMethods;
import com.dani.methods.ImageMethods;
import com.dani.methods.PlayMethods;
import com.dani.util.HibernateUtils;

import twitter4j.TwitterException;

public class MainApp {
	
	public static void main(String[] args) throws TwitterException, IOException {
		
		Session session = HibernateUtils.getTransaction();
		//AppCargaDatos.execute();
		AppPlayJornada.execute();
		
		
		//pruebas();
	}

	private static void pruebas() {
		// TODO Auto-generated method stub
		ResultadosDaoImpl resultadosDao = new ResultadosDaoImpl();
		PartidosDaoImpl partidosDao = new PartidosDaoImpl();
		EquiposDaoImpl equiposDao = new EquiposDaoImpl();
		JornadasDaoImpl jornadasDao = new JornadasDaoImpl();
		EstadisticasDaoImpl estadisticasDao = new EstadisticasDaoImpl();
		
//		Long goles = resultadosDao.getGolesTotalesCasa();
		
		Equipos equipoC = equiposDao.getEquipoById(7);
		Equipos equipoF = equiposDao.getEquipoById(6);
		Jornadas jornada = jornadasDao.nextJornada();
//		
//		partidosDao.getPartidosCasa(equipo,jornada);
		
//		Long goles = resultadosDao.getGolesFavorCasa(equipo);
		
//		Long goles = resultadosDao.getGolesContraFuera(equipo);
//		double phiVM = staticsC.getValorMercado() / (estadisticasDao.getValorMercadoMedio());
		
		Estadisticas staticsC = estadisticasDao.getStatics(equipoC);
		Estadisticas staticsF = estadisticasDao.getStatics(equipoF);
		
		Double phiF = PlayMethods.analizeStaticsFuera(staticsF,staticsC,jornada);
		Double phiC = PlayMethods.analizeStaticsCasa(staticsC,staticsF,jornada);
		
		
	}
}
