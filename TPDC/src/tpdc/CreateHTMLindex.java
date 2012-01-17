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
		
		
		
		String test = "	<div id=\"logo\" align=\"center\">" +
							"<a href=\"http://www.atea.no\">" +
								"<img src=\"images/atealogo.jpg\" />" +
							"</a>" +
							"<p>Telepresence Network Documentation</p>" +
						"</div>" +
						
						"<div id=\"navbar-container\">" +
						"	<div id=\"navbar\" align=\"center\">" +
							"	<a href=\"#\">" +
							"		Oversikt" +
							"	</a>" +
							"	<a href=\"#\">" +
							"		Topologi" +
							"	</a>" +
							"	<!-- Other links here...-->" +
							"</div>" +
						"</div>" +
						
						"<div id=\"int-site-container\">" +
							"<div id=\"int-site\">" +
							"<div id=\"footer\">" +
							"	<img src=\"images/ciscologo.jpg\" height=\"50px\" />" +
							"	<img src=\"images/companylogo.jpg\" height=\"50px\" />" +
							"</div>" +
							"</div>" +
						"</div>";
	
		String slutt = "</body></html>";
		
		FileWriter fstream = new FileWriter("index.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(start);
		  out.write(test);
		  out.write(slutt);
		  //Close the output stream
		  out.close();

		
	}
}
