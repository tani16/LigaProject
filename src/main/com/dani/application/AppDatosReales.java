package com.dani.application;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.dani.methods.CargaDatosReales;
import com.dani.util.HibernateUtils;

public class AppDatosReales {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.getTransaction();
		cargaDatosReales(6);
		HibernateUtils.doCommit(session);
	}

	private static void cargaDatosReales(int jornada) throws IOException {
		
		Map<String, List<String>> datosAs = CargaDatosReales.takeDataWeb(jornada);
		
		for(int i = 1; i <= 10; i++) {
			
			List<String> rawData = datosAs.get("partido_" + i);
			CargaDatosReales.tratamientoDatos(rawData);
			
		}
		
	}

}
