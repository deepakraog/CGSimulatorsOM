package com.onmobile.VodaFlow;
import com.ibm.vodafone.encrytion.CryptoException;
import com.ibm.vodafone.encrytion.CryptoService;
import com.ibm.vodafone.encrytion.DESedeCipher;
import com.ibm.vodafone.encrytion.DecryptionFailedException;
import com.ibm.vodafone.encrytion.EncryptionFailedException;


public class DecryptKey {

		//static String key="1234";
 public static String decrypt(String password) throws DecryptionFailedException{
	 
	 String res = null;
	 try {
		 res =  CryptoService.getInstance().decrypt(password);
	} catch (CryptoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return res;
 }
	
/*public static String encrypt(String password) throws EncryptionFailedException {
	 
	 DESedeCipher en = new DESedeCipher();
	 String value = en.encrypt(password, key);
	 System.out.println("DecryptKey : encrypt value :"+value);
	 return value;
 }*/

}
