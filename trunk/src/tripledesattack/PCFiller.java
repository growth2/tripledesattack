/**
 * 
 */
package tripledesattack;

import java.util.HashMap;
import java.util.Random;

import javax.crypto.Cipher;

/**
 * @author Andreas Urke
 * PCFiller returns a HashMap filled with random pairs of plaintext and ciphertext. 
 * One pair can never appear twice. Key and number of pairs is given by constructor. 
 */


public class PCFiller {

	private static HashMap<String, byte[]> pcTable = new HashMap<String, byte[]>();
	private static HashMap<Integer, String> pTextTable = new HashMap<Integer, String>();
	private static Integer count = 0;

	public static HashMap<String, byte[]> fillPcTable(int numberOfPCs, byte[] key){
		TDES tdes = new TDES();
		tdes.setKey(key);
		try {
			tdes.setMode(Cipher.ENCRYPT_MODE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i<numberOfPCs; i++){
			String message = createRandomHex(8);
			try {
				//TODO: Encrypt gir et byte-array tilbake. vi vil kanskje ha en hex-string? Rettelse: tror dette er OK
				tdes.setMessage(message);
				pcTable.put(message, tdes.encrypt());
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
