<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.net.URLEncoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CGDisplay Page</title>
</head>
<body>

<%

System.out.print("--------------inside PPU CGDisplay.jsp---------------\n");
String Wapurl=request.getParameter("Wapurl");
System.out.println("Wapurl: "+Wapurl);
String imageURL=request.getParameter("imageURL");
System.out.println("imageURL: "+imageURL);
String DeclineURL=request.getParameter("DeclineURL");
System.out.println("DeclineURL: "+DeclineURL);
String msisdn=request.getParameter("msisdn");
System.out.println("msisdn: "+msisdn);
String mode=request.getParameter("mode");
System.out.println("mode: "+mode);
String eventkey=request.getParameter("eventkey");
System.out.println("eventkey: "+eventkey);
String URL = "/VodaNewConsentPPU/ConsentDB?"+"Wapurl="+URLEncoder.encode(Wapurl,"UTF-8")+"&msisdn="+msisdn+"&mode="+mode+"&eventkey="+eventkey+"&DeclineURL="+DeclineURL; 
%>

	<h3>
		<center><b><u>Vodafone PPU Double Confirmation</u></b></center>
	</h3>
	<center>Click "Yes" to Subscribe or "No" to go back</center>
	
	<br />
	<center><img src="<%=imageURL%>"/></center>
	
	<table align="center" border="0" width="30">
		
		<td align="center"><a href="<%=URL%>&action=yes"><b>YES</b></a>
		</td>
		

		<td align="center"><a href="<%=URL%>&action=no"><b>NO</b></a>
		</td>
		
	</table> 

</body>
</html>