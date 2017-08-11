package com.onmobile.aircel.helper;

import com.ibm.vodafone.encrytion.CryptoException;
import com.ibm.vodafone.encrytion.CryptoService;
import com.ibm.vodafone.encrytion.DecryptionFailedException;

public class DecryptKey
{
  public static String decrypt(String password)
    throws DecryptionFailedException
  {
    String res = null;
    try
    {
      res = CryptoService.getInstance().decrypt(password);
    }
    catch (CryptoException e)
    {
      e.printStackTrace();
    }
    return res;
  }
}
