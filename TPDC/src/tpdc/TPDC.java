/**
 * 
 */
package tpdc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	private static ArrayList<VCS> vcsC = new ArrayList<VCS>();
	private static ArrayList<VCS> vcsE = new ArrayList<VCS>();
	
	public static void main(String[] args) throws IOException{
		initializeDebug();
		
		CreateHTMLindex cHTML = new CreateHTMLindex(vcsC, vcsE);
		cHTML.buildHTML();
	}
	
	private static void initializeDebug(){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter # of VCS Controls in topology: ");
		String numVCSc = "1";
		System.out.print("Enter # of VCS Expressways in topology: ");
		String numVCSe = "0";
		System.out.print("Enter filename for xconf for VCS Control: ");
		String vcscXconf = "xconf.txt";
		System.out.print("Enter filename for xconf for VCS Control: ");
		String vcscXstat = "xstat.txt";
		
		try {
			vcsC.add(new VCS(vcscXconf, vcscXstat));
		} catch (FileNotFoundException e) {
			System.out.println("FILES NOT FOUND");
			e.printStackTrace();
			return;
		}
	}
	
	private static void initialize(){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter # of VCS Controls in topology: ");
		String numVCSc = in.nextLine();
		System.out.print("Enter # of VCS Expressways in topology: ");
		String numVCSe = in.nextLine();
		System.out.print("Enter filename for xconf for VCS Control: ");
		String vcscXconf = in.nextLine();
		System.out.print("Enter filename for xconf for VCS Control: ");
		String vcscXstat = in.nextLine();
		
		
	}
}
