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
		
		String xconfC = args[0];
		String xstatC = args[1];
		String xconfE = "test2";
		String xstatE = "test3";
		Topology t1 = null;
		try {
			t1 = new Topology(xconfC, xstatC, xconfE, xstatC);
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND");
			e.printStackTrace();
			return;
		}
		CreateHTMLindex cHTML = new CreateHTMLindex(t1);
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
