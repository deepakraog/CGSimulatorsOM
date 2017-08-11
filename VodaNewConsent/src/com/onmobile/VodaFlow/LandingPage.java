package com.onmobile.VodaFlow;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Arrays;
import java.util.Date;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class LandingPage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("Inside GET Method!!");
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("Inside POST Method!!");
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.print("--------------inside Voda2 DCLandingPage-------------------\n");
		String Action = request.getParameter("Action");
		Action = DecryptKey.decrypt(Action);
		String MSISDN = request.getParameter("MSISDN");
		MSISDN = DecryptKey.decrypt(MSISDN);

		String Service = request.getParameter("Service");
		Service = DecryptKey.decrypt(Service);

		String Class = request.getParameter("Class");
		Class = DecryptKey.decrypt(Class);
		String mode = request.getParameter("mode");
		mode = DecryptKey.decrypt(mode);
		String requestid = request.getParameter("requestid");
		requestid = DecryptKey.decrypt(requestid);
		String Requesttime = request.getParameter("requesttime");
		Requesttime = DecryptKey.decrypt(Requesttime);
		String CircleId = request.getParameter("CircleId");
		CircleId = DecryptKey.decrypt(CircleId);
		String Loginid = request.getParameter("Loginid");
		Loginid = DecryptKey.decrypt(Loginid);

		String password = request.getParameter("password");

		String org_id = request.getParameter("org_id");
		org_id = DecryptKey.decrypt(org_id);
		String CallBackURL = request.getParameter("CallBackURL");
		CallBackURL = DecryptKey.decrypt(CallBackURL);
		String from = request.getParameter("From");
		from = DecryptKey.decrypt(from);
		String param1 = request.getParameter("param1");
		param1 = DecryptKey.decrypt(param1);
		String param2 = request.getParameter("param2");
		param2 = DecryptKey.decrypt(param2);
		String param3 = request.getParameter("param3");
		param3 = DecryptKey.decrypt(param3);
		String CGStatusCode;
		String r;

		System.out.println("Action: " + Action + "\nMSISDN: " + MSISDN + "\nService= " + Service + "\nClass= " + Class
				+ "\nmode: " + mode + "\nrequestid: " + requestid);
		System.out.println("\nRequesttime: " + Requesttime + "\nCircleId: " + CircleId + "\nLoginid: " + Loginid
				+ "\npassword: " + password + "\norg_id: " + org_id + "\nCallBackURL: " + CallBackURL);
		System.out.println("from: " + from + "\nparam1: " + param1 + "\nparam2: " + param2 + "\nparam3: " + param3);

		if (MSISDN == null || Action == null || Service == null || Class == null || mode == null || requestid == null
				|| Requesttime == null || CircleId == null || Loginid == null || password == null || org_id == null
				|| CallBackURL == null || from == null || param3 == null) {
			System.out.println("Mandatory parameters missing!!\n");
			System.out.println(
					"MSISDN: " + MSISDN + "\nAction= " + Action + "\nService= " + Service + "\nClass: " + Class);
			System.out.println("\nmode= " + mode + "\nrequestid= " + requestid + "\nRequesttime: " + Requesttime);
			System.out.println("\nCircleId: " + CircleId + "\nLoginid: " + Loginid + "\npassword: " + password
					+ "\norg_id: " + org_id);
			System.out.println("\nCallBackURL: " + CallBackURL + "\nfrom: " + from + "\nparam3: " + param3);
			CGStatusCode = "CGW120";
			r = "?CG_TrxnId=" + RandomStringUtils.randomAlphanumeric(20) + "&MSISDN=" + MSISDN
					+ "&CGStatus=FAILED&CGStatusCode=" + CGStatusCode + "&CGStatusText=FAILURE&TRXID="
					+ (int) Math.ceil((Math.random() * 1000000));
			System.out.println("CallBackURL : " + CallBackURL + r);
			response.sendRedirect(CallBackURL + r);

		}

		try {
			if (MSISDN.length() > 12 || Action.length() > 10 || Service.length() > 25 || Class.length() > 25
					|| mode.length() > 20 || requestid.length() > 256 || Requesttime.length() > 20
					|| CircleId.length() > 2 || Loginid.length() > 30 || password.length() > 100 || org_id.length() > 30
					|| CallBackURL.length() > 256 || from.length() > 30 || param1.length() > 100
					|| param2.length() > 100 || param3.length() > 100) {
				System.out.println("Parameters length exceeds!!\n");
				System.out.println("MSISDN: " + MSISDN.length() + "\nAction= " + Action.length() + "\nService= "
						+ Service.length() + "\nClass= " + Class.length() + "\nmode= " + mode.length() + "\nrequestid= "
						+ requestid.length());
				System.out.println("\nRequesttime: " + Requesttime.length() + "\nCircleId: " + CircleId.length()
						+ "\nLoginid: " + Loginid.length() + "\npassword: " + password.length() + "\norg_id: "
						+ org_id.length());
				System.out.println("\nCallBackURL: " + CallBackURL.length() + "\nfrom: " + from.length() + "\nparam1: "
						+ param1.length() + "\nparam2: " + param2.length() + "\nparam3: " + param3.length());
				CGStatusCode = "CGW131";
				r = "?CG_TrxnId=" + RandomStringUtils.randomAlphanumeric(20) + "&MSISDN=" + MSISDN
						+ "&CGStatus=FAILED&CGStatusCode=" + CGStatusCode + "&CGStatusText=FAILURE&TRXID="
						+ (int) Math.ceil((Math.random() * 1000000));
				System.out.println("CallBackURL : " + CallBackURL + r);
				response.sendRedirect(CallBackURL + r);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		String[] param = param3.split("\\|");
		System.out.println("param : " + Arrays.toString(param));
		System.out.println("param[0] : " + param[0]);
		System.out.println("param[1] : " + param[1]);
		System.out.println("param[2] : " + param[2]);
		// String pass="1379675473328|345";
		// System.out.println("password:" +pass);
		// String enValue =null;
		// enValue = DecryptKey.encrypt(pass);
		// System.out.println("Encoded password is : " + password);
		// password = URLDecoder.decode(password, "UTF-8");
		System.out.println("Encrypted password is : " + password);
		String decValue = null;
		decValue = DecryptKey.decrypt(password);
		System.out.println("Decrypted password is : " + decValue);
		String[] token = decValue.split("\\|");
		System.out.println("decrypted token contains : " + Arrays.toString(token));
		String timestamp = token[0];
		System.out.println("token : " + Arrays.toString(token));
		long time = Long.valueOf(timestamp).longValue();
		System.out.println("time is : " + time);
		long current = System.currentTimeMillis();
		System.out.println("current system time is : " + current);
		System.out.println("difference in time is : " + (current - time));
		if (current - time <= 1800000) {

			System.out.println("\nCallBackURL : " + CallBackURL);
			System.out.println("imageURL : " + param[2]);
			System.out.println("DeclineURL : " + param[0]);
			String URL = "CallBackURL=" + URLEncoder.encode(CallBackURL, "UTF-8") + "&imageURL="
					+ URLEncoder.encode(param[2], "UTF-8") + "&DeclineURL=" + URLEncoder.encode(param[0], "UTF-8")
					+ "&msisdn=" + MSISDN + "&mode=" + mode + "&srvkey=" + Class;
			System.out.println("URL: " + URL);
			System.out.println("/VodaNewConsent/CGDisplay.jsp?" + URL);
			response.sendRedirect("/VodaNewConsent/CGDisplay.jsp?" + URL);

		} else {
			System.out.println("In else loop: token expired!!!!");
			System.out.println("DeclineURL : " + param[0]);
			response.sendRedirect(param[0]);
		}

	}

}
