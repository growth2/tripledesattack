/**
 * 
 */
package tripledesattack;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;


/**
 * @author Andreas
 *
 */
public class TDESAttack {
	
	private HashMap<String, String> pcTable;
	private byte[] key;
	private long time;
	private byte[] genKey1 = new byte[7], genKey2 = new byte[7];
	private int generatedKey1, generatedKey2 = 0;
	private static byte test2 = 0x0F;
	static int test3 = 2000000001;
	
	/**
	 * Constructor
	 * @throws UnsupportedEncodingException 
	 */
	public TDESAttack(String numberOfPCs, String key1, String key2) throws NumberFormatException, UnsupportedEncodingException{
		time = System.currentTimeMillis();
		
		//key = properKey(key1, key2);
		//pcTable = PCFiller.fillPcTable(Integer.parseInt(numberOfPCs), key);
		//System.out.println("done");
		
		//ROT
		//System.out.println(pcTable.toString());
		//for(int i = 0; i<pcTable.length; i++)System.out.println(Integer.toHexString(key[i]));
		//--------
		fillKeyArray();
		generateKey();
		time = System.currentTimeMillis() - time;
	    System.out.println("The test took " + time + " milliseconds");
	}

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//TDESAttack attack = new TDESAttack(args[0], args[1], args[2]);
		//properKey("testkrip" , "12345678");
		TDESAttack attack = new TDESAttack("10000", "test1234", "test1234");
		
		
	
		
	}
	
	private static byte[] properKey(String keyString1, String keyString2) throws UnsupportedEncodingException{
		String keyString = keyString1 + keyString2;
		byte[] keyByteArray = keyString.getBytes("utf-8");
        byte[] key = Arrays.copyOf(keyByteArray, 24);
        for (int j = 0, k = 16; j < 8;) {
                key[k++] = key[j++];
        }
        //for(int i = 0; i<key.length; i++)System.out.println(Integer.toHexString(key[i]));
		return key;
	}
	
	private byte[] generateKey(){
		byte[] keyBytes = new byte[24];
		keyBytes = Arrays.copyOf(genKey1, 24);
		System.arraycopy(genKey2, 0, keyBytes, 8, 7);
		System.arraycopy(genKey1, 0, keyBytes, 16, 7);
		for(int i = 0; i<keyBytes.length; i++)System.out.println(Integer.toHexString(keyBytes[i]));
		
		//TODO: MEMO TO SELF: BRUK ARRAYS.HASH til sammenligning av verdiene
		System.out.println(keyBytes.length);
		
		return keyBytes;
	}
	private void addToKeyArray(){
		//TODO: MEMO TO SELF. BRUKE MODULO FOR Å PLUSSE PÅ ARRAYET?
		Arrays.fill(genKey1, (byte)0x01);
		Arrays.fill(genKey2, (byte)0x02);
	}
	
	private void fillKeyArray(){
		Arrays.fill(genKey1, (byte)0x01);
		Arrays.fill(genKey2, (byte)0x02);
	}
	
	
}
