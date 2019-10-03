package com.dani.daoimpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dani.dao.PartidosDao;
import com.dani.entidad.Equipos;
import com.dani.entidad.Jornadas;
import com.dani.entidad.Partidos;
import com.dani.entidad.Resultados;
import com.dani.util.HibernateUtils;

public class PartidosDaoImpl implements PartidosDao {

	private Session session = null;
	
	public PartidosDaoImpl() {
		session = HibernateUtils.getSessionFactory().getCurrentSession();
	}

	@Override
	public void create(Partidos partido) {
	//	session = HibernateUtils.getTransaction();
		session.save(partido);
	//	HibernateUtils.doCommit(session);
	}

	@Override
	public Partidos nextPartido(Jornadas jornada, Resultados resultadoNulo) {
		Partidos partido = null;
		
		String sql = "select p"
				+ " 	from Partidos p "
			 	+ "	   where p.idJornada = :idJornada"
			 	+ "      and p.idPartido = (select min(p1.idPartido)"
			 	+ " 						  from Partidos p1"
			 	+ "							 where p1.idJornada = :idJornada"
			 	+ "							   and p1.idResultado = :idResultado)";
		Query<Partidos> query = session.createQuery(sql, Partidos.class);
		query.setParameter("idJornada", jornada);
		query.setParameter("idResultado", resultadoNulo);
		
		partido = query.uniqueResult();
					
		return partido;
	}

	@Override
	public void updateResult(Partidos partido) {
	//	session = HibernateUtils.getTransaction();
		session.update(partido);
	//	HibernateUtils.doCommit(session);
		
	}

	@Override
	public Partidos getPartidoByEquipos(Equipos equipoC, Equipos equipoF) {
		Partidos partido = null;
		
		String sql = "select p"
				+ " 	from Partidos p "
			 	+ "	   where p.equipoC = :equipoC"
			 	+ "      and p.equipoF = :equipoF";
		
		Query<Partidos> query = session.createQuery(sql, Partidos.class);
		query.setParameter("equipoC", equipoC);
		query.setParameter("equipoF", equipoF);
		
		partido = query.uniqueResult();
					
		return partido;
	}

	@Override
	public Long getPartidosCasa(Equipos equipo, Jornadas jornada) {
		
		Long partidos = 0L;
		
		String sql = "select count(p.idJornada)"
				+ " 	from Partidos p "
			 	+ "	   where p.equipoC = :equipoC"
				+ "      and p.idJornada < :jornada";
		
		Query<Long> query = session.createQuery(sql, Long.class);
		query.setParameter("equipoC", equipo);
		query.setParameter("jornada", jornada);
		
		partidos = query.uniqueResult();
					
		return partidos;
	}
	
	public Long getPartidosFuera(Equipos equipo, Jornadas jornada) {
		
		Long partidos = 0L;
		
		String sql = "select count(p.idJornada)"
				+ " 	from Partidos p "
			 	+ "	   where p.equipoF = :equipoF"
				+ "      and p.idJornada < :jornada";
		
		Query<Long> query = session.createQuery(sql, Long.class);
		query.setParameter("equipoF", equipo);
		query.setParameter("jornada", jornada);
		
		partidos = query.uniqueResult();
					
		return partidos;
	}

	


}
