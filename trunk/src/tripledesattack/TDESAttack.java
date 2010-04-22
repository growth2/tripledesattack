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
	private byte[] i = new byte[8];
	private byte[] j = new byte[8];
	private long time;
	private byte[] genKey1 = new byte[8], genKey2 = new byte[8];
	private boolean[] change1 = new boolean[7];
	private boolean[] change2 = new boolean[7];
	private long numKeys1 = 0;
	private long numKeys2 = 0;
	private DES des = new DES();
	private TDES tdes = new TDES();
	private byte[] cheatA;
	private Iterator<byte[]> plaintTextIterator;
	private Iterator<byte[]> pcIterator;
	private Iterator<byte[]> biIterator;
	
	/**
	 * Constructor
	 */
	public TDESAttack(String numberOfPCs, String key1, String key2) {
		//--------------Preparations
		Arrays.fill(change1, false);
		Arrays.fill(change2, false);
		Arrays.fill(i, (byte)-128);
		i[0] = 114;
		i[1] = 0;
		i[2] = -16;
		i[3] = -116;

		//i[0] = -126;
		Arrays.fill(j, (byte)-128);
		j[0] = 56;
		j[1] = 98;
		j[2] = -12;
		j[3] = -120;

		Arrays.fill(genKey1, (byte)-128);
		Arrays.fill(genKey2, (byte)-128);
		time = System.currentTimeMillis();
		//--------------
		
		correctKey = genKeyFromArr(i,j);
		pcTable = PCFiller.fillPcTable(Integer.parseInt(numberOfPCs), correctKey);
		System.out.print("Correct Key #1 is: ");
		for(int x = 0; x<i.length; x++)System.out.print(i[x] + " ");
		System.out.print("\nCorrect Key #2 is: ");
		for(int x = 0; x<j.length; x++)System.out.print(j[x] + " ");
		System.out.println();
		
		plaintTextIterator = pcTable.keySet().iterator();

		Arrays.fill(change1, false);
		Arrays.fill(change2, false);
		
		for(int i = 0; i<pcTable.size(); i++){
		makeCheatA();
		if(attackPart1() == true)break;
		}
		
		attackPart2();
		
		//-------Adm
		time = System.currentTimeMillis() - time;
	    System.out.println("The test took " + time + " milliseconds");
	    //---------
	}
	
	/**
	 * @param args0: Number of PC-pairs. args1: Key1 as 8-char String. args2: Key2 as 8-char String.
	 */
	public static void main(String[] args){
		//TDESAttack attack = new TDESAttack(args[0], args[1], args[2]);
		TDESAttack attack = new TDESAttack("1", "test1234", "cipher99");
	}
	
	/**
	 * Decrypts ciphertext with all keys. Compares with pcTable. If a decrypted text matches a plaintext,
	 * the corresponding ciphertext is decrypted using the same key.
	 * The new plaintext and and the key is stored in biTable. Returns true if match is found. False otherwise.
	 */
	private boolean attackPart1(){
			for(long j = 0L; j<72057594037927936L; j++){
				des.setKey(genKey1);
				des.initDES(Cipher.DECRYPT_MODE);
				pcIterator = pcTable.keySet().iterator();
				byte[] result = des.decrypt(cheatA);
				if(result != null){
					while(pcIterator.hasNext()){
						byte[] currentPlainText = pcIterator.next();
						if(Arrays.equals(result, currentPlainText)){
							byte [] cipherText = pcTable.get(currentPlainText);
							biTable.put((des.decrypt(cipherText)), genKey1);
							return true;
						}
					}
				}
				nextKey1();
			}
			return false;
	}
	
	/**
	 * Decrypts ciphertext with all keys. Compares with pcTable. 
	 */
	private void attackPart2(){
		for(int i = 0; i<biTable.size(); i++){
			for(long j = 0L; j<72057594037927936L; j++){
				des.setKey(genKey2);
				des.initDES(Cipher.DECRYPT_MODE);
				biIterator = biTable.keySet().iterator();
				byte[] result = des.decrypt(cheatA);
//				if(result != null){
				while(biIterator.hasNext()){
					byte[] currentB = biIterator.next();
					if(Arrays.equals(result, currentB)){
						verify(biTable.get(currentB), genKey2);
						Arrays.fill(change2, false);
						Arrays.fill(genKey2, (byte)-128);
					}
				}
//				}
				nextKey2();
			}
		}
	}
	
	/**
	 * Verifies that the found keys are correct by running 3DES on 5 PC-pairs.
	 */
	private void verify(byte[] key1, byte[] key2){
		tdes.setKey(genKeyFromArr(key1, key2));
		tdes.setMode(Cipher.ENCRYPT_MODE);
		Iterator<byte[]> pcIterator = pcTable.keySet().iterator();
		for(int i = 0; i<1; i++){
			byte[] plainText = pcIterator.next();
			tdes.plainTextBytes = plainText;
			if(!Arrays.equals(pcTable.get(plainText), tdes.encrypt())){
				return;
			}
		}
		System.out.println("SOLUTION FOUND!");
		System.out.print("Key #1 was: ");
		for(int i = 0; i<key1.length; i++)System.out.print(key1[i] + " ");
		System.out.print("\nKey #2 was: ");
		for(int i = 0; i<key2.length; i++)System.out.print(key2[i] + " ");
		time = System.currentTimeMillis() - time;
	    System.out.println("\nThe test took " + time + " milliseconds");
		System.exit(0);
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
	 * -----------OBSOLETE. STRINGS CAUSE HAVOC.----------
	 * 
	 * @param keyString1
	 * @param keyString2
	 * @return A DES-key as a byte[]-array
	 * @throws UnsupportedEncodingException
	 */
//	private byte[] genKeyFromStr(String keyString1, String keyString2) throws UnsupportedEncodingException{
//		String keyString = keyString1 + keyString2;
//		byte[] keyByteArray = keyString.getBytes("utf-8");
//        byte[] key = Arrays.copyOf(keyByteArray, 24);
//        for (int j = 0, k = 16; j < 8;) {
//                key[k++] = key[j++];
//        }
//        //For printing av key
//        //for(int i = 0; i<key.length; i++)System.out.println(Integer.toHexString(key[i]));
//		return key;
//	}
	
	/**
	 * Generates a DES-key based on two supplied key-arrays
	 * @return
	 */
	private byte[] genKeyFromArr(byte[] genKey1, byte[] genKey2){
		byte[] keyBytes = new byte[24];
		keyBytes = Arrays.copyOf(genKey1, 24);
		System.arraycopy(genKey2, 0, keyBytes, 8, 8);
		System.arraycopy(genKey1, 0, keyBytes, 16, 8);
		
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
		genKey1[0]+=2;
		for(int i = 0; i<6; i++){
			if(genKey1[i] == 126){
				if(change1[i] = true){
					genKey1[i] = -128;
					genKey1[i+1]+=2;
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
		genKey2[0]+=2;
		for(int i = 0; i<6; i++){
			if(genKey2[i] == 126){
				if(change2[i] = true){
					genKey2[i] = -128;
					genKey2[i+1]+=2;
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
		des.setKey(i);
		des.initDES(Cipher.ENCRYPT_MODE);
		cheatA = des.encrypt();	
	}
}

