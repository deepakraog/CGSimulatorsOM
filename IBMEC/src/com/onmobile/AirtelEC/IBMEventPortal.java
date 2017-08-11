package com.onmobile.AirtelEC;

import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onmobile.Airtel.AirtelAesEncDec;

public class IBMEventPortal extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String opname = request.getParameter("mth");

		String MSISDN = request.getParameter("m");
		String productID = request.getParameter("pi");
		String pName = request.getParameter("pn");
		String pPrice = request.getParameter("pp");
		String pDisc = request.getParameter("pd");
		String pmime = request.getParameter("pmt");
		String mode = request.getParameter("dc");
		String et = request.getParameter("et");
		String ci = request.getParameter("ci");
		String pPriceUnit = request.getParameter("cur");
		String cprtid = request.getParameter("cprtid");
		String skw = request.getParameter("skw");
		String srcd = request.getParameter("srcd");
		String curl = request.getParameter("curl");
		String ru = request.getParameter("ru");
		String pnp = request.getParameter("pnp");
		String imgurl = request.getParameter("imgurl");
		String up = request.getParameter("up");
		String cpx = request.getParameter("cpx");
		String opt3 = request.getParameter("opt3");

		System.out.println("curl : " + curl);

		response.setContentType("text/html");
		AirtelAesEncDec air = new AirtelAesEncDec();

		String URL = "rUrl=" + URLEncoder.encode(ru, "UTF-8") + "&curl=" + URLEncoder.encode(curl, "UTF-8") + "&MSISDN="
				+ MSISDN + "&cpx=" + cpx + "&opt3=" + URLEncoder.encode(opt3, "UTF-8") + "&imgurl="
				+ URLEncoder.encode(imgurl, "UTF-8");
		System.out.println("redirecting to : /IBMEC/ECPage.jsp?" + URL);

		response.sendRedirect("/IBMEC/ECPage.jsp?" + URL);
	}
}