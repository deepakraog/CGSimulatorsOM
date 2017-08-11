package com.onmobile.VodaFlow;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drg.properties.Utility;

@SuppressWarnings("serial")
public class ConsentDB extends HttpServlet{

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
			System.out.print("--------------inside ConsentDB---------------");
			
			String CallBackURL= request.getParameter("CallBackURL");
			System.out.println("CallBackURL: "+CallBackURL);
			String DeclineURL= request.getParameter("DeclineURL");
			System.out.println("DeclineURL: "+DeclineURL);
			String msisdn= request.getParameter("msisdn");
			System.out.println("msisdn: "+msisdn);
			String mode= request.getParameter("mode");
			System.out.println("mode: "+mode);
			String srvkey= request.getParameter("srvkey");
			System.out.println("srvkey: "+srvkey);
			String action= request.getParameter("action");
			System.out.println("action: "+action);
			String actURL = (String)util.get("SUBS_ACTIVATION_URL");		
			
			String r="?CG_TrxnId=1234abcd&TRXID=567849&MSISDN="+msisdn+"&CGStatus=Success&CGStatusCode=200&CGStatusText=null";
			
			if(action.equalsIgnoreCase("no"))
			{
				response.sendRedirect(DeclineURL);
			}else{
			
				String url=actURL + msisdn + 
						"&siteid=1&type=P&user=mmp&pass=mmp&remarks=ACTIVATION&srvkey="+srvkey+"&mode="+mode+"&refid="+Math.ceil((Math.random()*1000000));

				System.out.println("<-------URL:----------------> "+url);
								 
				 int rcode=-1;


					try
					{
						System.out.println("Hitting url :" + url);
						URL url1 = new URL(url);
						HttpURLConnection uc = ((HttpURLConnection)url1.openConnection());
						uc.connect();
						rcode=uc.getResponseCode();
						System.out.println("The status of hitting callbackURL : " + uc.getResponseCode());
						System.out.println("response Message :" + uc.getResponseMessage());
						uc.disconnect();
					}
					catch (IOException e)
					{
						System.out.println("Thread Additional : IOException : " + e);
					}

					if(rcode == 200){
					response.sendRedirect(CallBackURL+r);
					System.out.println("final response url is CallBackURL+r"+CallBackURL+r);
					}else {
					response.sendRedirect(DeclineURL);
					}
			}
		}
	
}
