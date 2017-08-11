<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AIRCEL CG Page</title>
</head>
<body>

<%

System.out.println("Request Method : "+request.getMethod());
System.out.print("--------------inside CGDisplay.jsp---------------");
String redirecturl=request.getParameter("redirecturl");
System.out.println("redirecturl: "+redirecturl);
String msisdn=request.getParameter("msisdn");
System.out.println("msisdn: "+msisdn);
String seid=request.getParameter("seid");
System.out.println("seid: "+seid);
String productId = request.getParameter("productid");
System.out.println("productId :"+ productId);
String pprice=request.getParameter("pprice");
System.out.println("pprice: "+pprice);
String contentid=request.getParameter("contentid");
System.out.println("contentid: "+contentid);
String transid =request.getParameter("transid");
System.out.println("transid: "+transid);
String cpid =request.getParameter("cpid");
System.out.println("cpid: "+cpid);
String imgURL =request.getParameter("imgURL");
System.out.println("imgURL: "+imgURL);

String URL = "/AircelCG/ConsentDB?"+"redirecturl="+redirecturl+"&msisdn="+msisdn+"&seid="+seid+"&productid="+productId+"&contentid="+contentid+"&transid="+transid+"&cpid="+cpid; 
%>

	<h3>
		<center><b><u>AIRCEL Double Confirmation</u></b></center>
	</h3>
	<center>You will be charged Rs <%=pprice%> for this content</center>
	<br/>
	<center>Click "Yes" to Subscribe or "No" to go back</center>
	
	<br />
	<center><img src="<%=imgURL%>"/></center>
	
	<table align="center" border="0" width="30">
		
		<td align="center"><a href="<%=URL%>&result=SUCCESS"><b>YES</b></a>
		</td>
		

		<td align="center"><a href="<%=URL%>&result=FAIL"><b>NO</b></a>
		</td>
		
	</table> 

</body>
</html>