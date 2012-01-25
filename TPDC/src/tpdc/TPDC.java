/**
 * 
 */
package tpdc;


import java.io.IOException;
import java.util.ArrayList;
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
	
	public static void main(String[] args) {
		debug = true;
		try {
			initialize();
		} catch (Exception e) {
			System.out.println("SOMETHING WENT WRONG. TRY AGAIN.");
			e.printStackTrace();
		}
	}
	
	private static void createHTML(String customer, String consultant) throws IOException{
		CreateHTMLindex.buildHTML(vcsC, vcsE, customer, consultant);
		CreateHTMLtopology.buildHTML(vcsC, vcsE);
		CreateHTMLstatic.createHTMLstaticPages();
		//CreateHTMLindex cIndex = new CreateHTMLindex(vcsC, vcsE);
		//CreateHTMLtopology cTopology = new CreateHTMLtopology(vcsC, vcsE);
	}
	
	private static void initialize() throws IOException{
		String customer = "Farstad";
		String consultant = "Smart Y. Pants";
		if(debug){
			vcsC.add(new VCS("xconf.txt", "xstat.txt", false));
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
				System.out.print("Enter xstat filename for VCS Control #" + i+1 + " : ");
				String xStat = in.nextLine();
				vcsC.add(new VCS(xConf, xStat, false));
			}
			
			for(int i = 0; i<numVCSE; i++){
				System.out.print("Enter xconf filename for VCS Expressway #" + i+1 + " : ");
				String xConf = in.nextLine();
				System.out.print("Enter xstat filename for VCS Expressway #" + i+1 + " : ");
				String xStat = in.nextLine();
				vcsE.add(new VCS(xConf, xStat, true));
			}
			System.out.print("Enter Customer name: ");
			customer = in.nextLine();
			System.out.print("Enter your name: ");
			consultant = in.nextLine();
		}
		createHTML(customer, consultant);
	}
}
