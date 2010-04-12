package tripledesattack;

import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TDES {

	private SecretKey key;
	private Cipher cipher;
	private byte[] plainTextBytes;
//	private byte[] cipherText;

    public TDES(){
    	try {
			cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
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
    public void setMessage(String message) {
    	try {
			plainTextBytes = message.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
//    public void setCipherText(byte[] cipherText) throws Exception{
//    	plainTextBytes = message.getBytes("utf-8");
//    }
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

    public String decrypt(byte[] message){
        byte[] plainText;
		try {
			plainText = cipher.doFinal(message);
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