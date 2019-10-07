package com.dani.util;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;

import com.dani.entidad.Clasificacion;
import com.dani.entidad.Equipos;
import com.dani.entidad.Estadisticas;
import com.dani.entidad.Jornadas;
import com.dani.entidad.Partidos;
import com.dani.entidad.Resultados;

public class HibernateUtils {

	private static SessionFactory sessionFactory;
	
	
	public static synchronized void buildSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure(new File("hibernate.cfg.xml"));
			configuration.addAnnotatedClass(Clasificacion.class);
			configuration.addAnnotatedClass(Equipos.class);
			configuration.addAnnotatedClass(Jornadas.class);
			configuration.addAnnotatedClass(Partidos.class);
			configuration.addAnnotatedClass(Resultados.class);
			configuration.addAnnotatedClass(Estadisticas.class);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
	}
	
	public static void openSessionAndBindToThread() {
		Session session = sessionFactory.openSession();
		ThreadLocalSessionContext.bind(session);
	}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory==null)  {
			buildSessionFactory();
		}
		return sessionFactory;
	}
	
	
	public static void closeSessionAndUnbindFromThread() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
	  	if (session!=null) {
	  		session.close();
	  	}
	}
	
	public static void closeSessionFactory() {
		if ((sessionFactory!=null) && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}
	}
			
	public static Session getTransaction() {
		Session session = getSessionFactory().getCurrentSession();
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return session;
	}
	
	public static void doCommit(Session session) {
		session.getTransaction().commit();
		session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}
}
