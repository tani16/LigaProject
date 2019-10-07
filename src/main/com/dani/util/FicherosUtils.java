package com.dani.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FicherosUtils {

	private FicherosUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static BufferedReader openFile () throws Exception {
		
		FileReader fileReader = null;
		
		try {
			fileReader = new FileReader("C:\\Users\\16dan\\Desktop\\LigaProject\\calendario.csv");
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		
		BufferedReader bufferedReader = null;
		
		try{
			bufferedReader = new BufferedReader(fileReader);
		}catch (Exception e) {
			throw new Exception();
		}
		return bufferedReader;
	}	
		
}
