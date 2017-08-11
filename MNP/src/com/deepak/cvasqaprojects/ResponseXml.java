package com.deepak.cvasqaprojects;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class ResponseXml {

	
	public static String response (String State, String Status, String Msisdn,String Operator, HttpServletResponse response){
		
		//HttpServletResponse res = null;
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><CustomerCircleInfo><MSISDN>" +  Msisdn + "</MSISDN><Status>"+ Status +"</Status><Customer>"+Operator+"</Customer><Circle>" + State + "</Circle><LRN>1234</LRN></CustomerCircleInfo>";
		response.setContentType("text/xml");
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		pw.print("<CustomerCircleInfo>");
		pw.print("<MSISDN>"+Msisdn+"</MSISDN>");
		pw.print("<Status>" + Status + "</Status>");
		pw.print("<Customer>"+Operator+"</Customer>");
		pw.print("<Circle>" + State + "</Circle>");
		pw.print("<LRN>1234</LRN>");
		pw.print("</CustomerCircleInfo>");
	
		System.out.println("XML :" +str);
		return str;
	}
	
}
