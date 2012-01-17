/**
 * 
 */
package tpdc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Andreas
 *
 */
public class TPDC {
	/**
	 * @param args 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		//initialize();
		
		String xconf = args[0];
		String xstat = args[1];
		Parser parser = null;
		try {
			parser = new Parser(xconf, xstat);
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
			e.printStackTrace();
		}
		CreateHTML cHTML = new CreateHTML(parser);
		cHTML.buildHTML();
	}
	private static void initialize(){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter # of VCSs in topology: ");
		String numVCS = in.nextLine();
		System.out.print("Enter filename for xconf for VCS Control: ");
		String vcscXconf = in.nextLine();
		
		
	}
}
