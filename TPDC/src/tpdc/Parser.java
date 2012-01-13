/**
 * 
 */
package tpdc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * @author andurke
 *
 */
public class Parser {
	private String searchString;
	private Scanner xconfScanner;
	private Scanner xstatScanner;
	
	public Parser(String xconf, String xstat) throws FileNotFoundException{
		FileInputStream fstreamXconf = new FileInputStream(xconf);
		DataInputStream inXconf = new DataInputStream(fstreamXconf);
		BufferedReader brXconf = new BufferedReader(new InputStreamReader(inXconf));
		FileInputStream fstreamXstat = new FileInputStream(xstat);
		DataInputStream inXstat = new DataInputStream(fstreamXstat);
		BufferedReader brXstat = new BufferedReader(new InputStreamReader(inXstat));
		xconfScanner = new Scanner(brXconf);
		xstatScanner = new Scanner(brXstat);
	}
	
	public String getSipDomain(){
		searchString = "SIP Domains Domain 1 Name: ";
		String strLine;
		String value = "";
		while (xconfScanner.hasNextLine()){
			strLine = xconfScanner.nextLine();
			int test = strLine.indexOf(searchString);
			if (test != -1){
				value = strLine.substring(test+searchString.length(),strLine.length());
			}
		}
		return validValue(value);
	}
	private String validValue(String value){
		if (value != "")return value;
		else return "N/A";
	}
	
	
}
