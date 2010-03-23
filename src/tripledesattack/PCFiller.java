/**
 * 
 */
package tripledesattack;

import java.util.HashMap;
import java.util.Random;

/**
 * @author Andreas Urke
 * PCFiller returns a HashMap filled with random pairs of plaintext and ciphertext. One pair can never appear twice.
 */


public class PCFiller {
	
	private static HashMap<String, String> pcTable = new HashMap<String, String>();
	private static HashMap<Integer, String> pTextTable = new HashMap<Integer, String>();
	private static Integer count = 0;
		
	public static HashMap<String, String> fillPcTable(int numberOfPCs, byte[] key){
		TDES tdes = new TDES(key);
		for (int i = 0; i<numberOfPCs; i++){
		String message = createRandomHex(8);
		try {
			//TODO: Encrypt gir et byte-array tilbake. vi vil kanskje ha en hex-string? Clusterfuck allerede.
			pcTable.put(message, tdes.encrypt(message).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return pcTable;
	}
	
	private static String createRandomHex(int length){
		StringBuilder sb;
		do {
			Random random = new Random();
			sb = new StringBuilder();
			while (sb.length() < length) {
				sb.append(Integer.toHexString(random.nextInt(16)));
			}
       } while (pTextTable.containsValue(sb.toString()));
			
			pTextTable.put(count, sb.toString());
			count++;
			return sb.toString();
	}
	
	
	

}
