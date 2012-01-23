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
public class CreateHTMLtopology {
	//private ArrayList<VCS> vcsC;
	//private ArrayList<VCS> vcsE;
	
	public CreateHTMLtopology(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE) throws IOException{
		//this.vcsC = vcsC;
		//this.vcsE = vcsE;
		//buildHTML();
	}
	
	public static void buildHTML(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE) throws IOException{
		String body = "<h1>Infrastructure</h1><p align=\"center\"><table id=\"gradient-style\" summary=\"Infrastructure\">" +
			"<thead>" +
				"<tr>" +
					"<th scope=\"col\">Description</th>" +
					"<th scope=\"col\">Hostname</th>" +
					"<th scope=\"col\">IP-address</th>" +
					"<th scope=\"col\">Sip Domain</th>" +
					"<th scope=\"col\">Version</th>" +
				"</tr>" +
			"</thead>" +						
			"<tbody>"; 
			if (vcsC.isEmpty()==false){
				for(VCS eachVCS : vcsC){
				body += "<tr><td>VCS Control</td><td>" + eachVCS.getHostname() + "</td><td>" + eachVCS.getIPaddress() + "</td><td>" + eachVCS.getSipDomain() + "</td><td>" + eachVCS.getSoftwareVersion() + "</td></tr>";
				}
			}
			if (vcsE.isEmpty()==false){
				body += "<tr><td>VCS Expressway</td><td>" + vcsE.get(0).getHostname() + "</td><td>" + vcsE.get(0).getIPaddress() + "</td><td>" + vcsE.get(0).getSipDomain() + "</td><td>" + vcsE.get(0).getSoftwareVersion() + "</td></tr>";
			}

			body += "</tbody>" +
		"</table></p>" +
		
		"<h1>Endpoints</h1><p align=\"center\"><table id=\"gradient-style\" summary=\"Infrastructure\">" +
		"<thead>" +
			"<tr>" +
				"<th scope=\"col\">IP-address</th>" +
				"<th scope=\"col\">E.164</th>" +
				"<th scope=\"col\">H.323</th>" +
				"<th scope=\"col\">Sip</th>" +
			"</tr>" +
		"</thead>" +						
		"<tbody>";
		/***pseudocode***/
		for(Endpoint eachEP : vcsC.get( 0).getEndpoints()){
			body += "<tr><td>" + eachEP.getIpAddress() + "</td><td>" + eachEP.getE164Alias() + "</td><td>" + eachEP.getH323IDs() + "</td><td>" + eachEP.getSipURI() + "</td></tr>";
		/***pseudocode***/
		}
		body += "</tbody>" +
		"</table></p>" +
		
			
	/*********************************************************************************************/
				
					"</p>" +
			"</div>" +
		"</div>";
				
	
	
		String slutt = "</body></html>";
		
		FileWriter fstream = new FileWriter("topology.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(CreateHTMLstatic.getStart());
		  out.write(body);
		  out.write(slutt);
		  //Close the output stream
		  out.close();

		
	}
}