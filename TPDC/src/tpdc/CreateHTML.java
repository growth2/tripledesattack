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
public class CreateHTML {
	private Parser parser;
	
	public CreateHTML(Parser parser){
		this.parser = parser;
	}
	
	public void buildHTML() throws IOException{
		String start = "<html><head></head><body><h1>DOC GENERATOR</h1>";
		
		
		String test = "<p>Sip domain is: " + parser.getSipDomain() + "</p><p>Version is: " +
						parser.getSoftwareVersion() + "</p>" + parser.getOptions().toString();
		
	
		
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
