package com.dani.daoimpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dani.dao.ResultadosDao;
import com.dani.entidad.Equipos;
import com.dani.entidad.Partidos;
import com.dani.entidad.Resultados;
import com.dani.util.HibernateUtils;

public class ResultadosDaoImpl implements ResultadosDao {

	private Session session = null;
	
	public ResultadosDaoImpl() {
		session = HibernateUtils.getSessionFactory().getCurrentSession();
	}
	
	
	@Override
	public Resultados getResultadoById(int id) {
		
		return session.get(Resultados.class, id);
	}


	@Override
	public void save(Resultados resultado) {
	//	session = HibernateUtils.getTransaction();
		session.save(resultado);
	//	HibernateUtils.doCommit(session);
		
	}


	@Override
	public void update(Resultados resultado) {
		session.update(resultado);
	}


	@Override
	public Long getGolesFavorCasa() {
		Long golesTotales = 0L;
		
		String sql = "select sum(r.golesCReal)"
				+ " 	from Resultados r";

		Query<Long> query = session.createQuery(sql, Long.class);
		
		golesTotales = query.uniqueResult();
					
		return golesTotales;
	}


	@Override
	public Long getGolesFavorCasa(Equipos equipo) {
		Long golesTotales = 0L;
		
		String sql = "select sum(r.golesCReal)"
				+ " 	from Resultados r, Partidos p"
				+ "    where p.equipoC = :equipoC"
				+ "      and r.idResultado = p.idResultado";

		Query<Long> query = session.createQuery(sql, Long.class);
		query.setParameter("equipoC", equipo);
		
		golesTotales = query.uniqueResult();
					
		return golesTotales;
	}


	@Override
	public Long getGolesContraFuera(Equipos equipo) {
		Long golesTotales = 0L;
		
		String sql = "select sum(r.golesCReal)"
				+ " 	from Resultados r, Partidos p"
				+ "    where p.equipoF = :equipoF"
				+ "      and r.idResultado = p.idResultado";

		Query<Long> query = session.createQuery(sql, Long.class);
		query.setParameter("equipoF", equipo);
		
		golesTotales = query.uniqueResult();
					
		return golesTotales;
	}
	
	@Override
	public Long getGolesFavorFuera() {
		Long golesTotales = 0L;
		
		String sql = "select sum(r.golesFReal)"
				+ " 	from Resultados r";

		Query<Long> query = session.createQuery(sql, Long.class);
		
		golesTotales = query.uniqueResult();
					
		return golesTotales;
	}


	@Override
	public Long getGolesFavorFuera(Equipos equipo) {
		Long golesTotales = 0L;
		
		String sql = "select sum(r.golesFReal)"
				+ " 	from Resultados r, Partidos p"
				+ "    where p.equipoF = :equipoF"
				+ "      and r.idResultado = p.idResultado";

		Query<Long> query = session.createQuery(sql, Long.class);
		query.setParameter("equipoF", equipo);
		
		golesTotales = query.uniqueResult();
					
		return golesTotales;
	}
	
	@Override
	public Long getGolesContraCasa(Equipos equipo) {
		Long golesTotales = 0L;
		
		String sql = "select sum(r.golesFReal)"
				+ " 	from Resultados r, Partidos p"
				+ "    where p.equipoC = :equipoC"
				+ "      and r.idResultado = p.idResultado";

		Query<Long> query = session.createQuery(sql, Long.class);
		query.setParameter("equipoC", equipo);
		
		golesTotales = query.uniqueResult();
					
		return golesTotales;
	}


}
