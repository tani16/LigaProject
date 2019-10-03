package com.dani.daoimpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dani.dao.EstadisticasDao;
import com.dani.entidad.Equipos;
import com.dani.entidad.Estadisticas;
import com.dani.util.HibernateUtils;

public class EstadisticasDaoImpl implements EstadisticasDao{
	
	private Session session = null;
	
	public EstadisticasDaoImpl() {
		session = HibernateUtils.getSessionFactory().getCurrentSession();
	}
	

	@Override
	public Estadisticas getStatics(Equipos equipo) {
		
		return session.get(Estadisticas.class, equipo.getIdEquipo());
	}


	@Override
	public void updateRacha(Equipos equipo, String key) {
		Estadisticas stats = getStatics(equipo);
		if(key.equals("Victoria")) {
			if(stats.getRacha() >= 0) {
				stats.setRacha(stats.getRacha() + 0.05);
			}else {
				stats.setRacha(0.0);
			}
		}else if(key.equals("Empate")) {
			stats.setRacha(0.0);
		}else {
			if(stats.getRacha() <= 0) {
				stats.setRacha(stats.getRacha() - 0.05);
			}else {
				stats.setRacha(0.0);
			}
		}
		save(stats);
	}

	@Override
	public void save(Estadisticas stats) {

	//	session = HibernateUtils.getTransaction();
		session.save(stats);
	//	HibernateUtils.doCommit(session);
		
	}


	@Override
	public double getPresupuestoMedio() {
		Double presupuestoMedio = 0.0;
		
		String sql = "select sum(e.presupuesto)"
				+ " 	from Estadisticas e";

		Query<Double> query = session.createQuery(sql, Double.class);
		
		presupuestoMedio = query.uniqueResult()/20;
					
		return presupuestoMedio;
	}
	
	@Override
	public double getValorMercadoMedio() {
		Double VMMedio = 0.0;
		
		String sql = "select sum(e.valorMercado)"
				+ " 	from Estadisticas e";

		Query<Double> query = session.createQuery(sql, Double.class);
		
		VMMedio = query.uniqueResult()/20;
					
		return VMMedio;
	}

}
