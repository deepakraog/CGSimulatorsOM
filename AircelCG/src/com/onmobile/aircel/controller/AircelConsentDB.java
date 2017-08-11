package com.onmobile.aircel.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drg.properties.Utility;

/**
 * @author somashekhar.wali
 * @Modified Deepak Rao Gaikwad
 * This servlet is responsible for sending the response
 *         back using the callback URL sent in the request.
 *
 */
public class AircelConsentDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Utility util = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("inside init() of CS");

		try {
			util = new Utility();
			util.load();
			System.out.println("loaded the properties Files\n");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method identifies the user action and accordingly send the response
	 * back. It has a logic to generate the CGID if the user has clicked on YES.
	 * It also has a logic to hit the prism for actual subscription.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Throwable
	 *             Exception
	 */
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.print("--------------inside ConsentDB---------------");

		String redirecturl = request.getParameter("redirecturl");
		System.out.println("redirecturl: " + redirecturl);
		String msisdn = request.getParameter("msisdn");
		System.out.println("msisdn: " + msisdn);
		String productId = request.getParameter("productid");
		System.out.println("productId :" + productId);
		String seid = request.getParameter("seid");
		System.out.println("seid: " + seid);
		String contentid = request.getParameter("contentid");
		System.out.println("contentid: " + contentid);
		String transid = request.getParameter("transid");
		System.out.println("transid: " + transid);
		String cpid = request.getParameter("cpid");
		System.out.println("transid: " + cpid);
		String result = request.getParameter("result");
		System.out.println("action: " + result);
		String actURL = (String) util.get("SUBS_ACTIVATION_URL");

		if (redirecturl.contains("?")) {
			redirecturl = redirecturl + "&";
		} else {
			redirecturl = redirecturl + "?";
		}

		redirecturl = redirecturl + "transid=" + transid;
		if (result.equalsIgnoreCase("FAIL")) {
			System.out.println("Result: " + result);
			System.out.println("Redirecting to URL :" + redirecturl);
			response.sendRedirect(redirecturl);
		} else {
			{
				String url = actURL + msisdn + "&siteid=1&type=P&user=mmp&pass=mmp&remarks=ACTIVATION&srvkey="
						+ productId + "&mode=wap" + "&refid=123123123";

				System.out.println("<-------URL:----------------> " + url);

				int rcode = -1;
				try {
					System.out.println("Hitting url :" + url);
					URL url1 = new URL(url);
					HttpURLConnection uc = (HttpURLConnection) url1.openConnection();
					uc.connect();
					rcode = uc.getResponseCode();
					System.out.println("The status of hitting callbackURL : " + uc.getResponseCode());
					System.out.println("response Message :" + uc.getResponseMessage());
					uc.disconnect();
				} catch (IOException e) {
					System.out.println("Thread Additional : IOException : " + e);
				}
				if (rcode == 200) {
					Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
					Date currentTime = localCalendar.getTime();
					System.out.println("Current Date: " + currentTime);

					int currentDay = localCalendar.get(Calendar.DATE);
					int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
					int second = localCalendar.get(Calendar.SECOND);
					int hour = localCalendar.get(Calendar.HOUR_OF_DAY);
					int minute = localCalendar.get(Calendar.MINUTE);
					int millis = localCalendar.get(Calendar.MILLISECOND);
					String currentYear = new SimpleDateFormat("yy").format(localCalendar.getTime());

					String cgId = "CG" + cpid + seid + currentYear + currentMonth + currentDay + hour + minute + second
							+ "12" + millis;
					// redirecturl = redirecturl+"&cgid="+cgId;
					System.out.println("final response url is " + redirecturl);
					response.sendRedirect(redirecturl);
				} else {
					System.out.println("Prism hit failed, Response code : " + rcode);
					response.sendRedirect(redirecturl);
				}
			}
		}
	}
}
