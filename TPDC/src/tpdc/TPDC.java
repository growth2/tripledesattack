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
	private static boolean debug;
	
	public static void main(String[] args) throws IOException{
		debug = true;
		initialize();
		createHTML();
	}
	
	private static void createHTML() throws IOException{
		CreateHTMLindex cIndex = new CreateHTMLindex();
		CreateHTMLtopology cTopology = new CreateHTMLtopology(vcsC, vcsE);
	}
	
	private static void initialize() throws FileNotFoundException{
		if(debug){
			vcsC.add(new VCS("xconf.txt", "xstat.txt"));
		}
		else{
			Scanner in = new Scanner(System.in);
			System.out.print("Enter # of VCS Controls in topology: ");
			int numVCSC = Integer.parseInt(in.nextLine());
			System.out.print("Enter # of VCS Expressways in topology: ");
			int numVCSE = Integer.parseInt(in.nextLine());
			
			for(int i = 0; i<numVCSC; i++){
				System.out.print("Enter xconf filename for VCS Control #" + i+1 + " : ");
				String xConf = in.nextLine();
				System.out.print("Enter xconf filename for VCS Control #" + i+1 + " : ");
				String xStat = in.nextLine();
				vcsC.add(new VCS(xConf, xStat));
			}
			
			for(int i = 0; i<numVCSE; i++){
				System.out.print("Enter xconf filename for VCS Expressway #" + i+1 + " : ");
				String xConf = in.nextLine();
				System.out.print("Enter xstat filename for VCS Expressway #" + i+1 + " : ");
				String xStat = in.nextLine();
				vcsE.add(new VCS(xConf, xStat));
			}
		}
	}
}
