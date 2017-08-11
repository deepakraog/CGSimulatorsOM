package com.onmobile.IDEA;

import com.ibm.wcg.utility.decryption.agc.AESEncryptionDecryptionUtil;

public class IdeaAesEncDec
{
  //private final String ALGO = "AES";
  
  public String decryption(String Data)
    throws Exception
  {
   
    String decryptedValue = AESEncryptionDecryptionUtil.decrypt(Data);
    
    return decryptedValue;
  }  
  public String encryption(String Data)
  throws Exception
  {
	 String  encryptedValue = AESEncryptionDecryptionUtil.encrypt(Data);
	 
	 return encryptedValue;
  }
}