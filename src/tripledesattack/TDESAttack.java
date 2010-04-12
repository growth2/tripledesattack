/**
 * @author Andreas Urke
 * @author Magnus Lervåg
 *
 */
package tripledesattack;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class TDESAttack {
	
	private HashMap<byte[], byte[]> pcTable;
	private HashMap<byte[], byte[]> biTable = new HashMap<byte[], byte[]>();
	private HashMap<byte[], Integer> pcHashTable = new HashMap<byte[], Integer>();
	private byte[] correctKey;
	private byte[] correctKey1;
	private byte[] correctKey2;
	private long time;
	private byte[] genKey1 = new byte[8], genKey2 = new byte[8];
	private boolean[] change1 = new boolean[7];
	private boolean[] change2 = new boolean[7];
	private long numKeys1 = 0;
	private long numKeys2 = 0;
	private DES des = new DES();
	private byte[] cheatA;
	private Iterator<byte[]> plaintTextIterator;
	private Iterator<byte[]> pcIterator;

	/**
	 * Constructor
	 * @throws UnsupportedEncodingException 
	 */
	public TDESAttack(String numberOfPCs, String key1, String key2) throws NumberFormatException, UnsupportedEncodingException{
		//Preparations
		Arrays.fill(change1, false);
		Arrays.fill(change2, false);
		Arrays.fill(genKey1, (byte)-128);
		Arrays.fill(genKey2, (byte)-128);
		time = System.currentTimeMillis();
		//--------------
		
		correctKey1 = key1.getBytes("utf-8");
		correctKey = genKeyFromStr(key1, key2);
		pcTable = PCFiller.fillPcTable(Integer.parseInt(numberOfPCs), genKeyFromArr());
		plaintTextIterator = pcTable.keySet().iterator();
		pcIterator = pcTable.keySet().iterator();
		attackPart1();
		
		//Adm
		time = System.currentTimeMillis() - time;
	    System.out.println("The test took " + time + " milliseconds");
	    //---------
	}
	
	/**
	 * @param args0: Number of PC-pairs. args1: Key1 as 8-char String. args2: Key2 as 8-char String.
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		//TDESAttack attack = new TDESAttack(args[0], args[1], args[2]);
		TDESAttack attack = new TDESAttack("100", "test1234", "cipher99");
	}
	
	/**
	 * Decrypts ciphertext with all keys. Compares with pcTable. If a decrypted text matches a plaintext,
	 * the corresponding ciphertext is decrypted using the same key.
	 * The new plaintext and and the key is stored in biTable.
	 * @throws UnsupportedEncodingException
	 */
	//TODO: dårlig navn
	private void attackPart1() throws UnsupportedEncodingException{
		
		for(int i = 0; i<pcTable.size(); i++){
			makeCheatA();
			//72057594037927936L
			for(long j = 0L; j<72057594037927936L; j++){
				des.setKey(genKey1);
				des.initDES(Cipher.DECRYPT_MODE);
				
				byte[] result = des.decrypt(cheatA);
				byte[] currentPlainText = pcIterator.next();
				if(Arrays.equals(result, currentPlainText)){
					biTable.put((des.decrypt(pcTable.get(currentPlainText))), genKey1);
					System.out.println("FOUND");
					Arrays.fill(change1, false);
					Arrays.fill(genKey1, (byte)-128);
					break;
				}
				nextKey1();
			}
		}
	}
	
	/**
	 * Reads of the pcTable, creates a hashcode of each Value(cipertexts) and inserts into a
	 * new HashMap pcHashTable. Keys are the same.
	 */
	//TODO: denne er useless nå. men kan kanskje få bruk for i part 2 av angrepet
	private void hashPCtable(){
		Set<byte[]> keySet = pcTable.keySet();
		Iterator<byte[]> keyIterator = keySet.iterator();
		while(keyIterator.hasNext()){
			byte[] currentKey = keyIterator.next();
			int hashCode = pcTable.get(currentKey).hashCode();
			pcHashTable.put(currentKey, hashCode);
		}
	}
	
	/**
	 * Generates a DES-key based on two keys represented as Strings.
	 * 
	 * @param keyString1
	 * @param keyString2
	 * @return A DES-key as a byte[]-array
	 * @throws UnsupportedEncodingException
	 */
	private byte[] genKeyFromStr(String keyString1, String keyString2) throws UnsupportedEncodingException{
		String keyString = keyString1 + keyString2;
		byte[] keyByteArray = keyString.getBytes("utf-8");
        byte[] key = Arrays.copyOf(keyByteArray, 24);
        for (int j = 0, k = 16; j < 8;) {
                key[k++] = key[j++];
        }
        //For printing av key
        //for(int i = 0; i<key.length; i++)System.out.println(Integer.toHexString(key[i]));
		return key;
	}
	
	/**
	 * Generates a DES-key based on two key-arrays
	 * @return
	 */
	private byte[] genKeyFromArr(){
		byte[] keyBytes = new byte[24];
		keyBytes = Arrays.copyOf(genKey1, 24);
		System.arraycopy(genKey2, 0, keyBytes, 8, 7);
		System.arraycopy(genKey1, 0, keyBytes, 16, 7);
		
		//For printing av key
		//for(int i = 0; i<keyBytes.length; i++)
			//System.out.println(Byte.valueOf(keyBytes[i]).intValue());
			//System.out.println(Integer.toHexString(keyBytes[i]));
			//System.out.println(Integer.toString((keyBytes[i]), 16));
		
		
		//TODO: MEMO TO SELF: BRUK ARRAYS.HASHCODE til sammenligning av verdiene
		return keyBytes;
	}
	
	
	/**
	 * Makes the genKey1 array contain the next key. Next is increment of 1.
	 */
	private void nextKey1(){
		genKey1[0]++;
		for(int i = 0; i<6; i++){
			if(genKey1[i] == 127){
				if(change1[i] = true){
					genKey1[i] = -128;
					genKey1[i+1]++;
					change1[i] = false;
				}
				else change1[i] = true;
			}
		}
		numKeys1++;
		
		//For printing av keys
		//for(int i = 0; i<genKey1.length; i++)
		//	System.out.println(Byte.valueOf(genKey1[i]).intValue());
	}
	
	/**
	 * Makes the genKey2 array contain the next key. Next is increment of 1.
	 */
	private void nextKey2(){
		if(numKeys2 == 72057594037927936L){
			//TODO: siste key. stoppe program? sende en spesiell return-value? calle en avslutningsmetode?
		}
		genKey2[0]++;
		for(int i = 0; i<6; i++){
			if(genKey2[i] == 127){
				if(change2[i] = true){
					genKey2[i] = -128;
					genKey2[i+1]++;
					change2[i] = false;
				}
				else change2[i] = true;
			}
		}
		numKeys2++;
		
		//For printing av keys
		//for(int i = 0; i<genKey2.length; i++)
		//	System.out.println(Byte.valueOf(genKey2[i]).intValue());		
	}
	public void makeCheatA(){
		
//		Set<byte[]> keySet = pcTable.keySet();
//		keySet.iterator().next()
//		Byte[] plainTextArray = (Byte[])keySet.toArray();
//		
//		byte plainText = plainTextArray[plainTextNumber];
//		
		des.plainTextBytes = plaintTextIterator.next();
		des.setKey(genKey1);
		des.initDES(Cipher.ENCRYPT_MODE);
		cheatA = des.encrypt();	
	}
}

