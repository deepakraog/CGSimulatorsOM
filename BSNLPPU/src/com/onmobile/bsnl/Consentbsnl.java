package com.onmobile.bsnl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drg.properties.Utility;

/**
 * Servlet implementation class Consentbsnl
 */
public class Consentbsnl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	Utility util = null;
	
    public Consentbsnl() {
        super();
        // TODO Auto-generated constructor stub
    }

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
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doProcess(HttpServletRequest request,HttpServletResponse response) throws Throwable
	{
		System.out.print("--------------inside Consent---------------");

		String srurl= request.getParameter("srurl");
		System.out.println("CallBackURL: "+srurl);
		String frurl= request.getParameter("frurl");
		System.out.println("CallBackURL: "+frurl);
		String action=request.getParameter("action");
		System.out.println("Userr action :" +action);
		String msisdn=request.getParameter("msisdn");
		System.out.println("Msisdn :" +msisdn);
		String srvkey=request.getParameter("srvkey");
		String refid=request.getParameter("refid");
		String mode=request.getParameter("mode");
		System.out.println("Mode :" +mode);
		System.out.println("refid :" +refid);
		System.out.println("Srvkey= " +srvkey);
		String ppuURL = (String)util.get("PPU_ACTIVATION_URL");
		String url=ppuURL + msisdn + 
				"&type=P&user=mmp&pass=mmp&srvkey=EVENT&eventkey="+srvkey+"&mode="+mode+"&refid="+refid;


	/*	if(action.equalsIgnoreCase("no"))
		{
			response.sendRedirect(frurl);
		}		
		else
		{*/
		
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

			if(rcode == 200)
			response.sendRedirect(srurl);
			
			
	}


}
