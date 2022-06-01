package login.web;

import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import login.bean.LoginBean;
import login.database.LoginDb;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		String contactno= request.getParameter("contactno");
		
		LoginBean signupbean=new LoginBean();
		signupbean.setUsername(username);
		signupbean.setPassword(password);
		signupbean.setContactno(contactno);
		
		LoginDb logindb=new LoginDb();
		
		password=logindb.hash(signupbean);
		signupbean.setPassword(password);
		signupbean.setUsername(username);
//		try {
//			username=logindb.encode(username);
//			signupbean.setUsername(username);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		logindb.insert(signupbean);
		
//		Cookie c=new Cookie("username",username);
//		Cookie c1=new Cookie("password",password);
//		Cookie c2=new Cookie("contactno",contactno);
//		response.addCookie(c);
//		response.addCookie(c1);
//		response.addCookie(c2);
		
		response.sendRedirect("SignUp.jsp");

	}

}
