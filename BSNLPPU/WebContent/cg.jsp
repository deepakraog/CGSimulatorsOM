<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.util.Enumeration"%>
<%-- <%@page import="org.apache.commons.httpclient.HttpStatus"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!
    String msg="";
public boolean nullLength(String key , String str) {
    	System.out.println(key + ": "+str);
		if(str==null||str.trim().equals("")||str.equalsIgnoreCase("null"))
		{
			msg =msg + key+" is null/empty, ";
			System.out.println("msg: "+msg);
			
			return true;
		}
		else
			return false;
	}
%>
<% 
BufferedWriter bw = null;
String redirecturl=null;
String transactionid= null;
boolean error = false;

		try {
			String msisdn=request.getParameter("msisdn");
			String serviceid=request.getParameter("serviceid");
			String keyword=request.getParameter("keyword");
			String shortcode=request.getParameter("shortcode");
			String authkey= request.getParameter("authkey");
			String contentid=request.getParameter("contentid");
			String reqtype=request.getParameter("reqtype");
			String cpid=request.getParameter("cpid");
			String mode=request.getParameter("mode");
			String lang=request.getParameter("lang");
			String responsetype=request.getParameter("responsetype");
			String seid=request.getParameter("seid");
			transactionid=request.getParameter("transactionid");
			String requesttimestamp=request.getParameter("requesttimestamp");
			redirecturl=request.getParameter("redirecturl");
			String circleid=request.getParameter("circleid");
			String param1=request.getParameter("param1");
			String param2=request.getParameter("param2");
			String param3=request.getParameter("param3");
			error = false;
			msg="";
			if(nullLength("msisdn",msisdn) |nullLength("serviceid",serviceid) |  nullLength("keyword",keyword) |nullLength("shortcode",shortcode) |
					nullLength("authkey",authkey) |nullLength("contentid",contentid) | nullLength("reqtype",reqtype) | nullLength("cpid",cpid) 
					| nullLength("mode",mode) | nullLength("lang", lang)  | nullLength("responsetype",responsetype) | nullLength("seid",seid) 
					| nullLength("transactionid",transactionid) | (redirecturl == null || redirecturl.isEmpty()))
			{
				error = true;
				out.println("Error : "+msg);
			}
			if(msisdn.length() > 10 | serviceid.length() > 30 |  keyword.length() > 50 | shortcode.length() > 20 |
					authkey.length() > 20 |  contentid.length() > 50 | reqtype.length() > 7 | cpid.length() > 3 |
					 mode.length() > 10 | lang.length() > 10 | responsetype.length() > 5 | seid.length() > 2 |
					 transactionid.length() > 50 | requesttimestamp.length() > 12 | redirecturl.length() > 200 | circleid.length() > 2)
			{
				error = true;
				out.println(" Length is greater : \n ");
				out.println("msisdn :" + msisdn.length() +"\n serviceid : " + serviceid.length() +"\n keyword :" + keyword.length() + "\n shortcode : "+ shortcode.length() +
					"\n authkey : "	+ authkey.length() + "\n contentid : " + contentid.length() + "\n reqtype : " + reqtype.length() + "\n cpid : " + cpid.length() +
						 "\n mode : " + mode.length() + " \n lang : " + lang.length() + "\n responsetype : " + responsetype.length() +"\n seid : " + seid.length() +
						 "\n transactionid : " + transactionid.length() + "\n requesttimestamp : "+ requesttimestamp.length() +" \n redirecturl : " + redirecturl.length() + "\n circleid :" + circleid.length());
			}
			if(!error)
			{
				out.println("\t\t\tHEADERS<br>");
				out.println("-------------------------------------------------<br>");
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
		}
		 catch (Exception e) {
				System.out.println("##### Error while writing headers...");
				e.printStackTrace();
				}
		 finally {
				if (bw != null)
				{
					bw.flush();
					bw.close();
				}
			}	
			
			
		if(!error)
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
						<a href="/BSNLPPU/Consentbsnl?srurl=<%=request.getParameter("redirecturl")%>&msisdn=<%=request.getParameter("msisdn")%>&srvkey=<%=request.getParameter("serviceid")%>&action=yes&mode=<%=request.getParameter("mode")%>&refid=<%=request.getParameter("transactionid") %>"> Accept </a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
<% } %>

