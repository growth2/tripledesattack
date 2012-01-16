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
		String test = "<html><head></head><body><h1>DOC GENERATOR<h2><p>Sip domain is: " + parser.getSipDomain() + "</p><p>Version is: " +
						parser.getSoftwareVersion() + "</body></html>";
		
		
		FileWriter fstream = new FileWriter("index.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(test);
		  //Close the output stream
		  out.close();

		
	}
}
