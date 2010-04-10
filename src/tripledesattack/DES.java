package tripledesattack;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {

	private SecretKey key;
	private Cipher cipher;
	private byte[] plainTextBytes;
//	private byte[] cipherText;

    public DES(){
    }
    public void setMode(int mode) throws Exception{
    	 cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
         cipher.init(mode, key);
    }
    public void setKey(byte[] keyBytes){
    	key = new SecretKeySpec(keyBytes, "DES");
    }
    public void setMessage(String message) throws Exception{
    	plainTextBytes = message.getBytes("utf-8");
    }
//    public void setCipherText(byte[] cipherText) throws Exception{
//    	plainTextBytes = message.getBytes("utf-8");
//    }
    public byte[] encrypt() throws Exception {
    	byte[] cipherText = cipher.doFinal(plainTextBytes);
        return cipherText;
    }
    //final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
    //final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    //decipher.init(Cipher.DECRYPT_MODE, key);
    //final byte[] plainText = decipher.doFinal(message);
    
    public String decrypt(byte[] cipherText) throws Exception {
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, "UTF-8");
    }
}


//final MessageDigest md = MessageDigest.getInstance("md5");
//final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
//              .getBytes("utf-8"));
//final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//for (int j = 0, k = 16; j < 8;) {
//      keyBytes[k++] = keyBytes[j++];
//}