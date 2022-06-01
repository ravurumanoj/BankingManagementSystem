<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logined</title>
<style>
	body{
		background-color:#f0f0f0;
	/*	margin:10px; */
	/*	padding:10px; */
	}
	.l{
		position:absolute;
		margin: 1% 0% 1% 80%;
		background-color:#159aff;
		font-size:15px;
		color:white;
		cursor:pointer;
		border:none;
	}
	.t{
		position:absolute;
		margin: 5% 0% 5% 60%;
		/* padding:40px 30px 40px 30px;  */
		padding:5%;
		background-color:white;
	}
	h3{
		text-align:center;
		
	}
	input[type=submit]{
		padding:10px 70px;
		margin:5px;
		cursor:pointer;
		background-color:#159aff;
		font-size:15px;
		color:white;
		border:none;
	}
	input{
		padding:10px;
		margin:5px;
	}
	input:focus{
		border: 2px solid #e92b2b;
		outline:none;
	}
	
	/* button{
		position:absolute;
		margin: 1% 0% 1% 80%;
		padding: 7px 15px;
		background-color:#159aff;
		font-size:15px;
		color:white;
		cursor:pointer;
		border:none;
	} */

</style>
</head>
<body>
<%
//allow access only if session exists
	String username = null;
	if(session.getAttribute("username") == null){
		response.sendRedirect("LoginPage.jsp");
	}
	else username = (String) session.getAttribute("username");
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("username")) userName = cookie.getValue();
				if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			}
		}
%>
<h3>Hi <%=username %>, Login successful. Your Session ID=<%=sessionID %></h3>
<%-- <br>
User=<%=username %>
<br> --%>
	<%-- <% 
	String username = null;
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("username")){
				username = cookie.getValue();
			}
		}
	} 
	if(username == null) response.sendRedirect("LoginPage.jsp");
	 %>  --%>
	<%-- <%
		String username = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("username")){
					username = cookie.getValue();
				}
			}
		}
		if(username == null) response.sendRedirect("LoginPage.jsp");
	%> --%>
	<%-- <h3>Hi <%=username%>, Welcome To ZOHO Bank</h3> --%>
	<%-- <%=session.getAttribute("message1")%> --%>
	<%-- <h3>Hi ${sessionScope['message1']}, Welcome To ZOHO Bank</h3><br> --%>
<%-- 	<h3>Hi ${sessionScope['username']}, Welcome To ZOHO Bank</h3><br> --%>
	<b>Balance: ${sessionScope['message']}</b>
<!--	<a href="./LoginPage.jsp"><button>Sign Out</button></a>  -->
	<div class="l"><form action="LogOut" method="post">
	<input type="submit" value="Logout">
	</form></div>
	<div class="t">
	<form action="TransferServlet" method="post">
	<h3>Transfer Money</h3>
	<table>
	<tr><td>Account Username: </td><td><input type="text" name="rusername" placeholder="Enter Username" required></td></tr>
	<tr><td>Amount: </td><td><input type="number" name="amount" placeholder="Enter Amount" min="0" required></td></tr>
	<tr><td></td><td><input type="submit" value="Transfer"></td></tr>
	<%
		if(request.getAttribute("transferResult") != null && "true".equals(request.getAttribute("transferResult").toString())) {
			%>
            	<p style="color: red">Enter a valid Amount or username.</p> 
            <%
           }
     %>
	</table>
	</form>
	</div>
</body>
</html>