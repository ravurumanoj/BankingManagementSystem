package login.web;

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
import java.net.URLEncoder;
import java.sql.SQLException;

/**
 * Servlet implementation class TransferServlet
 */
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServlet() {
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
		String rusername= request.getParameter("rusername");
		Float amount=Float.parseFloat(request.getParameter("amount"));
		
		LoginServlet obj = new LoginServlet();
//		System.out.println(obj.username+"456");
		String username=obj.username;
		
		LoginBean loginbean=new LoginBean();
		loginbean.setRusername(rusername);
		loginbean.setUsername(username);
		loginbean.setAmount(amount);
		
		Cookie c=new Cookie("rusername",rusername);
		Cookie c2=new Cookie("username",username);
		Cookie c3=new Cookie("amount",amount+"");
		response.addCookie(c);
		response.addCookie(c2);
		response.addCookie(c3);
//		String u = null;
//		Cookie cookies[]=request.getCookies();
//		for(Cookie co:cookies)
//		{
//			if(co.getName().equals("c"))
//				u=co.getValue();
//		}
//		System.out.println(u);
		LoginDb logindb=new LoginDb();
		
		if(logindb.validatetransfer(loginbean)){
			try {
				logindb.addAmount(loginbean);
				Float message = logindb.ubalance(loginbean);
				HttpSession session=request.getSession();	
				session.setAttribute("message", message);
				response.sendRedirect("LoginSuccess.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			SQLException sqlException = new SQLException();
			sqlException.printStackTrace();
		}
	}

}
