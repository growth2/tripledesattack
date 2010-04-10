package tripledesattack;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TDES {

	private SecretKey key;
	private Cipher cipher;
	private byte[] plainTextBytes;
//	private byte[] cipherText;

    public TDES(){
    }
    public void setMode(int mode) throws Exception{
    	 cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
         cipher.init(mode, key);
    }
    public void setKey(byte[] keyBytes){
    	key = new SecretKeySpec(keyBytes, "DESede");
    }
    public void setMessage(String message) throws Exception{
    	plainTextBytes = message.getBytes("utf-8");
    }
//    public void setCipherText(byte[] cipherText) throws Exception{
//    	plainTextBytes = message.getBytes("utf-8");
//    }
    public byte[] encrypt() throws Exception {
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        return cipherText;
    }

    public String decrypt(byte[] message) throws Exception {
        final byte[] plainText = cipher.doFinal(message);
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