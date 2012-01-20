/**
 * 
 */
package tpdc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author andurke
 *
 */
public class CreateHTMLindex {
	private ArrayList<VCS> vcsC;
	private ArrayList<VCS> vcsE;
	
	public CreateHTMLindex(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE) throws IOException{
		this.vcsC = vcsC;
		this.vcsE = vcsE;
		buildHTML();
	}
	
	public void buildHTML() throws IOException{
		String start = "<html><head><LINK href=\"style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body>";
		
		//Eksempel, hente ut ip-adresse fra endepunkt0 fra VCS0
		//String endpointIPaddress = vcsC.get(0).getEndpoints().get(0).getIpAddress();
		
		//Eksempel, hente ut Lync-adresse
		//String lyncAddress = vcsC.get(0).getZones().get(0).getPeerAdr();
		
		String body = "	<div id=\"logo\" align=\"center\">" +
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
							"<div id=\"content\"><p>" +

	/*********************************************************************************************/					
			
"<p>This is the documentation of the video</p>" +

	/*********************************************************************************************/						
											
							
							"</p><div id=\"footer\">" +
							"	<img src=\"images/ciscologo.jpg\" height=\"50px\" />" +
							"	<img src=\"images/companylogo.jpg\" height=\"50px\" />" +
							"</div>" +
							"</div>" +
						"</div>";
	
		String slutt = "</body></html>";
		
		FileWriter fstream = new FileWriter("index.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(start);
		  out.write(body);
		  out.write(slutt);
		  //Close the output stream
		  out.close();

		
	}
}
