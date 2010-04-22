package tripledesattack;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {

	private SecretKey key;
	private Cipher cipher;
	public byte[] plainTextBytes;


    public DES(){
    	try {
			cipher = Cipher.getInstance("DES/ECB/NoPadding");
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    public void initDES(int mode) {
         try {
			cipher.init(mode, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void setKey(byte[] keyBytes){
    	key = new SecretKeySpec(keyBytes, "DES");
    }

    public byte[] encrypt() {
    	byte[] cipherText;
		try {
			cipherText = cipher.doFinal(plainTextBytes);
			return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

    public byte[] decrypt(byte[] cipherText){
        try {
            return cipher.doFinal(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
}
