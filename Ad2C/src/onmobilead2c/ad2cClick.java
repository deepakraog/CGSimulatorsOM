package onmobilead2c;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ad2cClick
 */
public class ad2cClick extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ad2cClick() {
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

	System.out.println("<!------------Inside Ad2Click ------------->");
	String method = request.getMethod();
	String tid = request.getParameter("tid");
	String msisdn = request.getParameter("msisdn");
	String HTTP_USER_AGENT = request.getParameter("HTTP_USER_AGENT");
	String HTTP_REFERER = request.getParameter("HTTP_REFERER");
	String REMOTE_ADDR = request.getParameter("REMOTE_ADDR");
	//String ctype = request.getParameter("ctype");
	
	
	try{
	if (method != "GET")
	{
		System.out.println("Http Method is wrong");
		System.out.println("HTTP Method : " + method + "\n");
		response.sendError(500);
		
	}else if (tid == "null" || msisdn == "null" || HTTP_USER_AGENT == "null" || HTTP_REFERER == "null" || REMOTE_ADDR == "null" ){
		
		System.out.println("Mandatory Parameters missing");
		System.out.println("tid : "+ tid + "\n" + "msisdn : " + msisdn + "\n" + "HTTP_USER_AGENT : " + HTTP_USER_AGENT + "\n");
		System.out.println("HTTP_REFERER : "+ HTTP_REFERER + "\n" + "REMOTE_ADDR : " + REMOTE_ADDR + "\n" );
		response.sendError(500);
		
	}else {
		
		System.out.println("HTTP Method : " + method + "\n");
		System.out.println("tid : "+ tid + "\n" + "msisdn : " + msisdn + "\n" + "HTTP_USER_AGENT : " + HTTP_USER_AGENT + "\n");
		System.out.println("HTTP_REFERER : "+ HTTP_REFERER + "\n" + "REMOTE_ADDR : " + REMOTE_ADDR + "\n" );
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