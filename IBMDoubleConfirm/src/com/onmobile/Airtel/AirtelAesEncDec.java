package com.onmobile.Airtel;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AirtelAesEncDec
{
  private final String ALGO = "AES";
  
  public String encrypt(String Data, String keys)
    throws Exception
  {
    byte[] keyValue = keys.getBytes();
    Key key = generateKey(keyValue);
    
    Cipher c = Cipher.getInstance("AES");
    c.init(1, key);
    byte[] encVal = c.doFinal(Data.getBytes());
    
    String encryptedValue = new BASE64Encoder().encode(encVal);
    
    return encryptedValue;
  }
  
  public String decrypt(String encryptedData, String keys)
    throws Exception
  {
    byte[] keyValue = keys.getBytes();
    Key key = generateKey(keyValue);
    
    Cipher c = Cipher.getInstance("AES");
    c.init(2, key);
    byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
    
    byte[] decValue = c.doFinal(decordedValue);
    
    String decryptedValue = new String(decValue);
    
    return decryptedValue;
  }
  
  public void getKey(String paramString)
  {
    throw new Error("Unresolved compilation problem: \n\tkeys cannot be resolved to a variable\n");
  }
  
  private Key generateKey(byte[] keyValue)
    throws Exception
  {
    Key key = new SecretKeySpec(keyValue, "AES");
    return key;
  }
}
