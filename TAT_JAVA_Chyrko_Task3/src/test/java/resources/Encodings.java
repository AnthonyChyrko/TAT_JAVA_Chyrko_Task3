package test.java.resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Encodings {	
	
	public static StringBuilder readFileWithCharset(String file, String charset){
		FileInputStream fis;
		StringBuilder sb = new StringBuilder();
		try {
			fis = new FileInputStream(file);			
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis, Charset.forName(charset)));
			int i=-1;		
	            while((i=reader.read())!=-1){
	            	
	            	sb.append((char)i);            	
	            } 			
         fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb;		
	}
}
