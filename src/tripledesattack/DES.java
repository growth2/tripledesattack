package tripledesattack;


import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {

	private SecretKey key;
	private Cipher cipher;
	private byte[] plainTextBytes;
//	private byte[] cipherText;

    public DES(){
    	try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
    public void setMessage(String message){
    	try {
			plainTextBytes = message.getBytes("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
//    public void setCipherText(byte[] cipherText) throws Exception{
//    	plainTextBytes = message.getBytes("utf-8");
//    }
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
    //final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
    //final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    //decipher.init(Cipher.DECRYPT_MODE, key);
    //final byte[] plainText = decipher.doFinal(message);
    
    public String decrypt(byte[] cipherText){
        try {
            byte[] plainText = cipher.doFinal(cipherText);
			return new String(plainText, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
}


//final MessageDigest md = MessageDigest.getInstance("md5");
//final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
//              .getBytes("utf-8"));
//final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//for (int j = 0, k = 16; j < 8;) {
//      keyBytes[k++] = keyBytes[j++];
//}