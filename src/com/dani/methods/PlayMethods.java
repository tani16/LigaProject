package com.dani.methods;

import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;

import com.dani.dao.ClasificacionDao;
import com.dani.dao.EstadisticasDao;
import com.dani.dao.JornadasDao;
import com.dani.dao.PartidosDao;
import com.dani.dao.ResultadosDao;
import com.dani.daoimpl.ClasificacionDaoImpl;
import com.dani.daoimpl.EstadisticasDaoImpl;
import com.dani.daoimpl.JornadasDaoImpl;
import com.dani.daoimpl.PartidosDaoImpl;
import com.dani.daoimpl.ResultadosDaoImpl;
import com.dani.entidad.Clasificacion;
import com.dani.entidad.Equipos;
import com.dani.entidad.Estadisticas;
import com.dani.entidad.Jornadas;
import com.dani.entidad.Partidos;
import com.dani.entidad.Resultados;

public class PlayMethods {
	
	private static JornadasDao jornadasDao = new JornadasDaoImpl();
	private static PartidosDao partidosDao = new PartidosDaoImpl();
	private static EstadisticasDao estadisticasDao = new EstadisticasDaoImpl();
	private static ResultadosDao resultadoDao = new ResultadosDaoImpl();
	
	private PlayMethods() {
		throw new IllegalStateException("Utility class");
	}

	public static Jornadas searchNextJornada() {
				
		return jornadasDao.nextJornada();
	}

	public static Partidos searchNextPartido(Jornadas jornada) {
		
		Resultados resultadoNulo = resultadoDao.getResultadoById(0);
		return partidosDao.nextPartido(jornada, resultadoNulo);
	}

	public static Estadisticas getStatics(Equipos equipo) {
		
		return estadisticasDao.getStatics(equipo);
	}

	public static double analizeStaticsCasa(Estadisticas staticsC, Estadisticas staticsF, Jornadas jornada) {
		
		Long golesTotalesCasa = resultadoDao.getGolesFavorCasa();
		Long partidosTotales = (jornada.getIdJornada() - 1) * 10L;
		
		final double CTE = (double)golesTotalesCasa/partidosTotales;
		
		Long partidosCasa = partidosDao.getPartidosCasa(staticsC.getEquipo(), jornada);		
		Long golesFavorCasa = resultadoDao.getGolesFavorCasa(staticsC.getEquipo());		
		double fuerzaAtaque = ((double)golesFavorCasa / partidosCasa) / CTE;
		
		Long golesContraFuera = resultadoDao.getGolesContraFuera(staticsF.getEquipo()); 
		Long partidosFuera = partidosDao.getPartidosFuera(staticsF.getEquipo(), jornada);
		double fuerzaDefensa = ((double)golesContraFuera / partidosFuera) / CTE;
		
		double phiEquipo = CTE * fuerzaAtaque * fuerzaDefensa;
		double phiPresupuesto = staticsC.getPresupuesto() / estadisticasDao.getPresupuestoMedio();
		phiPresupuesto = (phiPresupuesto > 2) ? 0.2 : phiPresupuesto * 0.1;
		double phiValorMercado = staticsC.getValorMercado() / estadisticasDao.getValorMercadoMedio();
		phiValorMercado = (phiValorMercado > 2) ? 0.2 : phiValorMercado *0.1;
		
		double phi = 0.70 * phiEquipo  +  phiPresupuesto + phiValorMercado;
		
		phi = (phi < 0) ? 0 : phi;
		
		return phi;
	}

	public static double analizeStaticsFuera(Estadisticas staticsF, Estadisticas staticsC, Jornadas jornada) {
		Long golesTotalesFuera = resultadoDao.getGolesFavorFuera();
		Long partidosTotales = (jornada.getIdJornada() - 1) * 10L;
		
		final double CTE = (double)golesTotalesFuera/partidosTotales;
		
		Long partidosFuera = partidosDao.getPartidosFuera(staticsF.getEquipo(), jornada);		
		Long golesFavorFuera = resultadoDao.getGolesFavorFuera(staticsF.getEquipo());		
		double fuerzaAtaque = ((double)golesFavorFuera / partidosFuera) / CTE;
				
		Long golesContraCasa = resultadoDao.getGolesContraCasa(staticsC.getEquipo()); 
		Long partidosCasa = partidosDao.getPartidosCasa(staticsC.getEquipo(), jornada);
		double fuerzaDefensa = ((double)golesContraCasa / partidosCasa) / CTE;
		
		double phiEquipo = CTE * fuerzaAtaque * fuerzaDefensa;
		double phiPresupuesto = staticsF.getPresupuesto() / estadisticasDao.getPresupuestoMedio();
		phiPresupuesto = (phiPresupuesto > 2) ? 0.2 : phiPresupuesto * 0.1;
		double phiValorMercado = staticsF.getValorMercado() / estadisticasDao.getValorMercadoMedio();
		phiValorMercado = (phiValorMercado > 2) ? 0.2 : phiValorMercado * 0.1;
		
		double phi = 0.70 * phiEquipo  +  phiPresupuesto + phiValorMercado;
		
		phi = (phi < 0) ? 0 : phi;
		
		return phi;
	}

	public static int calculateGoals(double phiC) {
		
		RandomDataGenerator rd = new RandomDataGenerator();
		
		return (int) rd.nextPoisson(phiC);
	}

	public static void logConsoleResultado(String post) {
		
		System.out.println(post);
		
	}

	public static void updateData(Partidos partido, Resultados resultado) {
		resultadoDao.save(resultado);		
		partidosDao.updateResult(partido);
		
		ClasificacionDao clasificacionDao = new ClasificacionDaoImpl();
		Clasificacion clasLocal = clasificacionDao.getClasificacionByEquipo(partido.getEquipoC());
		Clasificacion clasVisitante = clasificacionDao.getClasificacionByEquipo(partido.getEquipoF());
		
		clasLocal.setMarcados(clasLocal.getMarcados() + resultado.getGolesC());
		clasLocal.setRecibidos(clasLocal.getRecibidos() + resultado.getGolesF());
		clasLocal.setDiferencia(clasLocal.getMarcados() - clasLocal.getRecibidos());
		
		clasVisitante.setMarcados(clasVisitante.getMarcados() + resultado.getGolesF());
		clasVisitante.setRecibidos(clasVisitante.getRecibidos() + resultado.getGolesC());
		clasVisitante.setDiferencia(clasVisitante.getMarcados() - clasVisitante.getRecibidos());
		
		if(resultado.getGolesC() > resultado.getGolesF()) {
			clasLocal.setPuntos(clasLocal.getPuntos() + 3);
			estadisticasDao.updateRacha(partido.getEquipoC(), "Victoria");
			estadisticasDao.updateRacha(partido.getEquipoF(), "Derrota");
		}else if (resultado.getGolesC() < resultado.getGolesF()) {
			clasVisitante.setPuntos(clasVisitante.getPuntos() + 3);
			estadisticasDao.updateRacha(partido.getEquipoC(), "Derrota");
			estadisticasDao.updateRacha(partido.getEquipoF(), "Victora");
		}else {
			clasLocal.setPuntos(clasLocal.getPuntos() + 1);
			clasVisitante.setPuntos(clasVisitante.getPuntos() + 1);
			estadisticasDao.updateRacha(partido.getEquipoC(), "Empate");
			estadisticasDao.updateRacha(partido.getEquipoF(), "Empate");
		}
		
		clasificacionDao.save(clasLocal);
		clasificacionDao.save(clasVisitante);
		
	}

	public static void saveJornadaPlayed(Jornadas jornada) {
		jornada.setJugado(true);
		jornadasDao.save(jornada);
		
	}

	public static List<Clasificacion> getClasificacion() {
		ClasificacionDao clasificacionDao = new ClasificacionDaoImpl();
		return clasificacionDao.getList();
	}


}
