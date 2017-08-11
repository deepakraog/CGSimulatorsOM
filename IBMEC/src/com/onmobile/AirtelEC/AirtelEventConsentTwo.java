package com.onmobile.AirtelEC;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AirtelEventConsentTwo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			doAction(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			doAction(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String responsestr = "ERROR|INVALID_STATUS";
		String s = request.getParameter("s");
		String rc = request.getParameter("rc");
		String rm = request.getParameter("rm");
		String msisdn = request.getParameter("msisdn");
		String eventkey = request.getParameter("eventkey");
		String srvkey = request.getParameter("srvkey");
		String siteid = request.getParameter("siteid");
		String type = request.getParameter("type");
		String user = request.getParameter("user");
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");

		if ((msisdn == null) || (msisdn.equals("")) || (msisdn.equalsIgnoreCase("null")) || (eventkey == null)
				|| (eventkey.equals("")) || (eventkey.equalsIgnoreCase("null")) || (srvkey == null)
				|| (srvkey.equals("")) || (srvkey.equalsIgnoreCase("null")) || (siteid == null) || (siteid.equals(""))
				|| (siteid.equalsIgnoreCase("null")) || (type == null) || (type.equals(""))
				|| (type.equalsIgnoreCase("null")) || (user == null) || (user.equals(""))
				|| (user.equalsIgnoreCase("null")) || (mode == null) || (mode.equals(""))
				|| (mode.equalsIgnoreCase("null")) || (pass == null) || (pass.equals(""))
				|| (pass.equalsIgnoreCase("null"))) {
			responsestr = "ERROR|Mandatory parameter missing";
			System.out.println(
					msisdn + " " + eventkey + " " + srvkey + " " + type + " " + user + " " + mode + " " + pass);
		} else if (s.equalsIgnoreCase("success")) {
			responsestr = "SUCCESS";
		} else if (s.equalsIgnoreCase("aoc_decline")) {
			responsestr = "AOC_DECLINE";
		} else if (s.equalsIgnoreCase("failure")) {
			if (((rc == null) || (rc.trim().equals("")) || (rc.equalsIgnoreCase("null")))
					&& (rm.startsWith("Insufficient balance")))
				responsestr = "BAL_LOW";
			else {
				responsestr = "ERROR|" + rc + "|" + rm;
			}
		}
		response.getOutputStream().write(responsestr.getBytes());
	}
}