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
		
		
		
		String body = "	<div id=\"logo\" align=\"center\">" +
							"<a href=\"http://www.atea.no\">" +
								"<img src=\"images/atealogo.jpg\" />" +
							"</a>" +
							"<p>Telepresence Network Documentation</p>" +
						"</div>" +
						
						"<div id=\"navbar-container\">" +
						"	<div id=\"navbar\" align=\"center\">" +
							"	<a href=\"index.html\">" +
							"		Oversikt" +
							"	</a>" +
							"	<a href=\"topology.html\">" +
							"		Topologi" +
							"	</a>" +
							"	<!-- Other links here...-->" +
							"</div>" +
						"</div>" +
						
						"<div id=\"wrapper\">" +
							"<div id=\"content\"><p>" +

	/*********************************************************************************************/					
			
"<p>Sip domain is: " + vcsC.get(0).getSipDomain() + "</p>" +
"<p>Version is: " + vcsC.get(0).getSoftwareVersion() + "</p>" +
		"<p align=\"center\"><table id=\"gradient-style\" summary=\"Meeting Results\">" +
			"<thead>" +
				"<tr>" +
					"<th scope=\"col\">Employee</th>" +
					"<th scope=\"col\">Division</th>" +
					"<th scope=\"col\">Suggestions</th>" +
					"<th scope=\"col\">Rating</th>" +
				"</tr>" +
			"</thead>" +						
			"<tbody>" +
				"<tr><td>Stephen C. Cox</td><td>Marketing</td><td>Make discount offers</td><td>3/10</td></tr>" +
				"<tr><td>Josephin Tan</td><td>Advertising</td><td>Give bonuses</td><td>5/10</td></tr>" +
				"<tr><td>Joyce Ming</td><td>Marketing</td><td>New designs</td><td>8/10</td></tr>" +
				"<tr><td>James A. Pentel</td><td>Marketing</td><td>Better Packaging</td><td>8/10</td></tr>" +
			"</tbody>" +
		"</table></p>" +

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
