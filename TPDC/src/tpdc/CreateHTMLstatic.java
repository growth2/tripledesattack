/**
 * 
 */
package tpdc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author andurke
 *
 */
public class CreateHTMLstatic {
	private static String start = "<html><head><LINK href=\"style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body>" +
			"<div id=\"logo\" align=\"center\">" +
			"<a href=\"http://www.atea.no\">" +
			"<img src=\"images/atealogo.jpg\" />" +
		"</a>" +
		"<p>Telepresence Network Documentation</p>" +
	"</div>" +
	
	"<div id=\"navbar-container\">" +
	"	<div id=\"navbar\" align=\"center\">" +
		"	<a href=\"index.html\">" +
		"		Overview" +
		"	</a>" +
		"	<a href=\"topology.html\">" +
		"		Topology" +
		"	</a>" +
		"	<!-- Other links here...-->" +
		"</div>" +
	"</div>" +
	
	"<div id=\"wrapper\">" +
		"<div id=\"content\">";
	
	private static String end = "<p></p><p></p><div id=\"footer\">" +
			"	<img src=\"images/ciscologo.jpg\" height=\"50px\" />" +
			"	<img src=\"images/companylogo.jpg\" height=\"50px\" />" +
			"</div>" +
			"</div>" +
			"</div></body></html>";
	
	public static void createHTMLstaticPages() throws IOException{
		createVCSCpage();
		createVCSEpage();
		createTMSpage();
	}
	public static String getStart(){
		return start;
	}
	public static String getEnd(){
		return end;
	}

	private static void createVCSCpage() throws IOException{	
		String body = "<h2>VCS Control</h2>" + 
		"<p>The Video Communication Server (VCS) is an integral part of the Cisco Total Solution and is the center of the video communications network, connecting the" +
		"benefits of video conferencing and telepresence to other communications environments including unified communications and IP Telephony networks." +
		"The Cisco Video Communication Server (VCS) Control Application provides SIP proxy and call control as well as H.323 gatekeeper services. VCS is the center " +
		"of the intelligent video communication network, connecting all infrastructure, management and endpoint devices and is key to interoperability with Unified " +
		"Communications and IP Telephony networks and VoIP devices.</p><p><a href=\"http://www.cisco.com/en/US/products/ps11337/index.html\"> Go to VCS on Cisco.com </a></p>";				
		
		FileWriter fstream = new FileWriter("VCSC.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(start);
		  out.write(body);
		  out.write(end);
		  out.close();
	}
	
	private static void createVCSEpage() throws IOException{

	/*********************************************************************************************/					
	
		String body = "<h2>VCS-Expressway</h2>" + 
		"The TANDBERG Video Communication Server (VCS), deployed with the Expressway Application, provides standards-based firewall traversal for SIP and H.323" + 
		"devices. VCS enables communication with the outside world regardless of SIP or H.323 protocol, enabling organizations to utilize all of their video " +
		"communication investments.</p><p><a href=\"http://www.cisco.com/en/US/products/ps11337/index.html\"> Go to VCS on Cisco.com </a></p>";
		

	/*********************************************************************************************/							
	
		FileWriter fstream = new FileWriter("VCSE.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(start);
		  out.write(body);
		  out.write(end);
		  out.close();
		
	}
	
	private static void createTMSpage() throws IOException{


	/*********************************************************************************************/					
		String body = "<h2>TelePresence Mangement Suite (TMS)</h2>" + 
		"<p>TMS is one management platform for all your video communication needs. Cisco TelePresence Management Suite (TMS) is scalable, easy-to-use and " +
		"integrates with existing applications to increase ROI. It provides complete visibility and centralized control for on-site and remote video systems. TMS " +
		"supports management, deployment, and scheduling of the entire video network, including telepresence, from one single product.</p> " +
		"<p><a href=\"http://www.cisco.com/en/US/products/ps11338/index.html\"> Go to TMS on Cisco.com </a></p>";
		

	/*********************************************************************************************/							
		
		FileWriter fstream = new FileWriter("TMS.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(start);
		  out.write(body);
		  out.write(end);
		  out.close();
		
	}
}
