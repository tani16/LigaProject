package com.dani.daoimpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dani.dao.JornadasDao;
import com.dani.entidad.Jornadas;
import com.dani.util.HibernateUtils;

public class JornadasDaoImpl implements JornadasDao {

	private Session session;
	
	public JornadasDaoImpl() {
		session = HibernateUtils.getSessionFactory().getCurrentSession();
	}

	@Override
	public void createJornada(Jornadas jornada) {
	//	session = HibernateUtils.getTransaction();
		session.save(jornada);
	//	HibernateUtils.doCommit(session);
	}

	@Override
	public Jornadas nextJornada() {
		
		Jornadas jornada = null;
		
		String sql = "SELECT j" + 
				"       FROM Jornadas j " + 
				"	   WHERE j.isJugado = :isJugado" +
				"      and j.idJornada = (Select min(j1.idJornada) " +
				"							from Jornadas j1" +
				"                          where j1.isJugado = :isJugado)";
		Query<Jornadas> query = session.createQuery(sql, Jornadas.class);
		query.setParameter("isJugado", false);

		jornada = query.uniqueResult();
		
		return jornada;
	}

	@Override
	public void save(Jornadas jornada) {		
		session.save(jornada);
	}
}
