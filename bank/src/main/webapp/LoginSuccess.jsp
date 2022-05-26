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
	input{
		padding:10px;
		margin:5px;
	}
	input:focus{
		border: 2px solid #e92b2b;
		outline:none;
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
	form{
		position:absolute;
		margin: 5% 0% 5% 60%;
		/* padding:40px 30px 40px 30px; */
		padding:4%;
		background-color:white;
	}
	h3{
		text-align:center;
		
	}
	button{
		position:absolute;
		margin: 1% 0% 1% 80%;
		padding: 7px 15px;
		background-color:#159aff;
		font-size:15px;
		color:white;
		cursor:pointer;
		border:none;
	}

</style>
</head>
<body>
	<%
		String username = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("username")) username = cookie.getValue();
			}
		}
		if(username == null) response.sendRedirect("LoginPage.jsp");
	%>
	<h3>Hi <%=username%>, Welcome To ZOHO Bank</h3>
	<b>Balance: ${sessionScope['message']}</b>
	<a href="./LoginPage.jsp"><button>Sign Out</button></a>
	<form action="TransferServlet" method="post">
	<h3>Transfer Money</h3>
	<table>
	<tr><td>Account Username: </td><td><input type="text" name="rusername" placeholder="Enter Username" required></td></tr>
	<tr><td>Amount: </td><td><input type="number" name="amount" placeholder="Enter Amount" min="0" required></td></tr>
	<tr><td></td><td><input type="submit" value="Transfer"></td></tr>
	</table>
	</form>
</body>
</html>