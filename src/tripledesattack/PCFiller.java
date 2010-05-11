/**
 * Class PCFiller
 * @author Andreas Urke
 * @author Magnus Lervåg
 * PCFiller returns a HashMap filled with random pairs of plaintext and ciphertext. 
 * One pair can never appear twice. Key and number of pairs is given by constructor. 
 */
package tripledesattack;

import java.util.HashMap;
import java.util.Random;
import javax.crypto.Cipher;

public class PCFiller {
	private static HashMap<byte[], byte[]> pcTable = new HashMap<byte[], byte[]>();
	private static HashMap<Integer, Integer> pTextTable = new HashMap<Integer, Integer>();
	private static Integer count = 0;

	public static HashMap<byte[], byte[]> fillPcTable(int numberOfPCs, byte[] key){
		TDES tdes = new TDES();
		tdes.setKey(key);
		tdes.setMode(Cipher.ENCRYPT_MODE);

		for (int i = 0; i<numberOfPCs; i++){
			byte[] plainTextBytes = new byte[8];
			Random random = new Random();
			do{
			random.nextBytes(plainTextBytes);
			tdes.plainTextBytes = plainTextBytes;
			}
			while (pTextTable.containsValue(plainTextBytes.hashCode()));
			pTextTable.put(count, plainTextBytes.hashCode());
			pcTable.put(plainTextBytes, tdes.encrypt());
			count++;	
		}
		return pcTable;
	}
}
