<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.xml.bind.DatatypeConverter"%>
<%@page import="com.drg.properties.Utility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Properties"%>
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy_mm_dd_hh_MM_ss");
BufferedReader br = null;
Map<String , String> paramMap = null;
String rurl = null;
String msisdn = null ,  service_id = null , mode = null,transactionid = null;
boolean isError = false;
	try {
		Utility util = new Utility();
		util.load();
		String PATH = (String)util.get("BSNL_SESSION_BASE_DIR");
		String sessionId = request.getParameter("session_id");
		String logFileName = PATH+"SESSION_"+sessionId+".properties";
		File inputFile = new File(logFileName);
		if(inputFile.exists() && inputFile.isFile())
		 {
			System.out.println("------------------Param list from Prism request---------------------");
			Properties sessionTxt;
			sessionTxt = new Properties();
			sessionTxt.load(new FileInputStream(new File(logFileName)));
			msisdn = sessionTxt.getProperty("msisdn");
			service_id = sessionTxt.getProperty("service_id");
			mode = sessionTxt.getProperty("consent_source");
			transactionid = sessionTxt.getProperty("tid");
			
			System.out.println("msisdn --->"+ msisdn);
			System.out.println("service_id --->"+service_id);
			System.out.println("mode --->"+mode);
			System.out.println("------------------------------------------------------------------");
			
			paramMap = new HashMap<String , String>();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(logFileName)));
			String thisLine = null;
			String[] keyValue = null;
			out.println("\t\t\tSESSION PARAMS<br>");
			out.println("------------------------------------------------------<br>");
			
			while ((thisLine = br.readLine()) != null) {
				System.out.println("thisLine : " +thisLine);
				keyValue = thisLine.split("=");
				if (keyValue.length == 2) {
					paramMap.put(keyValue[0],keyValue[1]);
					out.println(keyValue[0] + ":" + keyValue[1] + "<br>");
					if(keyValue[0].equalsIgnoreCase("url")){
						rurl = keyValue[1];
						rurl = new String(DatatypeConverter.parseBase64Binary(rurl));
						System.out.println("RURL --->"+rurl);
					}
				}
				}
			}
		else
		{
			out.println("Sesion Id is invalid ... Check if session Property path is created !!<br>");
			isError= true;
		}
		if(!isError)
		{
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
		}
	} catch (Exception e) {
			System.out.println("##### Error while writing headers...");
			out.println("##### Error while writing headers...");
			e.printStackTrace();
	}
	finally {
		if (br != null)
			br.close();
	}
	if(!isError)
	{
%>
<head>
<meta name="viewport"
	content="width=200%,initial-scale=1,height= device-height" />
</head>
<body>
	<div>
		<table style="border: 0;">
			<tr>
				<td>
					<div
						style="background: #BA2F31; vertical-align: middle; text-align: left; padding-top: 3px; padding-bottom: 2px; margin: 0px;">
						<p>Confirmation page</p>
					</div>
				</td>
			</tr>
			<tr>
				<td>
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
							href="/BSNL/Consentbsnl?msisdn=<%=msisdn%>&srvkey=<%=service_id%>&action=yes&mode=<%=mode%>&srurl=<%=rurl%>&refid=<%=transactionid %>>">
							Accept </a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
<% } %>