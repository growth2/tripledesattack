package tripledesattack;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	private byte[] keyBytes;

//    public static void main(String[] args) throws Exception {
//
//        String text = "test123123123";
//
//        byte[] codedtext = new TDES().encrypt(text);
//        String decodedtext = new TDES().decrypt(codedtext);
//
//        System.out.println(codedtext); // this is a byte array, you'll just see a reference to an array
//        System.out.println(decodedtext); // This correctly shows "kyle boon"
//    }
    public DES(byte[] keyBytes){
    	this.keyBytes = keyBytes;
    }
    
    public byte[] encrypt(String message) throws Exception {
        final SecretKey key = new SecretKeySpec(keyBytes, "DES");
        final Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        // final String encodedCipherText = new sun.misc.BASE64Encoder()
        // .encode(cipherText);

        return cipherText;
    }

    public String decrypt(byte[] message) throws Exception {
    	final SecretKey key = new SecretKeySpec(keyBytes, "DES");
        final Cipher decipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key);
        // final byte[] encData = new
        // sun.misc.BASE64Decoder().decodeBuffer(message);
        final byte[] plainText = decipher.doFinal(message);

        return new String(plainText, "UTF-8");
    }
    
    public void setKey(byte[] keyBytes){
    	this.keyBytes = keyBytes;
    }    
    public byte[] getKey(){
    	return keyBytes;
    }
}


//final MessageDigest md = MessageDigest.getInstance("md5");
//final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
//              .getBytes("utf-8"));
//final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//for (int j = 0, k = 16; j < 8;) {
//      keyBytes[k++] = keyBytes[j++];
//}