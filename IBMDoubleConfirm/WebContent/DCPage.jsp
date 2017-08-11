<%@page import="com.onmobile.Airtel.AirtelAesEncDec"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.drg.properties.Utility"%>
<%
String rurl=request.getParameter("rUrl");
System.out.println("rUrl: "+rurl);
String MSISDN=request.getParameter("MSISDN");
String pname=request.getParameter("pName");
String pId=request.getParameter("productID");
String amount=request.getParameter("SpPrice");
String opt4=request.getParameter("opt4");
System.out.println("opt4: "+opt4);
String am=request.getParameter("am");
System.out.println("am: "+am);
String ar=request.getParameter("ar");
System.out.println("ar: "+ar);
String ac=request.getParameter("ac");
System.out.println("ac: "+ac);
String rtg=request.getParameter("rtg");
System.out.println("rtg: "+rtg);
String adt=request.getParameter("adt");
System.out.println("adt: "+adt);
String chn=request.getParameter("chn");
System.out.println("chn: "+chn);
String opt1=request.getParameter("opt1");
System.out.println("opt1: "+opt1);

//String URL=rul;
//AirtelAesEncDec air=new AirtelAesEncDec();
String[] code1= {"101","102","103","104","106","1000","2000","3000","3001","3002","3003","3004","3005","3006","3010","3011","3012","3015","3104","3114","3116","3119","3119","3404","3500","4000","4001","5010","5020","5021"};
String[] ppid={"500649","5004994","5004995","5004996","5004997","5004999","5004998"};
String[] ppid_sen={"Normal charging","Fallback to Rs 20 from 30 ","Fallback to Rs 10 from 30 " , "Fallback to Rs 40 from 60 ","Fallback to Rs 50 from 60 ","Rs 30 ","RS 60 "};

//String enc_code1 [] =new String[code1.length];
//String URL[] =new String[code1.length];
String enc_code1 ;
String URL[] =new String[ppid.length];
String URLLOW  ;
String URLERROR  ;
String URLCORPORATE ;
int inc=0;
//String code1="106";
String code2="105";
String lowbalcode="3404";
String res ="Invalid Request";
AirtelAesEncDec air=new AirtelAesEncDec();

Utility util = new Utility();
util.load();
String ENC = (String)util.get("ENCRYPTKEY");
String m=air.encrypt(MSISDN,ENC);
System.out.println("msisdn encrypt: "+m);

/*for(inc=0; inc < code1.length ;inc++)
{	
enc_code1[inc]= air.encrypt(code1[inc], "SDPibmBHARTIPKey");
System.out.println("code for Acccept Success: "+enc_code1[inc]); 
System.out.println("code for Acccept Success decrypt: "+air.decrypt(enc_code1[inc], "SDPibmBHARTIPKey")); 
if(rurl.contains("?")){
URL[inc]=rurl+"&MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode(enc_code1[inc], "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&temp1=1&temp2=1&temp3=1";
}else{
URL[inc]=rurl+"?MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode(enc_code1[inc], "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&temp1=1&temp2=1&temp3=1";
}
System.out.println("DCPage.jsp: URL: "+URL[inc]);
}
*/
for(inc=0; inc < ppid.length ;inc++)
{
enc_code1= air.encrypt("1000", ENC);
System.out.println("code for Acccept Success: "+enc_code1); 
System.out.println("code for Acccept Success decrypt: "+air.decrypt(enc_code1, ENC)); 
if(rurl.contains("?")){
URL[inc]=rurl+"&MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode(enc_code1, "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+ppid[inc]+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1&";
}else{
URL[inc]=rurl+"?MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode(enc_code1, "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+ppid[inc]+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1&";
}
}

if(rurl.contains("?")){
URLLOW =rurl+"&MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode((air.encrypt("3404", ENC)), "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
URLERROR =rurl+"&MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode((air.encrypt("1001", ENC)), "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
URLCORPORATE =rurl+"&MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode((air.encrypt("0", ENC)), "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
}else{
URLLOW =rurl+"?MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode((air.encrypt("3404", ENC)), "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
URLERROR =rurl+"?MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode((air.encrypt("1001", ENC)), "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
URLCORPORATE =rurl+"?MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+URLEncoder.encode((air.encrypt("0", ENC)), "UTF-8")+"&msg="+URLEncoder.encode(res, "UTF-8")+"&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
}


String code3= air.encrypt(code2, ENC);
System.out.println("code for decline: "+code3); 

String URL1 = "";

if(rurl.contains("?")){
URL1=rurl+"&MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+code3+"&msg=success&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
}else{
URL1=rurl+"?MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+code3+"&msg=success&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&chn="+chn+"&temp1=1&temp2=1&temp3=1";
}

String BACK_URL=URLEncoder.encode(opt4,"UTF-8");


/*if(BACK_URL == null || BACK_URL.trim().length() < 1)
	BACK_URL = "http://www.google.com";
if(BACK_URL.indexOf("?") < 0)
	BACK_URL = BACK_URL + "?";
else
	BACK_URL = BACK_URL + "&";
BACK_URL = BACK_URL +"MSISDN="+URLEncoder.encode(m, "UTF-8")+"&pname="+pname+"&method=a&code="+code3+"&msg=success&time=1&productId="+pId+"&lowBalance=1&amount="+amount+"&temp1=1&temp2=1&temp3=1";
*/
System.out.println("final backUrl = "+ BACK_URL);
%>
<h3>
	<b>
		<u>
			<center>IBM Double Confirmation Dummy Site</center>
		</u>
	</b>
</h3>

<center>Please select below option for confirming/declining MusicAdda subscription
</center><br>
<br />
	<center><img src="<%=opt1%>"/></center>
<table border="1" align="center" border="0" width="30" colspan="2">
	<tr>
		<td>
					<table border="1" align="center">
					<%for(inc=0;inc<1;inc++)
						//for(inc=0;inc<URL.length;inc++)
					{
					%>
				<tr>
					<td align="center">
						<a href=<%=URL[inc]%>>Accept_with_<%=ppid_sen[inc]%>
						</a><br>
					</td>
				</tr>
					<%}
					%>
					<tr>
					<td align="center">
						<a href=<%=URLLOW%>>Low Balance 
						</a><br>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href=<%=URLERROR%>>ERROR IN Charging 
						</a><br>
					</td>
				</tr>
				<tr>
					<td align="center">
						<a href=<%=URLCORPORATE%>>Corporate Number 
						</a><br>
					</td>
				</tr>
					
			</table> 
			<!--<a href=<%=URL[5]%>> Accept -->
                                                </a><br>
		</td>
		<td align="center"><!-- <input type="button" name="Accept" value="Decline" onclick="location.href='index.html'"/> -->
			<a href="<%=URL1%>"> Decline 
			</a>
		</td>
	<td align="center">
		<a href="<%=BACK_URL%>">BACK</a></td>		
	</tr>
</table>
