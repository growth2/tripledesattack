/**
 * 
 */
package tpdc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


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
		return findValue(1);
	}
	
	public String getIPaddress(){
		searchString = "Ethernet 1 IP V4 Address: ";
		return findValue(1);
	}
	
	public String getSubnetmask(){
		searchString = "Ethernet 1 IP V4 SubnetMask: ";
		return findValue(1);
	}
	
	public String getGateway(){
		searchString = "IP Gateway: ";
		return findValue(1);
	}
	
	public String getHostname(){
		searchString = "IP DNS Hostname: ";
		return findValue(1);
	}
	
	public String getDomain(){
		searchString = "DNS Domain Name: ";
		return findValue(1);
	}
	
	public String getNTPserver(){
		searchString = "NTP Address: ";
		return findValue(1);
	}
	
	public String getTMSaddress(){
		searchString = "ExternalManager Address: ";
		return findValue(1);
	}
	
	public String getRegRestriction(){
		searchString = "Registration RestrictionPolicy Mode: ";
		return findValue(1);
	}
	
	public String getLoopDetection(){
		searchString = "Call Loop Detection Mode: ";
		return findValue(1);
	}
	
	public String getRoutedMode(){
		searchString = "Call Routed Mode: ";
		return findValue(1);
	}
	
	public String getCallToUnknownIP(){
		searchString = "Call Services CallsToUnknownIPAddresses: ";
		return findValue(1);
	}
	
	public String getH323mode(){
		searchString = "H323 Mode: ";
		return findValue(1);
	}
	
	public String getSIPmode(){
		searchString = "SIP Mode: ";
		return findValue(1);
	}
	
	public String getInterworkingMode(){
		searchString = "Interworking Mode: ";
		return findValue(1);
	}
	
	public String getFindMeMode(){
		searchString = "FindMe Mode: ";
		return findValue(1);
	}
	
	public String getOCSRelayMode(){
		searchString = "OCS Relay Mode: ";
		return findValue(1);
	}
	
	public String getOCSDomain(){
		searchString = "OCS Relay OCS Domain: ";
		return findValue(1);
	}
	
	public String getPresenceServerMode1(){
		searchString = "Presence Server Mode: ";
		return findValue(1);
	}
	
	public String getSoftwareVersion(){
		searchString = " Version: ";
		return findValue(2);
	}
	
	public String getReleasekey(){
		searchString = "ReleaseKey: ";
		return findValue(2);
	}
	
	public String getExpresswayMode(){
		searchString = "Expressway: ";
		return findValue(2);
	}
	
	public String getMaxNonTraversalCalls(){
		searchString = "NonTraversalCalls: ";
		return findValue(2);
	}
	
	public String getMaxTraversalCalls(){
		searchString = "TraversalCalls: ";
		return findValue(2);
	}

	public String getMaxRegistrations(){
		searchString = "Registrations: ";
		return findValue(2);
	}

	public String getSerialNumber(){
		searchString = "SerialNumber: ";
		return findValue(2);
	}

	public HashMap<String, String> getOptions(){
		HashMap<String, String> options = new HashMap<String, String>();
		while (xstatScanner.hasNextLine()){
			if ((xstatScanner.nextLine().indexOf("Options:")) != -1){
				break;
			}		
		}
		while((xstatScanner.nextLine().indexOf("*s/end")) == -1){
			String key = xstatScanner.nextLine();
			String searchString = "       Key: ";
			key = key.substring(key.indexOf(searchString)+searchString.length(),key.length());
			String desc = xstatScanner.nextLine();
			searchString = "       Description: ";
			desc = desc.substring(desc.indexOf(searchString)+searchString.length(),desc.length());
			options.put(cleanValidValue(key), cleanValidValue(desc));
		}
		return options;
	}
	
	private String findValue(int mode){
		xconfScanner.reset();
		xstatScanner.reset();
		Scanner currScanner;
		if(mode == 1)currScanner = xconfScanner;
		else currScanner = xstatScanner;
		String value = "";
		while (currScanner.hasNextLine()){
			String strLine = currScanner.nextLine();
			if (strLine.indexOf(searchString) != -1){
				value = strLine.substring(strLine.indexOf(searchString)+searchString.length(),strLine.length());
				break;
			}
		}
		return cleanValidValue(value);
	}
	
	
	
	private String cleanValidValue(String value){
		if (value == "")return "N/A";
		else {
			if(value.endsWith("\"") && value.startsWith("\"")){
				return value.substring(1, value.length()-1);
			}
			return value;
		}
	}
	
	
}
/*

	public HashMap<String, String> getOptions(){
		HashMap<String, String> options = new HashMap<String, String>();
		while (xstatScanner.hasNextLine()){
			if ((xstatScanner.nextLine().indexOf("Options:")) != -1){
				break;
			}		
		}
		while((xstatScanner.nextLine().indexOf("*s/end")) == -1){
			Scanner lineScanner;
			String key = xstatScanner.nextLine();
			lineScanner = new Scanner(key);
			lineScanner.useDelimiter("\"");
			key = lineScanner.next();
			String desc = xstatScanner.nextLine();
			System.out.println(desc);
			lineScanner = new Scanner(desc);
			lineScanner = lineScanner.useDelimiter("\"");
			desc = lineScanner.next();
			options.put(key, desc);
			
			
		}
		return options;
	}

*/