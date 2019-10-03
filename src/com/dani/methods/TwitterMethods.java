package com.dani.methods;

import com.dani.entidad.Partidos;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterMethods {
	
	private final static String ENTER = "\r\n";
	
	private TwitterMethods() {
		throw new IllegalStateException("Utility class");
	}
	
	
	public static void postResult(String post) throws TwitterException {
		
		Twitter twitter = new TwitterFactory().getInstance();
		
		twitter.updateStatus("Prueba - " + post);
		
	}


	public static String getPost(Partidos partido) {
		
		StringBuilder post = new StringBuilder("Jornada ");
		post.append(partido.getIdJornada().getIdJornada());
		post.append(" " + ENTER);
		post.append(partido.getEquipoC().getTwitter() + " " + partido.getIdResultado().getGolesC());
		post.append(" - ");
		post.append(partido.getIdResultado().getGolesF() + " " + partido.getEquipoF().getTwitter());
		post.append(" " + ENTER);
		post.append("#" + partido.getEquipoC().getAbreviatura() + " #" + partido.getEquipoF().getAbreviatura() + ENTER);
		post.append("#LaLiga #LaLigaSantander #LFP #LigaBot " + ENTER );
		post.append("#RealMadrid #RealSociedad #Atleti #Athletic " + ENTER );

		
		return post.toString();
//		return "Beta - Jornada " + partido.getIdJornada().getIdJornada() + ENTER +
//			   partido.getEquipoC().getTwitter() + " " + partido.getIdResultado().getGolesC() + " - " +
//			   partido.getIdResultado().getGolesF() + " " + partido.getEquipoF().getTwitter();				
		
	}
}
