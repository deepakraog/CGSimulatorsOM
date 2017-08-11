package com.onmobile.VodaPPUFlow;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;

public class ConsentDB extends HttpServlet{

	private static final long serialVersionUID = 1L;
		public void doGet(HttpServletRequest request,HttpServletResponse response){
			try {
				doProcess(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void doPost(HttpServletRequest request,HttpServletResponse response){
			try {
				doProcess(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void doProcess(HttpServletRequest request,HttpServletResponse response) throws Throwable{
			System.out.print("--------------inside ConsentDB---------------\n");
			
			String Wapurl= request.getParameter("Wapurl");
			System.out.println("Wapurl: "+Wapurl);
			String msisdn= request.getParameter("msisdn");
			System.out.println("msisdn: "+msisdn);
			String mode= request.getParameter("mode");
			System.out.println("mode: "+mode);
			String eventkey= request.getParameter("eventkey");
			System.out.println("eventkey: "+eventkey);
			String action= request.getParameter("action");
			System.out.println("action: "+action);
			String DeclineURL=request.getParameter("DeclineURL");
			System.out.println("DeclineURL: "+DeclineURL);
			String StatusCode;
			String Status;
			String r;
			
			
			if(action.equalsIgnoreCase("no"))
			{
				StatusCode = "CGW330";
				Status = "FAILED";
				r="?CG_TrxnId="+RandomStringUtils.randomAlphanumeric(20)+"&MSISDN="+msisdn+"&CGStatus="+Status+"&CGStatusCode="+StatusCode+"&CGStatusText=null&OCG_TrxnID=" + (int)Math.ceil((Math.random()*1000000));
				System.out.println("Redirecting user to : " + DeclineURL + r);
				response.sendRedirect(DeclineURL+r);
			}else{
			
				StatusCode = "CGW200";
				Status = "SUCCESS";
				r="?CG_TrxnId="+RandomStringUtils.randomAlphanumeric(20)+"&MSISDN="+msisdn+"&CGStatus="+Status+"&CGStatusCode="+StatusCode+"&CGStatusText=null&OCG_TrxnID=" + (int)Math.ceil((Math.random()*1000000));
				System.out.println("Redirecting user to : " + Wapurl + r);
				response.sendRedirect(Wapurl+r);
				
			}
		}
	
}
