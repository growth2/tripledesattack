/**
 * 
 */
package tpdc;

import java.io.FileNotFoundException;

/**
 * @author andurke
 *
 */
public class Topology {

	public VCS vcsC;
	public VCS vcsE;
	
	public Topology(String xconfC, String xstatC, String xconfE, String xstatE) throws FileNotFoundException{
		vcsC = new VCS(xconfC, xstatC);
		vcsE = new VCS(xconfE, xstatE);
	}
}
