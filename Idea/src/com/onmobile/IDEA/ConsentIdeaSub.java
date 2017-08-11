package com.onmobile.IDEA;

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
 * Servlet implementation class ConsentIdea
 */
public class ConsentIdeaSub extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Utility util = null;

	public ConsentIdeaSub() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.print("--------------inside ConsentIdeaSUB---------------");

		String refid = request.getParameter("refid");
		System.out.println("refid: " + refid);
		String srurl = request.getParameter("srurl");
		// String srurl1 = URLDecoder.decode(srurl, "UTF-8");
		System.out.println("CallBackURL: " + srurl);
		String frurl = request.getParameter("frurl");
		System.out.println("CallBackURL: " + frurl);
		String action = request.getParameter("action");
		System.out.println("Userr action :" + action);
		String msisdn = request.getParameter("msisdn");
		System.out.println("Msisdn :" + msisdn);
		String srvkey = request.getParameter("srvkey");
		String mode = request.getParameter("mode");
		System.out.println("Mode :" + mode);
		System.out.println("Srvkey= " + srvkey);
		String info = request.getParameter("info");
		// String rurlD = request.getParameter("rurlD");
		String originator = request.getParameter("originator");
		System.out.println("originator = " + originator);

		String actURL = (String) util.get("IDEA_SUBS_ACTIVATION_URL");
		String url = actURL + msisdn + "&siteid=1&Type=P&action=ACT&User=mmp&Pass=mmp&remarks=ACTIVATION&srvkey="
				+ srvkey + "&mode=" + mode + "&Refid=" + refid + "&info=" + info
				+ "&originator=&status=SUCCESS&price=50";

		try {
			System.out.println("Hitting url :" + url);
			URL url1 = new URL(url);
			HttpURLConnection uc = ((HttpURLConnection) url1.openConnection());
			uc.connect();
			System.out.println("The status of hitting callbackURL : " + uc.getResponseCode());
			System.out.println("response Message :" + uc.getResponseMessage());
			uc.disconnect();
		} catch (IOException e) {
			System.out.println("Thread Additional : IOException : " + e);
		}

	}

}
