package com.dani.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dani.dao.ClasificacionDao;
import com.dani.entidad.Clasificacion;
import com.dani.entidad.Equipos;
import com.dani.util.HibernateUtils;

public class ClasificacionDaoImpl implements ClasificacionDao {

	private Session session = null;
	
	public ClasificacionDaoImpl() {
		session = HibernateUtils.getSessionFactory().getCurrentSession();
	}

	@Override
	public Clasificacion getClasificacionByEquipo(Equipos equipo) {
		
		return session.get(Clasificacion.class, equipo.getIdEquipo());
	}

	@Override
	public void save(Clasificacion clasificacion) {
	//	session = HibernateUtils.getTransaction();
		session.save(clasificacion);
	//	HibernateUtils.doCommit(session);		
	}
	
	public List<Clasificacion> getList(){
		List<Clasificacion> clasificacion = null;
		
		String sql = "select c"
				+ " 	from Clasificacion c "
			 	+ "	   order by puntos desc, diferencia desc, "
			 	+ "				marcados desc, recibidos asc";
		
		Query<Clasificacion> query = session.createQuery(sql, Clasificacion.class);
		
		clasificacion = query.getResultList();
					
		return clasificacion;
	}

	@Override
	public void update(Clasificacion clasificacion) {
		session.update(clasificacion);
		
	}
	

}
