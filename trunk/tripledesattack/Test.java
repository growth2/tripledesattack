package test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Test {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("md5");
         byte[] digestOfPassword = md.digest("HG58YZ3CR9"
                        .getBytes("utf-8"));
         byte[] keyBytes = Arrays.copyOf(digestOfPassword, 21);
        for (int j = 0, k = 14; j < 7;) {
                keyBytes[k++] = keyBytes[j++];
        }

        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
		System.out.println(key.getFormat() + " /// " + key.toString());
		for (int j = 0; j<key.getEncoded().length; j++) {
			
			System.out.println(key.getEncoded()[j]);
            System.out.println(Integer.toHexString((key.getEncoded()[j])));
            
           
    }
		 System.out.println(key.getEncoded().length);

	}

}
