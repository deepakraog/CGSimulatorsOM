<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.UUID"  %>
 <%@ page import="java.net.URLEncoder" %>
 <%@page import="com.drg.properties.Utility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tyroo Page</title>
</head>
<body>

		
	
	<table align="center" border="1" width="300">
	<tr>
	<td align="center">
	<h3><u>Welcome to Tyroo/DC/DM Page</u>
	</h3>
	</td>
	</tr>
	<tr>
	<td align="center">
	Click on the below url to subscribe 
	</td>
	</tr>
		<tr><td align="center">
		<%
		System.out.println("<!-----------DM Tyroo Page-------------->");
		UUID dmid = UUID.randomUUID();

		System.out.println("dmid : " + dmid);
		Utility util = new Utility();
		util.load();
		String TYROO_CURL = (String)util.get("TYROO_CURL");
		String url = TYROO_CURL+dmid;
		System.out.println("URL formed : " + url);
		%>
		Click <a href="<%=url%>">here</a> to subscribe
		</td></tr>
	</table>
	
	
</body>
</html>