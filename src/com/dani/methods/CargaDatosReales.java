package com.dani.methods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dani.dao.ClasificacionDao;
import com.dani.dao.EquiposDao;
import com.dani.dao.PartidosDao;
import com.dani.dao.ResultadosDao;
import com.dani.daoimpl.ClasificacionDaoImpl;
import com.dani.daoimpl.EquiposDaoImpl;
import com.dani.daoimpl.PartidosDaoImpl;
import com.dani.daoimpl.ResultadosDaoImpl;
import com.dani.entidad.Clasificacion;
import com.dani.entidad.Equipos;
import com.dani.entidad.Partidos;
import com.dani.entidad.Resultados;

public class CargaDatosReales {

	private CargaDatosReales() {
		throw new IllegalStateException("Utility class");
	}

	public static Map<String, List<String>> takeDataWeb(int jornada) throws IOException {
		
		Map<String, List<String>> datos = new HashMap<>();
		List<String> values = new ArrayList<>();
		
		String URL = "https://resultados.as.com/resultados/futbol/primera/2019_2020/jornada/regular_a_" + jornada + "/";
		
		Document doc = Jsoup.connect(URL).userAgent("Mozilla/5.0").timeout(100000).get();
		
		Elements resultados = doc.getElementsByClass("resultado");
		
		int aux = 1;
		for (Element resultado : resultados) {
			values = new ArrayList<String>();
			values.add(resultado.attr("title"));
			values.add(resultado.text());
			String key = "partido_" + aux;
			datos.put(key, values);
			aux++;
		}
		
		return datos;
	}

	public static void tratamientoDatos(List<String> rawData) {
		
		EquiposDao equiposDao = new EquiposDaoImpl();
		ResultadosDao resultadosDao = new ResultadosDaoImpl();
		PartidosDao partidosDao = new PartidosDaoImpl();
		ClasificacionDao clasificacionDao = new ClasificacionDaoImpl();

		String[] equipos = rawData.get(0).split(" - ");
		String nameC = equipos[0];
		String nameF  = equipos[1].replaceAll(" en directo", "");
		
		Equipos equipoC = equiposDao.getEquipoByAsName(nameC);
		Equipos equipoF = equiposDao.getEquipoByAsName(nameF);
		
		Partidos partido = partidosDao.getPartidoByEquipos(equipoC,equipoF);
		
		Resultados resultado = partido.getIdResultado();
		
		String[] goles = rawData.get(1).split(" - ");
		int golesC = Integer.parseInt(goles[0]);
		int golesF = Integer.parseInt(goles[1]);
		
		resultado.setGolesCReal(golesC);
		resultado.setGolesFReal(golesF);
		
		resultadosDao.update(resultado);
			
		Clasificacion clasLocal = clasificacionDao.getClasificacionByEquipo(partido.getEquipoC());
		Clasificacion clasVisitante = clasificacionDao.getClasificacionByEquipo(partido.getEquipoF());
		
		if(resultado.getGolesCReal() > resultado.getGolesFReal()) {
			clasLocal.setPuntosReal(clasLocal.getPuntosReal() + 3);
		}else if (resultado.getGolesCReal() < resultado.getGolesFReal()) {
			clasVisitante.setPuntosReal(clasVisitante.getPuntosReal() + 3);
		}else {
			clasLocal.setPuntosReal(clasLocal.getPuntosReal() + 1);
			clasVisitante.setPuntosReal(clasVisitante.getPuntosReal() + 1);
		}
		
		clasificacionDao.update(clasLocal);
		clasificacionDao.update(clasVisitante);
		
	
	}
	
}