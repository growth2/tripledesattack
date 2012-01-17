package tpdc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rot {

	
	private static String readAllTextInFile(String filepath){
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			return "File not Found";
		}
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		String text = "";
		try {
			while ((strLine = br.readLine()) != null)   {
				text+= strLine;
				//evt legg inn denne for å få linjeskift: text+= strLine + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}
