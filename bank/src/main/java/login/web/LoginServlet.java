package login.web;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.bean.LoginBean;
import login.database.LoginDb;

import java.io.IOException;
//import java.net.URLEncoder;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	static String username;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		username= request.getParameter("username");
		//System.out.println(username);
		String password= request.getParameter("password");
		
		LoginBean loginbean=new LoginBean();
		loginbean.setUsername(username);
		loginbean.setPassword(password);
		
		LoginDb logindb=new LoginDb();
		Float message = logindb.ubalance(loginbean);
		password=logindb.hash(loginbean);
		loginbean.setPassword(password);
//		System.out.println(message);
		HttpSession session=request.getSession();
		session.setAttribute("message", message);
//		request.getSession().setAttribute("message", message);
		message= (Float) session.getAttribute("message");
		
		Cookie c=new Cookie("username",username);
		response.addCookie(c);
		
		if(logindb.validate(loginbean)) {
			response.sendRedirect("LoginSuccess.jsp");
		}
		else {
			response.sendRedirect("LoginPage.jsp");
		}
		
	}

}
