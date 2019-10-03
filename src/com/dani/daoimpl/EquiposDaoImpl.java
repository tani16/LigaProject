package com.dani.daoimpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dani.dao.EquiposDao;
import com.dani.entidad.Equipos;
import com.dani.util.HibernateUtils;

public class EquiposDaoImpl implements EquiposDao {

	private Session session = null;
	
	public EquiposDaoImpl() {
		session = HibernateUtils.getSessionFactory().getCurrentSession();
	}
	
	
	@Override
	public Equipos getEquipoById(int id) {
		
		return session.get(Equipos.class, id);
	}


	@Override
	public Equipos getEquipoByAsName(String name) {
		Equipos equipo = null;
		
		String sql = "select e"
				+ "		from Equipos e"
				+ "	   where e.nameAs = :name";
		
		Query<Equipos> query = session.createQuery(sql, Equipos.class);
		query.setParameter("name", name);
		
		equipo = query.uniqueResult();
				
				
		return equipo;
	}

}
