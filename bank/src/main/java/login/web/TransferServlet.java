package login.web;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import login.bean.LoginBean;
import login.database.LoginDb;

import java.io.IOException;
import java.net.URLEncoder;

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

		LoginDb logindb=new LoginDb();
		
		if(logindb.validatetransfer(loginbean)){
			logindb.addAmount(loginbean);
			Float message = logindb.ubalance(loginbean);
			request.getSession().setAttribute("message", message);
			response.sendRedirect("LoginSuccess.jsp");
//			System.out.println(amount+loginbean.getUbalance());
		}
		else {
			
			System.out.println(amount+"bbbbb");
		}
	}

}
