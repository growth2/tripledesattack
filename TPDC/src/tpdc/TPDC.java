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
		createHTML();

	}
	
	private static void createHTML() throws IOException{
		CreateHTMLindex cIndex = new CreateHTMLindex();
		CreateHTMLtopology cTopology = new CreateHTMLtopology(vcsC, vcsE);
	}
	
	private static void initializeDebug() throws FileNotFoundException{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter # of VCS Controls in topology: ");
		int numVCSC = Integer.parseInt(in.nextLine());
		System.out.print("Enter # of VCS Expressways in topology: ");
		int numVCSE = Integer.parseInt(in.nextLine());
		
		for(int i = 0; i<numVCSC; i++){
			System.out.print("Enter filename for xconf for VCS Control #" + i+1 + " : ");
			String xConf = in.nextLine();
			System.out.print("Enter filename for xstat for VCS Control #" + i+1 + " : ");
			String xStat = in.nextLine();
			vcsC.add(new VCS(xConf, xStat));
		}
		
		for(int i = 0; i<numVCSE; i++){
			System.out.print("Enter filename for xconf for VCS Expressway #" + i+1 + " : ");
			String xConf = in.nextLine();
			System.out.print("Enter filename for xstat for VCS Expressway #" + i+1 + " : ");
			String xStat = in.nextLine();
			vcsE.add(new VCS(xConf, xStat));
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
