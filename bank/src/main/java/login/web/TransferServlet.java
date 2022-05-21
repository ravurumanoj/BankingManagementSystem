package login.web;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import login.bean.LoginBean;
import login.database.LoginDb;

import java.io.IOException;

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
		String username= request.getParameter("username");
		String amount=request.getParameter("amount");
		LoginBean loginbean=new LoginBean();
		loginbean.setUsername(username);
		
		LoginDb logindb=new LoginDb();
		
		if(logindb.validatetransfer(loginbean)) {
			System.out.println(amount+"AAAAAAAA");
		}
		else {
//			response.sendRedirect("LoginPage.jsp");
			System.out.println(amount+"bbbbb");
		}
	}

}
