package com.onmobile.IDEA;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drg.properties.Utility;

/**
 * Servlet implementation class ideaCGmiddle
 */
public class ideaCGmiddle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	Utility util = null;

	public ideaCGmiddle() {
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("------Inside middle----------------");
		String refid = request.getParameter("refid");
		System.out.println("refid: " + refid);
		String srurl = request.getParameter("srurl");
		String srurl1 = URLDecoder.decode(srurl, "UTF-8");
		System.out.println("CallBackURL: " + srurl);
		String frurl = request.getParameter("frurl");
		System.out.println("CallBackURL: " + frurl);
		String action = request.getParameter("action");
		System.out.println("Userr action :" + action);
		String msisdn = request.getParameter("msisdn");
		System.out.println("Msisdn :" + msisdn);
		String srvkey = request.getParameter("srvkey");
		String mode = request.getParameter("mode");
		String originator = request.getParameter("originator");
		System.out.println("Mode :" + mode);
		System.out.println("Srvkey= " + srvkey);
		System.out.println("originator = " + originator);
		String info = request.getParameter("info");
		String rurlD = request.getParameter("rurlD");

		String actURL = (String) util.get("IDEA_CONSENT_URL");
		String url = actURL + srurl + "&msisdn=" + msisdn + "&srvkey=" + srvkey + "&action=yes&refid=" + refid
				+ "&mode=wap&info=" + info + "&rurlD=" + rurlD + "&originator=" + originator;

		// response.setStatus(200);
		System.out.println("srurl : " + srurl1);
		response.sendRedirect(srurl1);

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
