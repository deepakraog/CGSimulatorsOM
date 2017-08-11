package com.onmobile.AirtelEC;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onmobile.Airtel.AirtelAesEncDec;

public class AirtelEventConsentOne extends HttpServlet
{
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    try
    {
      doAction(request, response);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      doAction(request, response);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String responsestr = null;

    Properties prop = new Properties();
    String catalina_home = System.getenv("CATALINA_HOME");

    prop.load(new FileInputStream(new File(catalina_home + "/conf/simulator.properties")));
    String Mobile_number_length = prop.getProperty("Mobile_number_length".toUpperCase());
    String IBMEventUrl = prop.getProperty("IBM_EVENT_CHARGNG_URL".toUpperCase());
    String msisdn = request.getParameter("msisdn");
    String srvkey = request.getParameter("srvkey");
    String eventkey = request.getParameter("eventkey");
    String rurl = request.getParameter("rURL");
    String imgurl = request.getParameter("imgURL");
    String curl = request.getParameter("cURL");
    AirtelAesEncDec air = new AirtelAesEncDec();
    String enckey = prop.getProperty("enckey".toUpperCase());
    System.out.println("curent url " + curl);
    if ((msisdn.length() != Integer.parseInt(Mobile_number_length)) || (msisdn == null) || (msisdn.equalsIgnoreCase("null")) || 
      (srvkey.trim().equals("")) || (srvkey == null) || (srvkey.equalsIgnoreCase("null")) || 
      (eventkey.trim().equals("")) || (eventkey == null) || (eventkey.equalsIgnoreCase("null")) || 
      (rurl.trim().equals("")) || (rurl == null) || (rurl.equalsIgnoreCase("null")))
    {
      responsestr = "ERROR|INVALID HIT";
    }
    else
    {
      responsestr = IBMEventUrl + "?";
      responsestr = responsestr + "mth=" + URLEncoder.encode("debit", "UTF-8") + "&";
      responsestr = responsestr + "m=" + URLEncoder.encode(air.encrypt(msisdn, enckey), "UTF-8") + "&";
      responsestr = responsestr + "pi=" + URLEncoder.encode("ProductID1", "UTF-8") + "&";
      responsestr = responsestr + "pn=" + URLEncoder.encode("ProductName1", "UTF-8") + "&";
      responsestr = responsestr + "pi=" + URLEncoder.encode("ProductID1", "UTF-8") + "&";
      responsestr = responsestr + "pp=" + URLEncoder.encode("30.00", "UTF-8") + "&";
      responsestr = responsestr + "pd=" + URLEncoder.encode("Product Description", "UTF-8") + "&";
      responsestr = responsestr + "pmt=" + URLEncoder.encode("audio/mpeg", "UTF-8") + "&";
      responsestr = responsestr + "dc=" + URLEncoder.encode("WAP", "UTF-8") + "&";
      responsestr = responsestr + "et=" + URLEncoder.encode("Content Purchase", "UTF-8") + "&";
      responsestr = responsestr + "ci=" + URLEncoder.encode("2779", "UTF-8") + "&";
      responsestr = responsestr + "cur=" + URLEncoder.encode("INR", "UTF-8") + "&";
      responsestr = responsestr + "cprtid=" + URLEncoder.encode("002779", "UTF-8") + "&";
      responsestr = responsestr + "skw=" + URLEncoder.encode("DEFAULT", "UTF-8") + "&";
      responsestr = responsestr + "srcd=" + URLEncoder.encode("servicecode", "UTF-8") + "&";
      if (curl != null)
        responsestr = responsestr + "curl=" + URLEncoder.encode(curl, "UTF-8") + "&";
      responsestr = responsestr + "ru=" + URLEncoder.encode(rurl, "UTF-8") + "&";
      responsestr = responsestr + "pnp=" + URLEncoder.encode("Product name below image", "UTF-8") + "&";
      if (imgurl != null)
        responsestr = responsestr + "imgurl=" + URLEncoder.encode(imgurl, "UTF-8") + "&";
      responsestr = responsestr + "up=" + URLEncoder.encode(air.encrypt("onmobile:Onm0bile", enckey), "UTF-8") + "&";
      responsestr = responsestr + "cpx=" + URLEncoder.encode(getID(), "UTF-8");
    }

    System.out.println("URL : " + responsestr);

    response.getOutputStream().write(responsestr.getBytes());
  }

  public String getID()
  {
    String allChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    Random random = new Random();
    int length = random.nextInt(5);
    length += 10;
    System.out.println(length);
    char[] chars = new char[length];
    for (int i = 0; i < length; i++)
    {
      chars[i] = allChars.charAt(random.nextInt(allChars.length()));
      System.out.println(chars[i]);
    }
    String s = new String(chars, 0, length);
    System.out.println(s);
    return s;
  }
}