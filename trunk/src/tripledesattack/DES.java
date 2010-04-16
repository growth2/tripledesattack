package tripledesattack;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES {

	private SecretKey key;
	private Cipher cipher;
	public byte[] plainTextBytes;
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
//			return new String(plainText, "UTF-8");
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
    }
    
//  public void setCipherText(byte[] cipherText) throws Exception{
//	plainTextBytes = message.getBytes("utf-8");
//}
}
