<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="onmobile.Airtel.AirtelAesEncDec"%>
<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String rurl=request.getParameter("rUrl");
System.out.println("rUrl: "+rurl);
String MSISDN=request.getParameter("MSISDN");
String cpx=request.getParameter("cpx");
String backurl=request.getParameter("opt3");
System.out.println("opt3 : " + backurl);
String imgurl=request.getParameter("imgurl");
System.out.println("imgurl before decoding : " + imgurl);
imgurl=URLDecoder.decode(imgurl,"UTF-8");
System.out.println("imgurl after decoding : " + imgurl);
String curl=request.getParameter("curl");
System.out.println("curl: "+curl);



String[] status= {"success","failure","aoc_decline"};
String[] messages={"Insufficient balance #`#-9.0", "Error happed whill charging or invalid request","User declined" };
String[] display={"Yes","Yes but with low balance ","Yes but Error" , "No"};
String URL[] =new String[6];

URL[0]=URLDecoder.decode(rurl, "UTF-8")+"?s="+URLEncoder.encode(status[0],"UTF-8")+"&x="+URLEncoder.encode(cpx,"UTF-8")+"&rc=&cpx="+URLEncoder.encode(cpx,"UTF-8")+"&rm="+URLEncoder.encode("SUCCESS","UTF-8");
URL[1]=URLDecoder.decode(rurl, "UTF-8")+"?s="+URLEncoder.encode(status[1],"UTF-8")+"&x="+URLEncoder.encode(cpx,"UTF-8")+"&rc=&cpx="+URLEncoder.encode(cpx,"UTF-8")+"&rm="+URLEncoder.encode(messages[0],"UTF-8");
URL[2]=URLDecoder.decode(rurl, "UTF-8")+"?s="+URLEncoder.encode(status[1],"UTF-8")+"&x="+URLEncoder.encode(cpx,"UTF-8")+"&rc=111&cpx="+URLEncoder.encode(cpx,"UTF-8")+"&rm="+URLEncoder.encode(messages[1],"UTF-8");
URL[3]=URLDecoder.decode(rurl, "UTF-8")+"?s="+URLEncoder.encode(status[2],"UTF-8")+"&x="+URLEncoder.encode(cpx,"UTF-8")+"&rc=&cpx="+URLEncoder.encode(cpx,"UTF-8")+"&rm="+URLEncoder.encode(messages[2],"UTF-8");
URL[4]=URLDecoder.decode(backurl, "UTF-8")+"?s="+URLEncoder.encode(status[2],"UTF-8")+"&x="+URLEncoder.encode(cpx,"UTF-8")+"&rc=&cpx="+URLEncoder.encode(cpx,"UTF-8")+"&rm="+URLEncoder.encode(messages[2],"UTF-8");

URL[5]=URLDecoder.decode(rurl, "UTF-8")+"?s="+URLEncoder.encode(status[1],"UTF-8")+"&x="+URLEncoder.encode(cpx,"UTF-8")+"&rc=TWSS_179&cpx="+URLEncoder.encode(cpx,"UTF-8")+"&rm="+URLEncoder.encode(messages[1],"UTF-8");
//for(int i=0;i<4;i++)
	//URL[i]="http://www.google.com";
%>
<h3>
	<b>
		<u>
			<center>IBM Event Charging Double Confirmation Dummy Site</center>
			
		</u>
	</b>
</h3>

<center>Please select below option for yes/no to allow event charging
</center><br/>
<center><img src="<%=imgurl%>"/></center>
<table align="center" border="1" width="60" colspan=2>
	<tr>
		<td>
					<table align="center" border="1" width="60" colspan=2>
					
					
				<tr>
				<td align="center">
						<a href=<%=URL[0]%>
						>Yes
						</a><br><br>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href=<%=URL[1]%>
						>Yes but with low balance
						</a><br>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href=<%=URL[2]%>
						>Yes but Error while Charging
						</a><br>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href=<%=URL[5]%>
						>Yes but with corporate number
						</a><br>
					</td>
				</tr>
					
			</table> 
			</td>
		<td align="center"><!-- <input type="button" name="Accept" value="Decline" onclick="location.href='index.html'"/> -->
			<a href=<%=URL[3]%>> No
			</a>
		</td>
		<td align="center"><!-- <input type="button" name="Accept" value="Decline" onclick="location.href='index.html'"/> -->
			<a href=<%=URL[4]%>> Back
			</a>
		</td>
	</tr>
</table>

</body>
</html>
