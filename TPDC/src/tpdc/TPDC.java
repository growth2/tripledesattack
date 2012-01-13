/**
 * 
 */
package tpdc;

import java.io.FileNotFoundException;
import java.io.IOException;

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
}
