/**
 * 
 */
package tpdc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author andurke
 *
 */
public class VCS {

		private String searchString;
		private Scanner xconfScanner;
		private Scanner xstatScanner;
		private ArrayList<Endpoint> endpoints = new ArrayList<Endpoint>();
		private String xconf;
		private String xstat;
		private int numEP;
		private int numUniqueEP;
		
		
		public VCS(String xconf, String xstat){
			this.xconf = xconf;
			this.xstat = xstat;
			initializeRead();
			populateEndpoints();
		}
		
		private void initializeRead(){
			try {
				FileInputStream fstreamXconf = new FileInputStream(xconf);
				DataInputStream inXconf = new DataInputStream(fstreamXconf);
				BufferedReader brXconf = new BufferedReader(new InputStreamReader(inXconf));
				FileInputStream fstreamXstat = new FileInputStream(xstat);
				DataInputStream inXstat = new DataInputStream(fstreamXstat);
				BufferedReader brXstat = new BufferedReader(new InputStreamReader(inXstat));
				xconfScanner = new Scanner(brXconf);
				xstatScanner = new Scanner(brXstat);
			} catch (Exception e){
				System.out.println("FILE NOT FOUND. TRY AGAIN.");
				e.printStackTrace();
			}
		}
		
		private void populateEndpoints(){
			initializeRead();
			ArrayList<String> endCfg = new ArrayList<String>(); 
			ArrayList<ArrayList<String>> endCfgArr = new ArrayList<ArrayList<String>>();
			while (xstatScanner.hasNextLine()){
				if ((xstatScanner.nextLine().indexOf("*s Registrations:")) != -1){
					break;
				}
			}
			String line;
			line = xstatScanner.nextLine().trim();
			if(line.contains("*s/end")){
				return;
			}
			endCfg.add(line); 
			line = xstatScanner.nextLine().trim();
			numEP++;
			do {
				if (line.contains("Registration ")) {
					numEP++;
					endCfgArr.add(endCfg);
					endCfg = new ArrayList<String>();
				}
				endCfg.add(line); 
				line = xstatScanner.nextLine().trim();
			}
			while(!line.contains("*s/end"));
			endCfgArr.add(endCfg);
			if (endCfg.size() == 0) return;
			
			for(int i = 0; i<numEP; i++){
				Endpoint endpoint = new Endpoint();
				endpoint = populateEndpointSimple(endCfgArr.get(i), endpoint);
				endpoint = populateEndpointAdv(endCfgArr.get(i), endpoint);
				endpoints.add(endpoint);
			}

			System.out.println("ARRAY: " + numEP + endCfg.toString());

			
		}
		private Endpoint populateEndpointSimple(ArrayList<String> endCfg, Endpoint endpoint){
				endpoint.setAuthenticated(searchEndCfg(endCfg, "Authenticated:"));
				endpoint.setProtocol(searchEndCfg(endCfg, "Protocol:"));
				endpoint.setNode(searchEndCfg(endCfg, "Node:"));
				endpoint.setVendor(searchEndCfg(endCfg, "VendorInfo:"));
			return endpoint;
		}
		
		private Endpoint populateEndpointAdv(ArrayList<String> endCfg, Endpoint endpoint){
			if(endpoint.getProtocol().equals("H323"))endpoint.setIpAddress((searchEndCfg(endCfg, "Address: ")).split(":")[0]);
			else endpoint.setIpAddress(searchEndCfg(endCfg, "Contact: ").split("@")[1].split(":")[0]);
			System.out.println(endpoint.getIpAddress());
			return endpoint;
		}
		
		private String searchEndCfg(ArrayList<String> endCfg, String search){
			for (String line : endCfg) {
			    if(line.contains(search))return cleanValidValue(line.split(" ")[1]);
			}
			return "";
		}
		

		
		private void numUniqueEP(ArrayList<String> endCfg){
			numUniqueEP = 0;
			for(int i=0; i<endCfg.size();i++){
				
			}
			for (String line : endCfg) {
			    if(line.contentEquals("Registration ")){
			    	
			    }
			}
			
		}
		
		public ArrayList<Endpoint> getEndpoints(){
			return endpoints;
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
			initializeRead();
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
			initializeRead();
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
				
				
				private void numEP(ArrayList<String> endCfg){
			numEP = 0;
			for (String line : endCfg) {
			    if(line.contains("Registration ")){
			    	numEP++;
			    }
			}
			//numUniqueEP(endCfg);
		}
		//			
//				while((xstatScanner.nextLine().indexOf("OutOfResources")) == -1){
//					String line = xstatScanner.nextLine();
//					endpoint.setProtocol(line.substring(line.indexOf("Protocol: "),line.length()));
//					while((xstatScanner.nextLine().indexOf(endpoint.getProtocol())) == -1){
//					
//					}
//					
//					xstatScanner.nextLine();
//					xstatScanner.nextLine();
//					xstatScanner.nextLine();
//					
//					
//				}
//				String key = xstatScanner.nextLine();
//				String searchString = "       Key: ";
//				key = key.substring(key.indexOf(searchString)+searchString.length(),key.length());
//				String desc = xstatScanner.nextLine();
//				searchString = "       Description: ";
//				desc = desc.substring(desc.indexOf(searchString)+searchString.length(),desc.length());
//				options.put(cleanValidValue(key), cleanValidValue(desc));
//			}
				//return options;

	*/
