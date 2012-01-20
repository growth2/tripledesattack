/**
 * 
 */
package tpdc;

/**
 * @author andurke
 *
 */
public class Zone {
	private int num = 0;
	private String name = "N/A";
	private String type = "N/A";
	private String peerAdr = "N/A";
	private String integration = "N/A";
	
	public Zone(){
		
		
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPeerAdr() {
		return peerAdr;
	}

	public void setPeerAdr(String peerAdr) {
		this.peerAdr = peerAdr;
	}

	public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}



}
