/**
 * 
 */
package tpdc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


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
	
	public static void buildHTML(ArrayList<VCS> vcsC, ArrayList<VCS> vcsE, String customer, String consultant) throws IOException{
		Calendar cal = Calendar.getInstance();
	/*********************************************************************************************/					
		String body = "<p>These documents describe the " + customer + " Cisco Telepresence solution.<br><b>Author:</b> <em>" +
				consultant + "</em><br><b>Date:</b> " + cal.getTime() + "</p>";
		body += "<img src=\"images/telepresence_752x343.jpg\"></img><p></p>";
		/*
		body += "<ul><li>";
		if(!vcsC.isEmpty()) body+= "<a href=\"VCSC.html\">VCS Control</a></li>";
		if(!vcsE.isEmpty()) body+= "<li><a href=\"VCSE.html\">VCS Expressway</a></li>";
		if(vcsC.get(0).getTMSaddress() != "") body += "<li><a href=\"TMS.html\">TMS</a></li>";
		body += "</ul>";
		*/

	/*********************************************************************************************/						
		//String slutt = "</body></html>";
		FileWriter fstream = new FileWriter("index.html");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(CreateHTMLstatic.getStart());
		out.write(body);
		out.write(CreateHTMLstatic.getEnd());
		//out.write(CreateHTMLstatic.getEnd());
		//Close the output stream
		out.close();
		  
	}
	
}
