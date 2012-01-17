/**
 * 
 */
package tpdc;

/**
 * @author andurke
 *
 */
public class Endpoint {
	
	private String ipAddress;
	private String vendor;
	
	public Endpoint(String ipAddress, String vendor){
		this.ipAddress = ipAddress;
		this.vendor = vendor;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getVendor() {
		return vendor;
	}

}
