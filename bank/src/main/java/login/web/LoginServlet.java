package login.web;

import jakarta.servlet.RequestDispatcher;
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
//import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
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
		
		password=logindb.hash(loginbean);
		loginbean.setPassword(password);
//		loginbean.setUsername(username);
		
//		try {
//			username=logindb.encode(username);
//			loginbean.setUsername(username);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
		
//		byte[] seed = "this is a key".getBytes();
//		
//		SecureRandom sr=new SecureRandom();
////		long seed = 1; 
//		sr.setSeed(seed);
//		int value=sr.nextInt(1000);
//		String key=Double.toString(value).substring(2);
//		String user="manoj";
//		String fullClassName=this.getClass().getName();
//		String testcasenumber=fullClassName.substring(fullClassName.lastIndexOf('.')+1+"BenchmarkTest".length());
//		System.out.println(testcasenumber);
//		user +=testcasenumber;
//		String cookieName="rememberme"+testcasenumber;
//		System.out.println(cookieName);
//		jakarta.servlet.http.Cookie rememberme=new jakarta.servlet.http.Cookie(cookieName,key);
//		rememberme.setSecure(true);
//		rememberme.setPath(request.getRequestURI());
//		System.out.println(rememberme);
//		request.getSession().setAttribute(cookieName, rememberme);
//		response.addCookie(rememberme);
//		
		UUID uuid = UUID.randomUUID();
//        String username = uuid.toString();
//        UUID uuid1 = UUID.randomUUID();
//        String pass = uuid1.toString();
//        
//        Cookie loginCookie = new Cookie("username",user);
//		Cookie passw = new Cookie("password",pass);
//		loginCookie.setMaxAge(10*60);
//		response.addCookie(loginCookie);
//		response.addCookie(passw);
		
//		Cookie loginCookie = new Cookie("username",username);
		
//		Cookie passw = new Cookie("password",password);
		
//		loginCookie.setMaxAge(10*60);
//		response.addCookie(loginCookie);
		
//		response.addCookie(passw);
//		System.out.println(message);
		Float message = logindb.ubalance(loginbean);
		
//		HttpSession session=request.getSession();
//		session.setAttribute("username", username);
//		String stateInSession = (String) session.getAttribute("username");
//		System.out.println(stateInSession);
		
//		Cookie loginCookie = new Cookie("username",username);
//		loginCookie.setMaxAge(10*60);
//		response.addCookie(loginCookie);
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
//		String message1=logindb.decode(username);
		request.getSession().setAttribute("message", message);
		message= (Float) session.getAttribute("message");
		String message1=username;
		session.setAttribute("message1", message1);
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(logindb.validate(loginbean));
		
		if(logindb.validate(loginbean)) {
			
			session.setAttribute("username", username);
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("username",uuid.toString());
			userName.setMaxAge(30*60);
			response.addCookie(userName);
			response.sendRedirect("LoginSuccess.jsp");	
		}
		else {
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginPage.jsp");
//			PrintWriter out= response.getWriter();
//			out.println("<font color=red>Either user name or password is wrong.</font>");
//			rd.include(request, response);
			
			request.setAttribute("loginResult", true);
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/LoginPage.jsp");
	        requestDispatcher.forward(request, response);
		}
		
	}

}
