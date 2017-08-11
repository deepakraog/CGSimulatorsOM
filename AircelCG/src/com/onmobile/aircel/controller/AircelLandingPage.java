package com.onmobile.aircel.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drg.properties.Utility;
import com.onmobile.aircel.helper.Validator;

/**
 * @author somashekhar.wali
 * This is a landing page for aircel CG Page. Here
 * all the validations are taken care and the then as a response CG Page is sent back.
 *
 */
public class AircelLandingPage extends HttpServlet {
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

	/**
	 * This method as the logic to first validate all the mandatory params then
	 * set the additional params requrired in redirect URL and send the response
	 * as CG PAGE.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 *             exception
	 */
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.print("--------------inside AIRCEL CG LandingPage-------------------\n");

		Map<String, String> params = new HashMap<>();
		boolean errorFlag = false;

		String MSISDN = request.getParameter("msisdn");
		params.put("MSISDN", MSISDN);
		String productId = request.getParameter("productid");
		params.put("productId", productId);
		String mpname = request.getParameter("mpname");
		params.put("mpname", mpname);
		String pprice = request.getParameter("pprice");
		params.put("pprice", pprice);
		String pval = request.getParameter("pval");
		params.put("pval", pval);
		String cpid = request.getParameter("cpid");
		params.put("cpid", cpid);
		String userid = request.getParameter("userid");
		params.put("userid", userid);
		String password = request.getParameter("password");
		params.put("password", password);
		String bearermode = request.getParameter("bearermode");
		params.put("bearermode", bearermode);
		String seid = request.getParameter("seid");
		params.put("seid", seid);
		String transid = request.getParameter("transid");
		params.put("transid", transid);
		String contentid = request.getParameter("contentid");
		params.put("contentid", contentid);
		String Wap_mdata = request.getParameter("Wap_mdata");

		params.put("Wap_mdata", Wap_mdata);
		String redirecturl = request.getParameter("redirecturl");
		params.put("redirecturl", redirecturl);
		String cpname = request.getParameter("cpname");
		params.put("cpname", cpname);

		String imgURL = Wap_mdata;
		if (Wap_mdata.indexOf('|') == 0) {
			imgURL = Wap_mdata.substring(1);
		}
		params.put("imgURL", imgURL);

		System.out.println("MSISDN: " + MSISDN + "\nProductId= " + productId + "\nmpName= " + mpname + "\npPrice: "
				+ pprice + "\npValidity: " + pval);
		System.out.println("cpid: " + cpid + "\nuserid= " + userid + "\npassword= " + password + "\npbearermode: "
				+ bearermode + "\nseid: " + seid);
		System.out.println("transid: " + transid + "\ncontentid= " + contentid + "\nWap_mdata= " + Wap_mdata
				+ "\nredirecturl: " + redirecturl + "\ncpname: " + cpname);

		try {
			Validator.validateMandatoryParams(params);
		} catch (NullPointerException e) {
			errorFlag = true;
			String reason = e.getMessage();

			redirecturl = redirecturl + "?&transid=" + transid;
			System.out.println("\n \n Exception occured one of the paramter is missing " + e);
		}
		if (errorFlag) {
			System.out.println("\nredirecturl : " + redirecturl);
			response.sendRedirect(redirecturl);
		} else {
			System.out.println("\nredirecturl : " + redirecturl);
			String URL = "redirecturl=" + URLEncoder.encode(redirecturl, "UTF-8") + "&msisdn=" + MSISDN + "&seid="
					+ seid + "&productid=" + productId + "&contentid=" + contentid + "&transid=" + transid + "&cpid="
					+ cpid + "&pprice=" + pprice + "&imgURL=" + URLEncoder.encode(imgURL, "UTF-8");
			System.out.println("URL: " + URL);
			System.out.println("/AircelCG/AircelCGDisplay.jsp?" + URL);
			response.sendRedirect("/AircelCG/AircelCGDisplay.jsp?" + URL);
		}
	}
}
