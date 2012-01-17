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
public class CreateHTMLindex {
	private Parser parser;
	
	public CreateHTMLindex(Parser parser){
		this.parser = parser;
	}
	
	public void buildHTML() throws IOException{
		String start = "<html><head><LINK href=\"style.css\" rel=\"stylesheet\" type=\"text/css\"></head><body><h1>DOC GENERATOR</h1>";
		
		
		String test = "<div id=\"logo\"></div><div id=\"navbar-container\"><div id=\"navbar\"><a href=\"#\">Home</a><a href=\"#\">About</a><!-- Other links here...--></div></div>" +
						"<div id=\"int-site-container\"><div id=\"int-site\"></div></div>";
	
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
