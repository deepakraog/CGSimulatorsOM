package onmobileDM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ad2Callback
 */

public class dmCallback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dmCallback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


public void doProcess(HttpServletRequest request,HttpServletResponse response) throws Exception {

		
		System.out.println("<!------------Inside DMCallback ------------->");
		String method = request.getMethod();
		String t = request.getParameter("t");
		String DC = request.getParameter("DC");
		String vserv = request.getParameter("vserv");
	
		
		try{
		if (method != "GET")
		{
			System.out.println("Http Method is wrong");
			System.out.println("HTTP Method : " + method + "\n");
			response.sendError(500);
			
		}else if (t != "null" || DC != "null" || vserv != "null"){
			
			System.out.println("------parameter-----");
			System.out.println("HTTP Method : " + method + "\n");
			System.out.println("Tyroo : t : "+ t + "\n" + "Digicolada : DC : " + DC + "\n" + "Vserv : vserv : " + vserv + "\n");


			System.out.println("redirecting with SUCCESS response");
			
			response.sendRedirect("/Tyroo/success.jsp");
			
		}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
		
		
}
	
	
}

