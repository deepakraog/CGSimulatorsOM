<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.onmobile.IDEA.IdeaAesEncDec"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.drg.properties.Utility"%>
<%--<%@page import="org.apache.commons.httpclient.HttpStatus"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!String msg = "Error";

	public boolean nullLength(String key, String str, boolean mandatory, int length) {
		System.out.println(key + ": " + str);
		if (mandatory && (str == null || str.trim().equals("") || str.equalsIgnoreCase("null"))) {
			msg = msg + key + " is null/empty, ";
			System.out.println("msg: " + msg);

			return true;
		} else if (mandatory) {
			if (str.length() > length) {
				msg = msg + key + " length is greater than " + length + ", ";
				System.out.println("msg: " + msg);
				return true;
			} else {
				return false;
			}

		} else if (!mandatory) {
			if (str == null || str.trim().equals("") || str.equalsIgnoreCase("null")) {
				return false;
			} else if (str.length() > length) {
				msg = msg + key + " length is greater than " + length + ", ";
				System.out.println("msg: " + msg);
				return true;
			}
			return false;
		} else
			return false;
	}%>
<%
	// Map map = new HashMap();
	String redirecturl = null;
	String dcurl = null;
	String errorurl = null;
	String imageURL = null;
	String info = null;
	String originator = null;

	boolean error = false;
	IdeaAesEncDec idea = new IdeaAesEncDec();
	Utility util = new Utility();
	util.load();
	String url = (String) util.get("IDEA_SUB_REDIRECT");

	String srvid = null;
	//out.println("service key : " + srvid1 );
	String refid = null;
	try {
		System.out.println("=========Request parameter to Idea CG===========================");
		String a = request.getParameter("A");
		srvid = idea.decryption(a);
		System.out.println("A  >> srvkey :: A =" + a + "  srvkey =" + srvid);
		out.println("A:" + a + " service key :: " + srvid);

		String subtype = request.getParameter("B");
		System.out.println("B >> subtype :: " + subtype);

		refid = request.getParameter("C");
		System.out.println("C >> refid ::" + refid);

		info = request.getParameter("D");
		System.out.println("D >> info :: " + info);

		String precharge = request.getParameter("E");
		System.out.println("E >> precharge ::" + precharge);

		originator = request.getParameter("F");
		System.out.println("F >> originator ::" + originator);

		String songname = request.getParameter("G");
		System.out.println("G >> songname ::" + songname);

		String fctimestamp = request.getParameter("H");
		System.out.println("H >> Time stamp of first consent ::" + fctimestamp);

		imageURL = request.getParameter("I");
		System.out.println("I >> imageURL ::" + imageURL);

		String j = request.getParameter("J");
		String userName = idea.decryption(j);
		System.out.println("J  >> userName :: J =" + j + "  userName =" + userName);

		String k = request.getParameter("K");
		String pass = idea.decryption(k);
		System.out.println("K  >> password :: K =" + k + "  password =" + pass);

		System.out.println("===========================END======================");

		error = false;
		msg = "Error2";
		if (nullLength("srvid", a, true, 45) | nullLength("subtype", subtype, true, 1)
				| nullLength("refid", refid, true, 25) | nullLength("info", info, false, 512)
				| nullLength("precharge", precharge, true, 1) | nullLength("originator", originator, true, 15)
				| nullLength("songname", songname, false, 45) | nullLength("fctimestamp", fctimestamp, true, 14)
				| nullLength("imageURL", imageURL, false, 45) | nullLength("userName", j, true, 35)
				| nullLength("pass", k, true, 35)) {
			error = true;
			System.out.println("Error : " + msg);
		}
		System.out.println("url : " + url);
		if (url == null || url.trim().isEmpty() || !url.startsWith("http://")) {
			msg = msg + " redirection url is not configured correctly";
			error = true;
			System.out.println("msg: " + msg);
		} else {
			double num = 10000000000.0 * Math.random();
			String sdptxnid = Long.valueOf(Math.round(num)).toString();
			redirecturl = url + "?srvkey=" + srvid + "&consent=Yes&refid=" + refid + "&secode=200"+ "&msisdn="+request.getHeader("x-msisdn");
			System.out.println("redirecturl=" + redirecturl);
			dcurl = url + "?srvkey=" + srvid + "&consent=No&refid=" + refid + "&secode=200";
			errorurl = url + "?srvkey=" + srvid + "&consent=Yes&refid=" + refid + "&secode=400";

			String rdurl = URLEncoder.encode(redirecturl, "UTF-8");
			System.out.println("redirect :" + rdurl);

		}

		if (!error) {

			out.println("\t\t\tHEADERS<br>");
			out.println("------------------------------------------------------<br>");
			Enumeration<String> emnHN = request.getHeaderNames();
			while (emnHN.hasMoreElements()) {
				String headerName = emnHN.nextElement();
				String headerValue = request.getHeader(headerName);
				out.println(headerName + ":" + headerValue + "<br>");
			}
			out.println("\t\t\tREQUEST PARAMS<br>");
			out.println("------------------------------------------------------<br>");
			Enumeration<String> emnPN = request.getParameterNames();
			while (emnPN.hasMoreElements()) {
				String paramName = emnPN.nextElement();

				String paramValue = request.getParameter(paramName);
				out.println(paramName + ":" + paramValue + "<br>");
			}

			String curlDaius = request.getParameter("curl");
			//String rutlDaius = 
		} else {
			if (errorurl != null)
				response.sendRedirect(errorurl);
		}
	} catch (Exception e) {
		System.out.println("##### Error while writing headers...");
		System.out.println("##### Error while writing headers...");
		e.printStackTrace();
	}

	if (!error) {
%>
<head>
<meta name="viewport"
	content="width=200%,initial-scale=1,height= device-height" />
</head>
<body>
	<div>
		<table style="border: 0;">
			<tr>
				<td colspan="2">
					<div
						style="background: #BA2F31; vertical-align: middle; text-align: left; padding-top: 3px; padding-bottom: 2px; margin: 0px;">
						<p>Confirmation page</p>
					</div>
				</td>
			</tr>
			<%
				if (imageURL != null && !imageURL.trim().isEmpty()) {
			%>
			<tr>
				<td align="center" colspan="2"><img alt="img"
					src="<%=imageURL%>"></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td colspan="2">
					<div
						style="background: #FFFFFF; vertical-align: middle; text-align: left; padding-top: 3px; padding-bottom: 2px; margin: 0px;">
						<p>You are going to be charged xx amount of money to subscribe
							to the service. To proceed click on Accept.</p>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div
						style="background: #4CB848; vertical-align: middle; text-align: left; padding-top: 3px; padding-bottom: 2px; margin: 0px;">
						<a
							href="/Idea/ideaCGmiddle?srurl=<%=URLEncoder.encode(redirecturl, "UTF-8")%>&msisdn=<%=request.getHeader("x-msisdn")%>&srvkey=<%=srvid%>&action=yes&refid=<%=refid%>&mode=wap&info=<%=info%>&rurlD=<%=request.getParameter("rurl")%>&originator=<%=originator%>">
							Accept </a>
					</div>
				</td>
				<td style="width: 33%; align: center">
					<div
						style="background: #4CB848; vertical-align: middle; text-align: left; padding-top: 3px; padding-bottom: 2px; margin: 0px;">
						<a href="<%=dcurl%>"> Decline </a>
					</div>
				</td>
			</tr>

		</table>
	</div>
</body>
<%
	} else {
		out.println("Error : " + msg);
	}
%>

