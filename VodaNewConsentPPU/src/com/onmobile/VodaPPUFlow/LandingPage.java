package com.onmobile.VodaPPUFlow;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;





public class LandingPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public void doProcess(HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.print("--------------inside Voda2 PPU LandingPage-------------------\n");

		String MSISDN = request.getParameter("MSISDN");
		MSISDN = DecryptKey.decrypt(MSISDN);
		String Eventid = request.getParameter("Eventide");
		Eventid = DecryptKey.decrypt(Eventid);
		String Device = request.getParameter("device");
		Device = DecryptKey.decrypt(Device);
		String inid = request.getParameter("inid");
		inid = DecryptKey.decrypt(inid);
		String indata = request.getParameter("indata");
		indata = DecryptKey.decrypt(indata);
		String tCharge = request.getParameter("tCharge");
		tCharge = DecryptKey.decrypt(tCharge);
		String Reqtype = request.getParameter("Reqtype");
		Reqtype = DecryptKey.decrypt(Reqtype);
		String Wapurl = request.getParameter("wapurl");
		Wapurl = DecryptKey.decrypt(Wapurl);
		String cSize = request.getParameter("cSize");
		cSize = DecryptKey.decrypt(cSize);
		String Wapnode = request.getParameter("Wapnode");
		Wapnode = DecryptKey.decrypt(Wapnode);
		String Invoiceid = request.getParameter("Invoiceid");
		Invoiceid = DecryptKey.decrypt(Invoiceid);
		String mode = request.getParameter("mode");
		mode = DecryptKey.decrypt(mode);
		String requestid = request.getParameter("Reqid");
		requestid = DecryptKey.decrypt(requestid);
		String Requesttime = request.getParameter("Requesttime");
		Requesttime = DecryptKey.decrypt(Requesttime);
		String CircleId = request.getParameter("CircleId");
		CircleId = DecryptKey.decrypt(CircleId);
		String Loginid  = request.getParameter("Loginid");
		Loginid = DecryptKey.decrypt(Loginid);

		String password  = request.getParameter("password");

		String Cpid = request.getParameter("Cpid");
		Cpid = DecryptKey.decrypt(Cpid);
		String Ocg_pwd = request.getParameter("Ocg_pwd");
		Ocg_pwd = DecryptKey.decrypt(Ocg_pwd);
		String Type  = request.getParameter("Type");
		Type = DecryptKey.decrypt(Type);
		String Price  = request.getParameter("Price");
		Price = DecryptKey.decrypt(Price);
		String param1 = request.getParameter("param1");
		param1 = DecryptKey.decrypt(param1);
		String param2 = request.getParameter("param2");
		param2 = DecryptKey.decrypt(param2);
		String param3 = request.getParameter("param3");
		param3 = DecryptKey.decrypt(param3);
		String CGStatusCode;
		String r;

		System.out.println("MSISDN: " + MSISDN + "\nEventid= " + Eventid + "\nDevice= " + Device +  "\ninid= " + inid +  "\nindata= " + indata +  "\ntCharge= " + tCharge );
		System.out.println("\nReqtype: " + Reqtype + "\nWapurl= " + Wapurl + "\ncSize= " + cSize +  "\nWapnode= " + Wapnode +  "\nInvoiceid= " + Invoiceid + "\nmode: " + mode  + "\nrequestid: "+ requestid );
		System.out.println("\nRequesttime: " + Requesttime + "\nCircleId: "+ CircleId + "\nLoginid: " + Loginid + "\npassword: "+ password + "\nCpid: " + Cpid  );
		System.out.println("\nOcg_pwd: " + Ocg_pwd + "\nType: " + Type  + "\nPrice: " + Price + "\nparam1: " + param1+ "\nparam2: " + param2 + "\nparam3: " + param3);

		if ( MSISDN == null || Eventid == null || tCharge == null || Reqtype == null || Wapurl == null || cSize == null ||
				mode == null || requestid == null || Requesttime == null || CircleId == null || Loginid == null || password == null ||
				Cpid == null || Ocg_pwd == null || Type == null || Price == null || param3 == null) {
			System.out.println("Mandatory parameters missing!!\n");
			System.out.println("MSISDN: " + MSISDN + "\nEventid= " + Eventid +  "\ntCharge= " + tCharge  + "\nReqtype: "+ Reqtype );
			System.out.println("\nWapurl= " + Wapurl + "\ncSize= " + cSize  + "\nmode: " + mode  + "\nrequestid: "+ requestid );
			System.out.println("\nRequesttime: " + Requesttime + "\nCircleId: "+ CircleId + "\nLoginid: " + Loginid + "\npassword: "+ password + "\nCpid: " + Cpid  );
			System.out.println("\nOcg_pwd: " + Ocg_pwd + "\nType: " + Type  + "\nPrice: " + Price + "\nparam3: " + param3);
			CGStatusCode="CGW302";
			r="?CG_TrxnId="+RandomStringUtils.randomAlphanumeric(20)+"&MSISDN="+MSISDN+"&CGStatus=FAILED&CGStatusCode="+CGStatusCode+"&CGStatusText=null&OCG_TrxnID=" + (int)Math.ceil((Math.random()*1000000));
			System.out.println("Wapurl : " + Wapurl + r);
			response.sendRedirect(Wapurl+r);

		}

		try {
			if ( MSISDN.length() > 12 || Eventid.length() > 30|| Device.length() > 200 || inid.length() > 12 || indata.length() > 200 || tCharge.length() > 10 || 
					Reqtype.length() > 4 || Wapurl.length() > 200 || cSize.length() > 4 || Wapnode.length() > 200 || Invoiceid.length() > 10 ||
					mode.length() > 20 || requestid.length() > 256 || Requesttime.length() > 20 || CircleId.length() > 2 || Loginid.length() > 30 || password.length() > 100 ||
					Cpid.length() > 30 || Ocg_pwd.length() > 30 || Type.length() > 50 || Price.length() > 5  || param1.length() > 100|| param2.length() > 100 || param3.length() > 100) {
				System.out.println("Parameters length exceeds!!\n");
				System.out.println("MSISDN: " + MSISDN.length() + "\nEventide= " + Eventid.length() + "\nDevice= " + Device.length() +  "\ninid= " + inid.length() +  "\nindata= " + indata.length() +  "\ntCharge= " + tCharge.length()  + "\nReqtype: "+ Reqtype.length() );
				System.out.println("\nWapurl= " + Wapurl.length() + "\ncSize= " + cSize.length() +  "\nWapnode= " + Wapnode.length() +  "\nInvoiceid= " + Invoiceid.length() + "\nmode: " + mode.length()  + "\nrequestid: "+ requestid.length() );
				System.out.println("\nRequesttime: " + Requesttime.length() + "\nCircleId: "+ CircleId.length() + "\nLoginid: " + Loginid.length() + "\npassword: "+ password.length() + "\nCpid: " + Cpid.length()  );
				System.out.println("\nOcg_pwd: " + Ocg_pwd.length() + "\nType: " + Type.length()  + "\nPrice: " + Price.length() + "\nparam1: " + param1.length() + "\nparam2: " + param2.length() + "\nparam3: " + param3.length());
				CGStatusCode="CGW315";
				r="?CG_TrxnId="+RandomStringUtils.randomAlphanumeric(20)+"&MSISDN="+MSISDN+"&CGStatus=FAILED&CGStatusCode="+CGStatusCode+"&CGStatusText=null&OCG_TrxnID=" + (int)Math.ceil((Math.random()*1000000));
				System.out.println("Wapurl : " + Wapurl + r);
				response.sendRedirect(Wapurl+r);
			}
		}catch (Exception e){
			System.out.println(e);
		}

		//String pass="1379675473328|345";
		//System.out.println("password:" +pass);
		//String enValue =null;
		//enValue = DecryptKey.encrypt(pass);
		//System.out.println("Encoded password is : " + password);
		//password = URLDecoder.decode(password, "UTF-8");
		String[] param = param3.split("\\|");
		System.out.println("param : "+ Arrays.toString(param));
		System.out.println("param[0] : " + param[0]);
		System.out.println("param[1] : " + param[1]);
		System.out.println("param[2] : " + param[2]);
		System.out.println("Encrypted password is : "+ password);
		String decValue =null;
		decValue = DecryptKey.decrypt(password); 
		System.out.println("Decrypted password is : "+ decValue);
		String[] token = decValue.split("\\|");
		System.out.println("decrypted token contains : "+ Arrays.toString(token));
		String timestamp = token[0];
		System.out.println("token : " + Arrays.toString(token));
		long time = Long.valueOf(timestamp).longValue();
		System.out.println("time is : " + time);
		long current = System.currentTimeMillis();
		System.out.println("current system time is : " + current);
		System.out.println("difference in time is : " + (current - time));
		if ( current - time <= 1800000){


			String URL="Wapurl="+URLEncoder.encode(Wapurl,"UTF-8")+"&msisdn="+MSISDN+"&mode="+mode+"&eventkey="+Eventid+"&imageURL="+URLEncoder.encode(param[2],"UTF-8")+"&DeclineURL="+URLEncoder.encode(param[0],"UTF-8");
			System.out.println("URL: "+URL);
			System.out.println("/VodaNewConsentPPU/CGDisplay.jsp?"+URL);
			response.sendRedirect("/VodaNewConsentPPU/CGDisplay.jsp?"+URL);

		}else{
			System.out.println("In else loop: token expired!!!!");
			CGStatusCode="CGW301";
			r="?CG_TrxnId="+RandomStringUtils.randomAlphanumeric(20)+"&MSISDN="+MSISDN+"&CGStatus=FAILED&CGStatusCode="+CGStatusCode+"&CGStatusText=null";
			System.out.println("Wapurl : " + Wapurl + r);
			response.sendRedirect(Wapurl+r);
		}



	}

}
