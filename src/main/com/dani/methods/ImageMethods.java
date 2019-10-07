package com.dani.methods;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.dani.entidad.Clasificacion;

public class ImageMethods {
	
	private static final String RUTA_CLAS = "C:\\Users\\16dan\\Desktop\\LigaProject\\Clasificacion\\";
	private static final String RUTA_TEMP = "C:\\Users\\16dan\\Desktop\\LigaProject\\Clasificacion\\Temporal\\";
	private static final String RUTA_ESCUDOS = "C:\\Users\\16dan\\Desktop\\LigaProject\\Escudos\\";
	private static final int WIDTH_ROW = 500;
	private static final int HEIGHT_ROW = 40;
	private static final int INIT_ROW_X = 0;
	private static final int INIT_ROW_Y = 0;
	private static final int WIDTH_C1 = 40;
	private static final int WIDTH_C2 = 60;
	private static final int WIDTH_C3 = 220;
	private static final int WIDTH_C4 = 80;
	private static final int WIDTH_C5 = 50;
	private static final int WIDTH_C6 = 50;
	private static final int INIT_C1 = INIT_ROW_X;
	private static final int INIT_C2 = INIT_C1 + WIDTH_C1;
	private static final int INIT_C3 = INIT_C2 + WIDTH_C2;
	private static final int INIT_C4 = INIT_C3 + WIDTH_C3;
	private static final int INIT_C5 = INIT_C4 + WIDTH_C4;
	private static final int INIT_C6 = INIT_C5 + WIDTH_C5;
	private static final int INC_Y = 30;
	private static final int INC_X = 20;
	private static final int INC_X_PUNTOS = 30;
	
	private ImageMethods() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void buildClasificacion(List<Clasificacion> clasificados) throws IOException {
		createCabecera();
		for(int i = 0; i < 20 && i < clasificados.size(); i++) {	
			BufferedImage registro = createRegistro(clasificados.get(i), i+1);
			saveGraphic(RUTA_TEMP, "row" + (i + 1), registro);
		}
		
		montarClasificacion();
	}
	
	private static BufferedImage createRegistro(Clasificacion clasificado, int posicion) throws IOException {
		BufferedImage registro = createRow();		
		Graphics g = registro.getGraphics();
		g.setColor(Color.BLACK);
		
		g.drawString(String.valueOf(posicion),INIT_C1 + INC_X/2, INC_Y);
		BufferedImage escudo = ImageIO.read(new File(RUTA_ESCUDOS + clasificado.getEquipo().getAbreviatura() + ".png"));
		g.drawImage(escudo, INIT_C2 + INC_X/2, INIT_ROW_Y + 2, WIDTH_C2 - INC_X, HEIGHT_ROW - 4, null);	
		g.drawString(clasificado.getEquipo().getNombre(), INIT_C3 + INC_X , INC_Y);
		g.drawString(String.valueOf(clasificado.getPuntos()), INIT_C4 + (INC_X_PUNTOS), INC_Y);
		g.drawString(String.valueOf(clasificado.getMarcados()), INIT_C5 + INC_X, INC_Y);
		g.drawString(String.valueOf(clasificado.getRecibidos()), INIT_C6 + INC_X, INC_Y);
		
		return registro;
		
	}

	public static void montarClasificacion() throws IOException {
		BufferedImage table = new BufferedImage(WIDTH_ROW, HEIGHT_ROW * 21, BufferedImage.TYPE_INT_ARGB);
		Graphics g = table.getGraphics();
		g.setColor(Color.black);
		
		BufferedImage cabecera = ImageIO.read(new File(RUTA_TEMP + "Cabecera.png"));
		g.drawImage(cabecera, INIT_ROW_X, HEIGHT_ROW * (0), WIDTH_ROW, HEIGHT_ROW, Color.WHITE, null);
		
		for(int i = 1; i <= 4; i++) {
			BufferedImage row = ImageIO.read(new File(RUTA_TEMP + "row" + i + ".png"));
			g.drawImage(row, INIT_ROW_X, HEIGHT_ROW *i, WIDTH_ROW, HEIGHT_ROW, Color.GREEN, null);
		}
		for(int i = 5; i <= 6; i++) {
			BufferedImage row = ImageIO.read(new File(RUTA_TEMP + "row" + i + ".png"));
			g.drawImage(row, INIT_ROW_X, HEIGHT_ROW *i, WIDTH_ROW, HEIGHT_ROW, Color.BLUE, null);
		}
		for(int i = 7; i <= 17; i++) {
			BufferedImage row = ImageIO.read(new File(RUTA_TEMP + "row" + i + ".png"));
			g.drawImage(row, INIT_ROW_X, HEIGHT_ROW *i, WIDTH_ROW, HEIGHT_ROW, Color.WHITE, null);
		}
		for(int i = 18; i <= 20; i++) {
			BufferedImage row = ImageIO.read(new File(RUTA_TEMP + "row" + i + ".png"));
			g.drawImage(row, INIT_ROW_X, HEIGHT_ROW *i, WIDTH_ROW, HEIGHT_ROW, Color.RED, null);
		}
		
		saveGraphic(RUTA_CLAS, "Clasificacion", table);
	}

	private static BufferedImage createRow() {
		
		BufferedImage row = new BufferedImage(WIDTH_ROW, HEIGHT_ROW, BufferedImage.TYPE_INT_ARGB);
		Graphics g = row.getGraphics();
		g.setColor(Color.BLACK);
		
		//Dibujamos Rectangulo total
		g.drawRect(INIT_ROW_X, INIT_ROW_Y, WIDTH_ROW, HEIGHT_ROW);
		//Dibujamos las columnas
		g.drawRect(INIT_C2, INIT_ROW_Y, WIDTH_C2, HEIGHT_ROW);
		g.drawRect(INIT_C3, INIT_ROW_Y, WIDTH_C3, HEIGHT_ROW);
		g.drawRect(INIT_C4, INIT_ROW_Y, WIDTH_C4, HEIGHT_ROW);
		g.drawRect(INIT_C5, INIT_ROW_Y, WIDTH_C5, HEIGHT_ROW);
		g.drawRect(INIT_C6, INIT_ROW_Y, WIDTH_C6, HEIGHT_ROW);
			
		return row;
	}
	
	private static void createCabecera() throws IOException {
		BufferedImage cabecera = createRow();		
		Graphics g = cabecera.getGraphics();
		g.setColor(Color.BLACK);
		
		
		g.drawString("Equipo", INIT_C3 + INC_X , INC_Y);
		g.drawString("Puntos", INIT_C4 + INC_X, INC_Y);
		g.drawString("G.F", INIT_C5 + INC_X, INC_Y);
		g.drawString("G.C", INIT_C6 + INC_X, INC_Y);
		
		saveGraphic(RUTA_TEMP, "Cabecera", cabecera);
	}
	
	private static void saveGraphic(String ruta, String name, BufferedImage img) throws IOException {
	
		ImageIO.write(img, "PNG", new File(ruta + name + ".png"));
	}
}
