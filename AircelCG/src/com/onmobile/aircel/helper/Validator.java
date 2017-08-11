package com.onmobile.aircel.helper;

import java.util.Map;

/**
 * @author somashekhar.wali
 * This is a helper class which takes care of 
 *
 */
public class Validator {

	public static void validateMandatoryParams(Map<String, String>params) throws NullPointerException{

		if(isNullLength(params.get("MSISDN")))
		{
			throw new NullPointerException("MSISDN_is_missing");

		} else if(isNullLength(params.get("pprice")))
		{
			throw new NullPointerException("pprice_is_missing");

		}else if(isNullLength(params.get("pval")))
		{
			throw new NullPointerException("pvalidity_is_missing");

		}else if(isNullLength(params.get("cpid")))
		{
			throw new NullPointerException("Cpid_is_missing");

		}else if(isNullLength(params.get("userid")))
		{
			throw new NullPointerException("userid_is_missing");

		}else if(isNullLength(params.get("password")))
		{
			throw new NullPointerException("password_is_missing");

		}else if(isNullLength(params.get("bearermode")))
		{
			throw new NullPointerException("bearermode_is_missing");

		}else if(isNullLength(params.get("seid")))
		{
			throw new NullPointerException("SeId_is_missing");

		}else if(isNullLength(params.get("transid")))
		{
			throw new NullPointerException("transId_is_missing");

		}else if(isNullLength(params.get("contentid")))
		{
			throw new NullPointerException("contentId_is_missing");
			
		}else if(isNullLength(params.get("productId")))
		{
			throw new NullPointerException("productId_is_missing");
		}

	}

	public static boolean isNullLength(String str) {
		if ((str == null) || (str.trim().length() < 1))
			return true;
		return false;
	}
}
