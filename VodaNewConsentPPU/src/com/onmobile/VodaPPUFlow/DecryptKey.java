package com.onmobile.VodaPPUFlow;
import com.ibm.vodafone.encrytion.CryptoException;
import com.ibm.vodafone.encrytion.CryptoService;
import com.ibm.vodafone.encrytion.DecryptionFailedException;


public class DecryptKey {

		//static String key="1234";
 public static String decrypt(String password) throws DecryptionFailedException{
	 
	 String res = null;
	 if((password == null) || (password.trim().length() < 1))
			return password;
		
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
