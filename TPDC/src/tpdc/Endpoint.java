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
	private String protocol = "N/A";
	private String node = "N/A";
	private String authenticated = "N/A";
	private ArrayList<String> aliases;
	
	public Endpoint(){

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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
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

	public ArrayList<String> getAliases() {
		return aliases;
	}

	public void setAliases(ArrayList<String> aliases) {
		this.aliases = aliases;
	}


}
