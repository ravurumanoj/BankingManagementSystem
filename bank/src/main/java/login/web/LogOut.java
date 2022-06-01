package login.web;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LogOut
 */
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("text/html");
//    	Cookie loginCookie = null;
//    	Cookie p = null;
//    	Cookie[] cookies = request.getCookies();
//    	if(cookies != null){
//    		for(Cookie cookie : cookies){
//    			if(cookie.getName().equals("username")){
//    				loginCookie = cookie;
//    				break;
//    			}
//    			if(cookie.getName().equals("password")){
//    				p = cookie;
//    				break;
//    			}
//    		}
//    	}
//    	if(loginCookie != null || p != null){
//    		loginCookie.setMaxAge(0);
//        	response.addCookie(loginCookie);
//    	}
//    	response.sendRedirect("LoginPage.jsp");
    	
    	
    	response.setContentType("text/html");
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("JSESSIONID")){
    			System.out.println("JSESSIONID="+cookie.getValue());
    			break;
    		}
    		cookie.setMaxAge(0);
    		response.addCookie(cookie);
    	}
    	}
    	//invalidate the session if exists
    	HttpSession session = request.getSession(false);
    	System.out.println("User="+session.getAttribute("username"));
    	if(session != null){
    		session.invalidate();
    	}
    	response.sendRedirect("LoginPage.jsp");
    
    	
	}

}
