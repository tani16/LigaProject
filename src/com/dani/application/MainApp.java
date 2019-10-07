package com.dani.application;

import java.io.IOException;

import org.hibernate.Session;

import com.dani.dao.EquiposDao;
import com.dani.dao.EstadisticasDao;
import com.dani.dao.JornadasDao;
import com.dani.dao.PartidosDao;
import com.dani.dao.ResultadosDao;
import com.dani.daoimpl.EquiposDaoImpl;
import com.dani.daoimpl.EstadisticasDaoImpl;
import com.dani.daoimpl.JornadasDaoImpl;
import com.dani.daoimpl.PartidosDaoImpl;
import com.dani.daoimpl.ResultadosDaoImpl;
import com.dani.entidad.Equipos;
import com.dani.entidad.Estadisticas;
import com.dani.entidad.Jornadas;
import com.dani.methods.PlayMethods;
import com.dani.util.HibernateUtils;

import twitter4j.TwitterException;

public class MainApp {
	
	public static void main(String[] args) throws IOException {
		
		Session session = HibernateUtils.getTransaction();
		AppPlayJornada.execute();
	}

	private static void pruebas() {
		ResultadosDao resultadosDao = new ResultadosDaoImpl();
		PartidosDao partidosDao = new PartidosDaoImpl();
		EquiposDao equiposDao = new EquiposDaoImpl();
		JornadasDao jornadasDao = new JornadasDaoImpl();
		EstadisticasDao estadisticasDao = new EstadisticasDaoImpl();
		
		Equipos equipoC = equiposDao.getEquipoById(7);
		Equipos equipoF = equiposDao.getEquipoById(6);
		Jornadas jornada = jornadasDao.nextJornada();
		
		Estadisticas staticsC = estadisticasDao.getStatics(equipoC);
		Estadisticas staticsF = estadisticasDao.getStatics(equipoF);
		
		Double phiF = PlayMethods.analizeStaticsFuera(staticsF,staticsC,jornada);
		Double phiC = PlayMethods.analizeStaticsCasa(staticsC,staticsF,jornada);
		
		
	}
}
