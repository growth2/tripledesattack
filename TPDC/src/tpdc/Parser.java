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
		return findValue();
	}
	
	public String getIPaddress(){
		searchString = "Ethernet 1 IP V4 Address: ";
		return findValue();
	}
	
	public String getSubnetmask(){
		searchString = "Ethernet 1 IP V4 SubnetMask: ";
		return findValue();
	}
	
	public String getGateway(){
		searchString = "IP Gateway: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "IP DNS Hostname: ";
		return findValue();
	}
	
	public String getDomain(){
		searchString = "DNS Domain Name: ";
		return findValue();
	}
	
	public String getNTPserver(){
		searchString = "NTP Address: ";
		return findValue();
	}
	
	public String getTMSaddress(){
		searchString = "ExternalManager Address: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "SystemUnit Name: ";
		return findValue();
	}
	
	public String getRegRestriction(){
		searchString = "Registration RestrictionPolicy Mode: ";
		return findValue();
	}
	
	public String getLoopDetection(){
		searchString = "Call Loop Detection Mode: ";
		return findValue();
	}
	
	public String getRoutedMode(){
		searchString = "Call Routed Mode: ";
		return findValue();
	}
	
	public String getCallToUnknownIP(){
		searchString = "Call Services CallsToUnknownIPAddresses: ";
		return findValue();
	}
	
	public String getH323mode(){
		searchString = "H323 Mode: ";
		return findValue();
	}
	
	public String getSIPmode(){
		searchString = "SIP Mode: ";
		return findValue();
	}
	
	public String getInterworkingMode(){
		searchString = "Interworking Mode: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "SystemUnit Name: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "SystemUnit Name: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "SystemUnit Name: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "SystemUnit Name: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "SystemUnit Name: ";
		return findValue();
	}
	
	public String getHostname(){
		searchString = "SystemUnit Name: ";
		return findValue();
	}
	
	private String findValue(){
		String value = "";
		while (xconfScanner.hasNextLine()){
			String strLine = xconfScanner.nextLine();
			if (strLine.indexOf(searchString) != -1){
				value = strLine.substring(strLine.indexOf(searchString)+searchString.length(),strLine.length());
			}
		}
		return validValue(value);
	}
	
	private String validValue(String value){
		if (value != "")return value;
		else return "N/A";
	}
	
	
}
