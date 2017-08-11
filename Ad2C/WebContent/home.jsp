<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.UUID"  %>
 <%@ page import="java.net.URLEncoder" %>
 <%@page import="com.drg.properties.Utility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AD2C Page</title>
</head>
<body>

		
	
	<table align="center" border="1" width="300">
	<tr>
	<td align="center">
	<h3><u>Welcome to Ad2C Page</u>
	</h3>
	</td>
	</tr>
	<tr>
	<td align="center">
	Click on the below url to subscribe to a Subscription pack via DM Flow
	</td>
	</tr>
		<tr><td align="center">
		<%
		System.out.println("<!-----------Ad2C Page-------------->");
		UUID ad2cid = UUID.randomUUID();
		//String usergaent= request.getHeader("HTTP_USER_AGENT");
		//String msisdn = request.getHeader("X-MSISDN");
		//String 
		System.out.println("ad2cid : " + ad2cid);
		Utility util = new Utility();
		util.load();
		String AD2C_CALLBACKURL = (String)util.get("AD2C_CALLBACKURL");
		String AD2C_CLICKURL = (String)util.get("AD2C_CLICKURL");
		String AD2C_URL = (String)util.get("AD2C_URL");
		
		String ad2cCallback = AD2C_CALLBACKURL+ad2cid;
		String ad2cClick = AD2C_CLICKURL+ad2cid;
		String url = AD2C_URL+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8");
		System.out.println("URL formed : " + url);
		
        //Subscription short url
        String urlqa = "http://172.16.5.199:8035/o/12TtTsZzT?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf&OMCID=10&operator=spice&refid=4546789080";
        String urlde = "http://172.16.5.199:8035/o/1BpscptId?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf&OMCID=10&operator=spice";

        //String urlde = "http://172.16.5.199:8035/o/1AkUBaysR?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf&OMCID=10&refid=21311113452454";

        //String urlde = "http://172.16.5.199:8035/o/1PkM7jPeO?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf&OMCID=10&refid=213114";

        String urlqaqa = "http://172.16.5.199:8035/o/1jlG00ltR?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf&OMCID=10&operator=spice&refid=31311164";

        String urlviva =
        "http://172.16.5.199:8035/o/1Ie6rtfiF?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf&OMCID=10&operator=spice&refid=31311164";

        //String urlde = "http://172.16.5.199:8035/o/1yF4UgNIG?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf&OMCID=10&operator=spice";

		//String url = "http://27.251.240.7/o/15DS9K6A1?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf";
        //Rich Alert Subscription short url
        //String url = "http://172.16.5.199:8035/o/1dNhCiauA?cid=dummy&ad2callback="+URLEncoder.encode(ad2cCallback,"UTF-8")+"&ad2click="+URLEncoder.encode(ad2cClick,"UTF-8")+"&a=deblina&b=hfyuf";

		
		
		        System.out.println("URL formed : " + urlqa);
                System.out.println("URL formed : " + urlde);
                System.out.println("URL formed : " + urlqaqa);
                System.out.println("URL formed : " + urlviva);
        %>
                Click <a href="<%=urlqa%>">here</a> to enjoy music :: qa-mneeds_113<br/>
                Click <a href="<%=urlde%>">here</a> to enjoy music :: qa-deepak_110<br/>
                Click <a href="<%=urlqaqa%>">here</a> to enjoy music :: qa-mneeds_110<br/>
                Click <a href="<%=urlviva%>">here</a> to enjoy music :: qa-bahrain_110

		</td></tr>
	</table>
	
	
</body>
</html>