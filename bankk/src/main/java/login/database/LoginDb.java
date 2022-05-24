package login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.bean.LoginBean;

public class LoginDb {
	private String dbUrl = "jdbc:mysql://localhost:3306/login";
	private String dbUname = "root";
	private String dbPassword = "manoj123";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public int insert(LoginBean signupbean)
	{		
		loadDriver(dbDriver);
		Connection con = getConnection();
		String insert="INSERT INTO login"+"(username,password,contactno) VALUES"+"(?,?,?);";
		int result=0;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(insert);
			ps.setString(1, signupbean.getUsername());
	        ps.setString(2, signupbean.getPassword());
	        ps.setString(3, signupbean.getContactno());
	        
			result = ps.executeUpdate();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return result;
	}
	public boolean validatetransfer(LoginBean loginBean)
	{
		boolean status = false;
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		
		String sql = "select * from login where username = ?";
		PreparedStatement ps;
		
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, loginBean.getRusername());
		ResultSet rs = ps.executeQuery();
		status = rs.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public boolean addAmount(LoginBean loginbean)
	{
		boolean status = false;
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		float ubal,rbal;
		
		String ubalance = "select balance from login where username = ?";
		String rbalance = "select balance from login where username = ?";
		String query = "update login set balance = ? where username = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(ubalance);
			ps.setString(1, loginbean.getUsername());
			System.out.println(loginbean.getUsername());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ubal = rs.getFloat("balance");
				ubal = ubal - loginbean.getAmount();
				System.out.println(ubal+"gh");
				ps = con.prepareStatement(query);
				ps.setFloat(1, ubal);
				ps.setString(2, loginbean.getUsername());
				int rs1 = ps.executeUpdate();
				//System.out.println(ubal+"gh");
				//loginbean.setUbalance(ubal);
			}
			
			//ResultSet rs1 = ps.executeQuery();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ps = con.prepareStatement(rbalance);
			ps.setString(1, loginbean.getRusername());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				rbal = rs.getFloat("balance");
				loginbean.setRbalance(rbal);
				System.out.println(rbal);
				rbal = rbal+loginbean.getAmount();
				ps = con.prepareStatement(query);
				ps.setFloat(1, rbal);
				ps.setString(2, loginbean.getRusername());
				int rs1 = ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status;
	}
	public boolean validate(LoginBean loginBean)
	{
		boolean status = false;
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		
		String sql = "select * from login where username = ? and password =?";
		PreparedStatement ps;
		
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, loginBean.getUsername());
		ps.setString(2, loginBean.getPassword());
		ResultSet rs = ps.executeQuery();
		status = rs.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
