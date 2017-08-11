package com.onmobile.Airtel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.drg.properties.Utility;
import com.onmobile.Airtel.AirtelAesEncDec;

public class IBMPortal extends HttpServlet {

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
		System.out.println("HTTP request Method : " + request.getMethod());
		String value = request.getParameter("mth");
		String MSISDN = request.getParameter("m");
		String productID = request.getParameter("pi");
		String pName = request.getParameter("pn");
		String pPrice = request.getParameter("pp");
		String pPriceUnit = request.getParameter("pu");
		String pVal = request.getParameter("pv");
		String pCName = request.getParameter("pc");
		String FirstConfirmationDTTM = request.getParameter("dt");
		String aocvalue = request.getParameter("aoc");
		String CpId = request.getParameter("ci");
		String CpName = request.getParameter("cpw");
		String rUrl = request.getParameter("ru");
		String opt4 = request.getParameter("opt4");
		String rtg = request.getParameter("rtg");
		String am = request.getParameter("am");
		String ar = request.getParameter("ar");
		String ac = request.getParameter("ac");
		String adt = request.getParameter("adt");
		String chn = request.getParameter("chn");
		String opt1 = request.getParameter("opt1");
		System.out.println("doProcess(): Parameters Received: value: " + value + "\nMSISDN: " + MSISDN + "\nproductID: "
				+ productID + "\npName: " + pName + "\npPrice: " + pPrice + "\npPriceUnit: " + pPriceUnit + "\npVal: "
				+ pVal + "\npCName: " + pCName + "\nFirstConfirmationDTTM: " + FirstConfirmationDTTM + "\naocvalue: "
				+ aocvalue + "\nCpId: " + CpId + "\nCpName: " + CpName + "\nrUrl: " + rUrl + "\nopt4: " + opt4
				+ "\nrtg: " + rtg + "\nam: " + am + "\nar: " + ar + "\nac: " + ac + "\nadt: " + adt + "\nchn: " + chn
				+ "\nopt1: " + opt1);

		response.setContentType("text/html");
		AirtelAesEncDec air = new AirtelAesEncDec();

		String ENC = (String) util.get("ENCRYPTKEY");
		String m = air.encrypt(MSISDN, ENC);
		System.out.println("msisdn encrypt: " + m);
		String URL = "rUrl=" + URLEncoder.encode(rUrl, "UTF-8") + "&MSISDN=" + MSISDN + "&pName=" + pName
				+ "&productID=" + productID + "&SpPrice=" + pPrice + "&opt4=" + URLEncoder.encode(opt4, "UTF-8")
				+ "&rtg=" + rtg + "&am=" + am + "&ar=" + ar + "&ac=" + ac + "&adt=" + adt + "&chn=" + chn + "&opt1="
				+ URLEncoder.encode(opt1, "UTF-8");

		System.out.println("/IBMDoubleConfirm/DCPage.jsp?" + URL);
		response.sendRedirect("/IBMDoubleConfirm/DCPage.jsp?" + URL);
	}
}
