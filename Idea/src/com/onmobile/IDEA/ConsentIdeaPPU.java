package com.onmobile.IDEA;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsentIdeaPPU
 */
public class ConsentIdeaPPU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsentIdeaPPU() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void doProcess(HttpServletRequest request,HttpServletResponse response) throws Throwable{
		
		System.out.println("--------------inside ConsentIdeaPPU---------------");
		
		String rurl = URLEncoder.encode(request.getParameter("srurl"),"UTF-8");
		
		System.out.println("Success Consent URL :" + rurl);
		response.sendRedirect(rurl);
		
	}
	
	
}
