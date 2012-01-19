/**
 * 
 */
package tpdc;

import java.util.ArrayList;

/**
 * @author andurke
 *
 */
public class Endpoint {
	
	private String ipAddress = "N/A";
	private String vendor = "N/A";
	private String node = "N/A";
	private String authenticated = "N/A";
	private ArrayList<String> h323IDs = new ArrayList<String>();
	private String e164Alias = "N/A";
	private String sipURI = "N/A";
	private boolean sip = false;
	private boolean h323 = false;
	
	public Endpoint(){

	}
	
	public boolean isSip() {
		return sip;
	}

	public void setSip(boolean sip) {
		this.sip = sip;
	}

	public boolean isH323() {
		return h323;
	}

	public void setH323(boolean h323) {
		this.h323 = h323;
	}

	public ArrayList<String> getH323IDs() {
		return h323IDs;
	}

	public void setH323IDs(ArrayList<String> h323ids) {
		h323IDs = h323ids;
	}

	public String getE164Alias() {
		return e164Alias;
	}

	public void setE164Alias(String e164Alias) {
		this.e164Alias = e164Alias;
	}

	public String getSipURI() {
		return sipURI;
	}

	public void setSipURI(String sipURI) {
		this.sipURI = sipURI;
	}



	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(String authenticated) {
		this.authenticated = authenticated;
	}


}
