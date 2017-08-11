package com.deepak.cvasqaprojects;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LandingPage extends HttpServlet {

	/**
	 * 
	 */
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
		System.out.print("--------------inside MNP LandingPage-------------------\n");
		String Msisdn = request.getParameter("Msisdn");
		System.out.println("msisdn read is :" + Msisdn);
		String State = null;
		String Operator = null;
		String Prefix = Msisdn.substring(0, 1);
		int Digit = Integer.parseInt(Prefix);
		switch (Digit) {

		case 0:
			State = "ALL";
			Operator = "SMILE";
			break;

		case 1:
			State = "ALL";
			Operator = "AIRCEL";
			break;

		case 2:
			State = "ANDHRA PRADESH";
			Operator = "BSNL";
			break;

		case 3:
			State = "TAMILNADU";
			Operator = "VODAFONE";
			break;

		case 4:
			State = "DELHI";
			Operator = "BSNL";
			break;

		case 5:
			State = "UTTAR PRADESH";
			Operator = "IDEA";
			break;

		case 6:
			State = "ASSAM";
			Operator = "BSNL";
			break;

		case 7:
			State = "RAJASTHAN";
			Operator = "BSNL";
			break;

		case 8:
			State = "WEST BENGAL";
			Operator = "AIRTEL";
			break;

		case 9:
			State = "MAHARASHTRA";
			Operator = "VODAFONE";
			break;

		}

		System.out.println("The msisdn belongs to : " + State);
		String Status = null;
		String Res = null;

		/*
		 * HttpServletResponse res; res.setStatus(arg0);
		 * res.setContentType("text/html") OutputStream out. Servlet Response
		 */

		if (State == "Unknown") {

			Status = "1";
			Res = ResponseXml.response(State, Status, Msisdn, Operator, response);

		} else if (State == "Unknown1") {
			response.setStatus(500);
			Res = "Http response failure";

		} else {
			Status = "0";
			response.setStatus(200);
			Res = ResponseXml.response(State, Status, Msisdn, Operator, response);
		}
		System.out.println("Response is : " + Res);
	}

}
