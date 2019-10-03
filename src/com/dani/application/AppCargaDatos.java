package com.dani.application;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import com.dani.dao.EquiposDao;
import com.dani.dao.JornadasDao;
import com.dani.dao.PartidosDao;
import com.dani.dao.ResultadosDao;
import com.dani.daoimpl.EquiposDaoImpl;
import com.dani.daoimpl.JornadasDaoImpl;
import com.dani.daoimpl.PartidosDaoImpl;
import com.dani.daoimpl.ResultadosDaoImpl;
import com.dani.entidad.Equipos;
import com.dani.entidad.Jornadas;
import com.dani.entidad.Partidos;
import com.dani.entidad.Resultados;
import com.dani.util.FicherosUtils;

class AppCargaDatos {
	private static List<String> jornadasTXT = new ArrayList<>();
	private static int contadorJornadas = 0;
	
	private AppCargaDatos() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void execute() throws Exception {
		String linea;		

		BufferedReader lector = FicherosUtils.openFile();
		
		while((linea = lector.readLine()) != null) {
			
			for(int i = 0; i < 13; i++) {
				jornadasTXT.add(linea);
				linea = lector.readLine();
			}
			contadorJornadas++;
			obtienePartidos();
			
			
			jornadasTXT.clear();
		}
		
		lector.close();
	}

	private static void obtienePartidos() {
		String[] infoFecha;
		ResultadosDao resultadosDao = new ResultadosDaoImpl();
		JornadasDao jornadasDao = new JornadasDaoImpl();
		PartidosDao partidosDao = new PartidosDaoImpl();
		
		Resultados resultado = resultadosDao.getResultadoById(0);
		infoFecha = jornadasTXT.get(2).split(";");
		Jornadas jornada = new Jornadas(contadorJornadas, infoFecha[1], false);
		
		jornadasDao.createJornada(jornada);
				
		for(int i = 2; i < 12; i++) {
			String[] infoPartido;
			infoPartido = jornadasTXT.get(i).split(";");

			Equipos equipoC = devuelveEquipo(infoPartido[0]);
			Equipos equipoF = devuelveEquipo(infoPartido[2]);
			
			Partidos partido = new Partidos(i-1, jornada, equipoC, equipoF, resultado);
			partidosDao.create(partido);
			
		}
		
	}

	private static Equipos devuelveEquipo(String key) {
		
		EquiposDao equiposDao = new EquiposDaoImpl();
		
		Equipos equipo = null;
		int id = 0;
		
		switch (key) {
		case "Athletic":
			id = 1;
			break;
		case "Atletico":
			id = 2;
			break;	
		case "Leganes":
			id = 3;
			break;
		case "Osasuna":
			id = 4;
			break;
		case "Alaves":
			id = 5;
			break;
		case "Barcelona":
			id = 6;
			break;	
		case "Getafe":
			id = 7;
			break;
		case "Granada":
			id = 8;
			break;
		case "Levante":
			id = 9;
			break;
		case "Mallorca":
			id = 10;
			break;
		case "Celta":
			id = 11;
			break;
		case "Espanyol":
			id = 12;
			break;	
		case "Betis":
			id = 13;
			break;
		case "Real Madrid":
			id = 14;
			break;
		case "R. Sociedad":
			id = 15;
			break;
		case "Valladolid":
			id = 16;
			break;	
		case "Eibar":
			id = 17;
			break;
		case "Sevilla":
			id = 18;
			break;
		case "Valencia":
			id = 19;
			break;
		case "Villarreal":
			id = 20;
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
		
		equipo = equiposDao.getEquipoById(id);
		
		return equipo;
	}
}

