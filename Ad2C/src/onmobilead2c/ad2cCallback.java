package onmobilead2c;



import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ad2Callback
 */

public class ad2cCallback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ad2cCallback() {
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

		
		System.out.println("<!------------Inside Ad2Callback ------------->");
		String method = request.getMethod();
		String tid = request.getParameter("tid");
		String msisdn = request.getParameter("msisdn");
		String atype = request.getParameter("atype");
		String cpp = request.getParameter("cpp");
		String arpu = request.getParameter("arpu");
		String ctype = request.getParameter("ctype");
		
		
		try{
		if (method != "GET")
		{
			System.out.println("Http Method is wrong");
			System.out.println("HTTP Method : " + method + "\n");
			response.sendError(500);
			
		}else if (tid == "null" || msisdn == "null" || atype == "null" || cpp == "null" || arpu == "null" || ctype == "null"){
			
			System.out.println("Mandatory Parameters missing");
			System.out.println("tid : "+ tid + "\n" + "msisdn : " + msisdn + "\n" + "atype : " + atype + "\n");
			System.out.println("cpp : "+ cpp + "\n" + "arpu : " + arpu + "\n" + "ctype : " + ctype + "\n");
			response.sendError(500);
			
		}else {
			
			System.out.println("HTTP Method : " + method + "\n");
			System.out.println("tid : "+ tid + "\n" + "msisdn : " + msisdn + "\n" + "atype : " + atype + "\n");
			System.out.println("cpp : "+ cpp + "\n" + "arpu : " + arpu + "\n" + "ctype : " + ctype + "\n");
			System.out.println("redirecting with SUCCESS response");
			
			response.sendRedirect("/Ad2C/success.jsp");
			//response.setStatus(200);
	
			//Thread.sleep(100);
			//response.getOutputStream().write("Ad2C response callback".getBytes());
			
		}
		}catch (Exception e){
			e.printStackTrace();
		}		
}
	
}

