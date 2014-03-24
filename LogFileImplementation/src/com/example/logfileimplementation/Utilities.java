package com.example.logfileimplementation;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class Utilities {
	public static String APP_DIR = MainActivity.SDCARD+"/Logfile";
	public static String LOG_FILE_PATH = APP_DIR+"/ myapp_log.txt"; 	
	
	
	
	public static void writeIntoLog(String data) 
	{
		 
		FileWriter fw = null;    
		try {
		 
			fw = new FileWriter(LOG_FILE_PATH , true);
			BufferedWriter buffer = new BufferedWriter(fw);   
			buffer.append(data+"\n");
			 
			buffer.close();

		} catch (Exception e) { 
			e.printStackTrace();
		}
		 
	}  
}
