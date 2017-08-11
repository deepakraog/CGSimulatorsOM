<%@page import="com.sun.org.apache.xml.internal.security.utils.Base64"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.util.Enumeration"%>
<%@page import="javax.xml.bind.DatatypeConverter"%>
<%@page import="com.drg.properties.Utility"%>
<%-- <%@page import="org.apache.commons.httpclient.HttpStatus"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%!
public boolean nullLength(String key ,String value) {
    	System.out.println(key + "-->"+value);
		if(value==null||value.trim().equals("")||value.equalsIgnoreCase("null"))
			return true;
		else
			return false;
    }
%>


<% 
BufferedWriter bw = null;
		try {
			System.out.println("\t\t\tHEADERS<br>");
			System.out.println("------------------------------------------------------<br>");
		Enumeration<String> emnHN = request.getHeaderNames();
		while (emnHN.hasMoreElements()) {
			String headerName = emnHN.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName + ":" + headerValue );
		}
		System.out.println("\t\t\tREQUEST PARAMS<br>");
		System.out.println("------------------------------------------------------<br>");
		Enumeration<String> emnPN = request.getParameterNames();
		while (emnPN.hasMoreElements()) {
			String paramName = emnPN.nextElement();
			String paramValue = request.getParameter(paramName);
			System.out.println(paramName + ":" + paramValue );
		}
		
		
		String cp_id= request.getParameter("cp_id");
		String tid=request.getParameter("tid");
		String mode=request.getParameter("source");
		String consent_source=request.getParameter("consent_source");
		String language=request.getParameter("language");
		String action=request.getParameter("action");
		String subscription_id=request.getParameter("subscription_id");
		String msisdn=request.getParameter("msisdn");
		String service_id=request.getParameter("service_id");
		String charge=request.getParameter("charge");
		String content_id=request.getParameter("content_id");
		String content_name=request.getParameter("content_name");
		String validity=request.getParameter("validity");
		String validity_type=request.getParameter("validity_type");
		Utility util = new Utility();
		util.load();
		String url=request.getParameter("url");
		System.out.println("Url recieved is :" + url);
		byte[] decodedUrlB = Base64.decode(url);
		url = new String(decodedUrlB,"UTF-8");
		System.out.println("Base 64 decoded url is : " + url);
		boolean error = false;
		if(nullLength("cp_id" ,cp_id) | nullLength("mode", mode) | nullLength("consent_source",consent_source) 
				| nullLength("language",language) | nullLength( "action" , action) | nullLength("subscription_id",subscription_id) 
				| nullLength("msisdn" , msisdn) |nullLength("service_id" , service_id) | nullLength("charge" , charge)
				| nullLength("" , content_id) |nullLength("content_name" , content_name) | nullLength("validity" , validity) | nullLength("validity_type" , validity_type))
		{
			error = true;
		}
		
		if(error)
		{
			out.print(tid +":1:ERROR");
		}
		else
		{
			if(nullLength("tid" , tid))
			{
				out.print("TID_NULL:1:ERROR");
			}else if(tid.length() > 25 || msisdn.length() > 10 )
			{
				out.print(" Length is greater : \n tid :" + tid.length() +"\n msisdn :" + msisdn.length());
			}
			else
			{
				StringBuffer sb = null;
				double num= 10000000000.0 * Math.random();
				String sessionId = Long.valueOf(Math.round(num)).toString();
				System.out.println("sessionId:" + sessionId);
				String PATH = (String)util.get("BSNL_SESSION_BASE_DIR");
				String logFileName = PATH+"SESSION_"+sessionId+".properties";
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFileName)));
				Enumeration<String> emnPara = request.getParameterNames();
				while (emnPara.hasMoreElements()) {
					String paramName = emnPara.nextElement();
					String paramValue = request.getParameter(paramName);
					sb = new StringBuffer(paramName);
					sb.append("=").append(paramValue).append("\n");
					bw.write(sb.toString());
					bw.flush();
				}
				out.print(tid +":0:"+sessionId);
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
		
%>

