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
	//private static ArrayList<VCS> vcsC;
	//private static ArrayList<VCS> vcsE;
	
	public CreateHTMLindex(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE) throws IOException{
		//this.vcsC = vcsC;
		//this.vcsE = vcsE;
		//buildHTML();
	}
	
	public static void buildHTML(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE) throws IOException{
		String start = "<html><head><LINK href=\"style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body>";
		
		//Eksempel, hente ut ip-adresse fra endepunkt0 fra VCS0
		//String endpointIPaddress = vcsC.get(0).getEndpoints().get(0).getIpAddress();
		
		//Eksempel, hente ut Lync-adresse
		//String lyncAddress = vcsC.get(0).getZones().get(0).getPeerAdr();
		
		

	/*********************************************************************************************/					
		String body = "<p>The Farstad videoconferencing solution consist of:</p><ul><li>";
		
		if(!vcsC.isEmpty()) body+= "<a href=\"VCSC.html\">VCS Control</a></li>";
		if(!vcsE.isEmpty()) body+= "<a href=\"VCSE.html\">VCS Expressway</a></li>";
		if(vcsC.get(0).getTMSaddress() != "") body += "<a href=\"TMS.html\">TMS</a></li>";
		

	/*********************************************************************************************/						
											
							
							body += "<div id=\"footer\">" +
							"	<img src=\"images/ciscologo.jpg\" height=\"50px\" />" +
							"	<img src=\"images/companylogo.jpg\" height=\"50px\" />" +
							"</div>" +
							"</div>" +
						"</div>";
	
		String slutt = "</body></html>";
		
		FileWriter fstream = new FileWriter("index.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(CreateHTMLstatic.getStart());
		  out.write(body);
		  out.write(CreateHTMLstatic.getEnd());
		  //Close the output stream
		  out.close();
		  
	}
	

	
		
		
	
}
