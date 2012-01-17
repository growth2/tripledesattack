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
public class CreateHTMLtopology {
	private Parser parser;
	
	public CreateHTMLtopology(Parser parser){
		this.parser = parser;
	}
	
	public void buildHTML() throws IOException{
		String start = "<html><head><LINK href=\"style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><h1>DOC GENERATOR</h1>";
		
		
		String test = "<div id=\"logo\"></div><div id=\"navbar-container\"><div id=\"navbar\"><a href=\"#\">Home</a><a href=\"#\">About</a><!-- Other links here...--></div></div>" +
						"<div id=\"int-site-container\"><div id=\"int-site\"></div></div>" +
						
						"<p>Sip domain is: " + parser.getSipDomain() + "</p><p>Version is: " + parser.getSoftwareVersion() + "</p>" + parser.getOptions().toString() +
						
						"<table id=\"gradient-style\" summary=\"Meeting Results\">" +
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
						"</table>";
	
		String slutt = "</body></html>";
		
		FileWriter fstream = new FileWriter("topology.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(start);
		  out.write(test);
		  out.write(slutt);
		  //Close the output stream
		  out.close();

		
	}
}