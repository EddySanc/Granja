/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JOptionPane;

public class Crear {

	//private static final String FILENAME = Crear.class.getSimpleName() + ".class"+"\\filename.txt";
        	private static final String FILENAME = "ruta.txt";
             

            public static void escribir(String puerto){

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content =puerto;
                        //String r=System.getProperty("user.dir"); 
                        
                                
                        //File miDir = new File (Crear.class.getResource("/ini/ruta.txt").getFile());
                        File miDir=new File("ruta.txt");
                        //String r=miDir.getAbsolutePath()+FILENAME;
                        
                        
                       // JOptionPane.showMessageDialog(null, "Ruta donde guardar: "+miDir.getPath());
			fw = new FileWriter(miDir.getPath());
			bw = new BufferedWriter(fw);
			bw.write(content);

			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
            
            /*Lectura*/
            public static String leerPuerto()
            {

                //File miDir = new File (Crear.class.getResource("/ini/ruta.txt").getFile());
                 File miDir = new File ("ruta.txt");
                //String rx=miDir.getAbsolutePath()+FILENAME;
                        
              
                //JOptionPane.showMessageDialog(null, "Nuevo port: " +miDir.getPath());
                try(BufferedReader br = new BufferedReader(new FileReader(miDir.getPath()))) 
                {

    StringBuilder sb = new StringBuilder();
    String line = br.readLine();

    while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
    }
    
    String everything = sb.toString();
    return everything;
}catch(Exception r)
{
    r.fillInStackTrace();
    return null;
}
                
                
            }
        
     

}