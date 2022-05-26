package login.database;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

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
	public static byte[] getSHA(String password) throws NoSuchAlgorithmException  
	{   
		MessageDigest md = MessageDigest.getInstance("SHA-512"); 
		return md.digest(password.getBytes(StandardCharsets.UTF_8));  
	} 
	public static String toHexString(byte[] hash)  
	{    
		BigInteger number = new BigInteger(1, hash);  
	    StringBuilder hexString = new StringBuilder(number.toString(16));
	    while (hexString.length() < 32)  
	    {  
	        hexString.insert(0, '0');  
	    } 
	return hexString.toString();  
	} 
	public String hash(LoginBean loginBean)  
	{  
		String password = null;
		try  
	    {   
			password=toHexString(getSHA(loginBean.getPassword())); 
	    }  
	     catch (NoSuchAlgorithmException e)   
	     {  
	    	 System.out.println("Exception thrown for incorrect algorithm: " + e);  
	     }
		return password;
	}   
	public int insert(LoginBean signupbean)
	{		
		loadDriver(dbDriver);
		Connection con = getConnection();
		String insert="INSERT INTO login"+"(username,password,contactno,balance) VALUES"+"(?,?,?,?);";
		int result=0;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(insert);
			ps.setString(1, signupbean.getUsername());
	        ps.setString(2, signupbean.getPassword());
	        ps.setString(3, signupbean.getContactno());
	        ps.setFloat(4, 10000);
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
	public Float ubalance(LoginBean loginbean)
	{
		float b = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		String ubalance = "select balance from login where username = ?";
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(ubalance);
			ps.setString(1, loginbean.getUsername());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				b = rs.getFloat("balance");
			}		
		} catch (SQLException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	public boolean addAmount(LoginBean loginbean) throws SQLException
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
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				ubal = rs.getFloat("balance");
				if(loginbean.getAmount()<ubal && loginbean.getAmount()>0) {
					ubal = ubal - loginbean.getAmount();
					ps = con.prepareStatement(query);
					ps.setFloat(1, ubal);
					ps.setString(2, loginbean.getUsername());
					int rs2 = ps.executeUpdate();
					
					ps = con.prepareStatement(rbalance);
					ps.setString(1, loginbean.getRusername());
					ResultSet rs1 = ps.executeQuery();
					if(rs1.next()) {
						rbal = rs1.getFloat("balance");
						rbal = rbal+loginbean.getAmount();
						ps = con.prepareStatement(query);
						ps.setFloat(1, rbal);
						ps.setString(2, loginbean.getRusername());
						int rs3 = ps.executeUpdate();
					}
				}
				
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
