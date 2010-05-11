package tripledesattack;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TDES {

	private SecretKey key;
	private Cipher cipher;
	public byte[] plainTextBytes;

    public TDES(){
    	try {
			cipher = Cipher.getInstance("DESede/ECB/NoPadding");
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    public void setMode(int mode){
         try {
			cipher.init(mode, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void setKey(byte[] keyBytes){
    	key = new SecretKeySpec(keyBytes, "DESede");
    }

    public byte[] encrypt()  {
        byte[] cipherText;
		try {
			cipherText = cipher.doFinal(plainTextBytes);
		    return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
}