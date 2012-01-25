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
public class CreateHTMLconnect {
	//private ArrayList<VCS> vcsC;
	//private ArrayList<VCS> vcsE;
	
	public CreateHTMLconnect(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE) throws IOException{
		//this.vcsC = vcsC;
		//this.vcsE = vcsE;
		//buildHTML();
	}
	
	public static void buildHTML(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE) throws IOException{	
			
		
	/*********************************************************************************************/		
		
		String body = "<h1>Infrastructure</h1>";
		
	/*********************************************************************************************/
		
		FileWriter fstream = new FileWriter("connect.html");
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(CreateHTMLstatic.getStart());
		  out.write(body);
		  out.write(CreateHTMLstatic.getEnd());
		  //Close the output stream
		  out.close();

		
	}
}