/**
 * Class TDESAttack
 * @author Andreas Urke
 * @author Magnus Lervåg
 */
package tripledesattack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import javax.crypto.Cipher;


public class TDESAttack {	
	private HashMap<byte[], byte[]> pcTable;
	private HashMap<byte[], byte[]> biTable = new HashMap<byte[], byte[]>();
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
	public TDESAttack(int numberOfPCs, byte[] i, byte[] j) {
		//---Preparations
		Arrays.fill(change1, false);
		Arrays.fill(change2, false);
		Arrays.fill(genKey1, (byte)-128);
		Arrays.fill(genKey2, (byte)-128);
		time = System.currentTimeMillis();
		this.i = i;
		this.j = j;
		//---End preparations
		
		//---Generate valid 3DESkey
		correctKey = genKeyFromArr(i,j);
		
		//---Populate pcTable
		pcTable = PCFiller.fillPcTable(numberOfPCs, correctKey);
		plaintTextIterator = pcTable.keySet().iterator();
		
		//---Print keys to console
		printKeys(1);
		
		//---Creates a value for a(cheatA)
		makeCheatA();
		
		//---Populate biTable and find candidate for first key
		attackPart1();
		
		//---Find candidate for second key
		attackPart2();
	}
	
	
	/**
	 * @param args0: Number of PC-pairs. args1-8: Key1 args9-16: Key2
	 */
	public static void main(String[] args){
		byte[] key1 = new byte[8];
		byte[] key2 = new byte[8];
		byte[] realArgs = new byte[args.length];
		for (int i = 1; i < args.length; i++) {
			realArgs[i] = Byte.parseByte(args[i]);
		}
		System.arraycopy(realArgs, 1, key1, 0, 8);
		System.arraycopy(realArgs, 9, key2, 0, 8);
		TDESAttack attack = new TDESAttack(Integer.parseInt(args[0]), key1, key2);
	}
	
	/**
	 * Decrypts ciphertext(cheatA) with all keys. Compares with pcTable. If a decrypted text matches a plaintext,
	 * the corresponding ciphertext is decrypted using the same key.
	 * The new plaintext and and the key is stored in biTable.
	 */
	
	private void attackPart1(){
		for(long j = 0L; j<72057594037927936L; j++){
			des.setKey(genKey1);
			des.initDES(Cipher.DECRYPT_MODE);
			pcIterator = pcTable.keySet().iterator();
			byte[] result = des.decrypt(cheatA);
			while(pcIterator.hasNext()){
				byte[] currentPlainText = pcIterator.next();
				if(Arrays.equals(result, currentPlainText)){
					byte [] cipherText = pcTable.get(currentPlainText);
					biTable.put((des.decrypt(cipherText)), genKey1);
					return;
				}
			}
			nextKey1();
		}
	}
	
	/**
	 * Decrypts ciphertext(cheatA) with all keys. Compares decrypted text with keys(b-values)
	 * in biTable. If there is a match, calls method verify with genKey2 and corresponding key
	 * from biTable as parameters.
	 */
	private void attackPart2(){
		for(long j = 0L; j<72057594037927936L; j++){
			des.setKey(genKey2);
			des.initDES(Cipher.DECRYPT_MODE);
			biIterator = biTable.keySet().iterator();
			byte[] result = des.decrypt(cheatA);
			while(biIterator.hasNext()){
				byte[] currentB = biIterator.next();
				if(Arrays.equals(result, currentB)){
					verify(biTable.get(currentB), genKey2);
				}
			}
			nextKey2();
		}
	}
	
	/**
	 * Verifies that the found keys are correct by running 3DES on all PC-pairs in pcTable.
	 */
	private void verify(byte[] key1, byte[] key2){
		tdes.setKey(genKeyFromArr(key1, key2));
		tdes.setMode(Cipher.ENCRYPT_MODE);
		Iterator<byte[]> pcIterator = pcTable.keySet().iterator();
		for(int i = 0; i<pcTable.size(); i++){
			byte[] plainText = pcIterator.next();
			tdes.plainTextBytes = plainText;
			if(!Arrays.equals(pcTable.get(plainText), tdes.encrypt())){
				return;
			}
		}
		System.out.println("\nSOLUTION FOUND!");
		printKeys(2);
		time = System.currentTimeMillis() - time;
	    System.out.println("\nThe test took " + time + " milliseconds");
		System.exit(0);
	}
	
	/**
	 * Generates a 3DES-key based on two supplied key-arrays
	 */
	private byte[] genKeyFromArr(byte[] genKey1, byte[] genKey2){
		byte[] keyBytes = new byte[24];
		keyBytes = Arrays.copyOf(genKey1, 24);
		System.arraycopy(genKey2, 0, keyBytes, 8, 8);
		System.arraycopy(genKey1, 0, keyBytes, 16, 8);
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
	}
	
	/**
	 * Makes the genKey2 array contain the next key. Next is increment of 1.
	 */
	private void nextKey2(){
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
	}
	
	/**
	 * Creates cheatA by getting a plainText from pcTable and encrypting it.
	 */
	public void makeCheatA(){
		des.plainTextBytes = plaintTextIterator.next();
		des.setKey(i);
		des.initDES(Cipher.ENCRYPT_MODE);
		cheatA = des.encrypt();	
	}
	
	/**
	 * Print keys to console
	 */
	private void printKeys(int whichKeys){
		byte[] key1 = i; byte[] key2 = j;
		if(whichKeys == 2){
			key1 = genKey1; key2 = genKey2;
		}
		System.out.print("Correct Key #1 is: ");
		for(int x = 0; x<key1.length; x++)System.out.print(key1[x] + " ");
		System.out.print("\nCorrect Key #2 is: ");
		for(int x = 0; x<key2.length; x++)System.out.print(key2[x] + " ");
	}
}

