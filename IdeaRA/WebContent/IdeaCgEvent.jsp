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
<%-- <%@page import="org.apache.commons.httpclient.HttpStatus"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!String msg = "";

	public boolean nullLength(String key, String str, boolean manadatory,
			int length) {
		System.out.println(key + ": " + str);
		if (manadatory
				&& (str == null || str.trim().equals("") || str
						.equalsIgnoreCase("null"))) {
			msg = msg + key + " is null/empty, ";
			System.out.println("msg: " + msg);

			return true;
		} else if (manadatory) {
			if (str.length() > length) {
				msg = msg + key + " length is greater than " + length + ", ";
				System.out.println("msg: " + msg);
				return true;
			} else {
				return false;
			}

		} else if (!manadatory) {
			if (str == null || str.trim().equals("")
					|| str.equalsIgnoreCase("null")) {
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
	//Map map = new HashMap();
	String redirecturl = null;
	String dcurl = null;
	String errorurl = null;
	boolean error = false;
	String contenturl = null;
	String cpid = null;
	IdeaAesEncDec idea = new IdeaAesEncDec();
	Utility util = new Utility();
	util.load();
	String url = (String)util.get("IDEA_PPU_REDIRECT");
	
	//String a1 = request.getParameter("A");
	//String srvid1 = idea.decryption(a1);
	//out.println("service key : " + srvid1 );
	
	try {
		System.out.println("================Request Params====================");
		out.println("-------------------Request Params---------------------");
		cpid = request.getParameter("A");
		System.out.println("A >> CPID ::"+ cpid);
		out.println("A >> CPID ::"+ cpid);
		// String srvid = idea.decryption(a);
		
		String contenttype = request.getParameter("B");
		System.out.println("B >> contenttype ::"+ contenttype);
		//out.println("B >> contenttype ::"+ contenttype);
		
		String contentid = request.getParameter("C");
		System.out.println("C >> contentid ::"+ contentid);
		//out.println("C >> contentid ::"+ contentid);
		
		String contentdesc = request.getParameter("D");
		System.out.println("D >> contentdesc ::"+ contentdesc);
		//out.println("D >> contentdesc ::"+ contentdesc);
		
		String e = request.getParameter("E");
		String contentprice = idea.decryption(e);
		System.out.println("E >> "+ e +" contentprice ::"+ contentprice);
		//out.println("E >> "+ e +" contentprice ::"+ contentprice);
		
		contenturl = request.getParameter("F");
		System.out.println("F >> contenturl ::"+ contenturl);
		//out.println("F >> contenturl ::"+ contenturl);
		
		String cptransid = request.getParameter("G");
		System.out.println("G >> cptransid ::"+ cptransid);
		//out.println("G >> cptransid ::"+ cptransid);
		
		String producttype = request.getParameter("H");
		System.out.println("H >> producttype ::"+ producttype);
		//out.println("H >> producttype ::"+ producttype);
		
		String aggregatorid = request.getParameter("I");
		System.out.println("I >> aggregatorid ::"+ aggregatorid);
		//out.println("I >> aggregatorid ::"+ aggregatorid);
		
		String ChargeType = request.getParameter("J");
		System.out.println("J >> ChargeType ::"+ ChargeType);
		//out.println("J >> ChargeType ::"+ ChargeType);
		
		String userName = request.getParameter("L");
		System.out.println("L >> userName ::"+ userName);
		//out.println("L >> userName ::"+ userName);
		
		String m = request.getParameter("M");
		String Password = idea.decryption(m);
		System.out.println("M >>"+ m +" Password ::"+ Password);
		//out.println("M >> "+ m +" Password ::"+ Password);
		//String cpid = null ;

		error = false;
		msg = "";
		if (nullLength("cpid", cpid, true, 2)
				| nullLength("contenttype", contenttype, true, 2)
				| nullLength("contentid", contentid, true, 7)
				| nullLength("contentdesc", contentdesc, false, 25)
				| nullLength("contentprice", contentprice, true, 100)//changed original is 6
				| nullLength("cptransid", cptransid, true, 21)
				| nullLength("producttype", producttype, true, 4)
				| nullLength("ChargeType", ChargeType, true, 1)
				| nullLength("aggregatorid", aggregatorid, false, 1)
				| nullLength("contenturl", contenturl, false, 50)
				| nullLength("userName", userName, true, 35)
				| nullLength("Password", Password, true, 35)) {
			error = true;
			System.out.println("Error : " + msg);
		}
		System.out.print("url : "+url);
		if (url == null || url.trim().isEmpty()
				|| !url.startsWith("http://")) {
			msg = msg
					+ " redirection url is not configured correctly";
			error = true;
			System.out.println("msg: " + msg);
		}
		else
		{
			double num= 10000000000.0 * Math.random();
			String sdptxnid = Long.valueOf(Math.round(num)).toString();
			String status = cptransid +","+sdptxnid+",200,"+contentprice;
			String encryptedStatus = (idea.encryption(status)).trim();
			System.out.println("Encrypted STATUS : "+encryptedStatus);
			redirecturl = url+"?status="+URLEncoder.encode(encryptedStatus,"UTF-8");
			
			//redirecturl = url+"?status="+idea.encryption(cptransid)+","+idea.encryption(sdptxnid)+","+idea.encryption("200")+","+idea.encryption(contentprice);
			
			dcurl = url+"?cptransid="+cptransid+"&consent=NO";
			errorurl = url+"?status="+cptransid+",,400,";
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
		}
		else
     	{
			if(errorurl != null)
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
			<center><img src="<%=contenturl%>"/></center>
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
						<a href="<%=redirecturl %>"> Accept </a>
					</div>
				</td>
				<td style ="width:33%; align:center">
						<div style="background:#4CB848; vertical-align:middle; text-align:left;padding-top:3px;padding-bottom:2px;margin:0px;">
							<a href="<%=dcurl%>">
								Decline
							</a>
						</div>				
					</td>
				
			</tr>
			<%
			if(contenturl != null && !contenturl.trim().isEmpty())
			{
				
			%>
			<tr>
				<td align="center"  colspan="2">
					<img alt="img" src="<%=contenturl%>">
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
<%
	}
	else
	{
		out.println("Error : " + msg);
	}
%>

