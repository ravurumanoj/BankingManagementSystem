<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<style>
	body{
		background-color:#f0f0f0;
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
		margin: 0% 37%;
		/* padding:40px 30px 40px 30px; */
		padding:2% 3%;
		background-color:white;
	}
</style>
</head>
<body>
	<div style="text-align:center">
		<div class="h"><h2>Sign Up</h2></div>
		<form action="SignupServlet" method="post" >
		<table>
		<tr><td>Username: </td><td><input type="text" name="username" placeholder="Enter your Username" required></td></tr>
		<tr><td>Password: </td><td><input type="password" name="password" placeholder="Enter your Password" required></td></tr>
		<tr><td>Contact No: </td><td><input type="number" name="contactno" placeholder="Enter your Phno" min="1000000000" required></td></tr>
		<tr><td></td><td><input type="submit" value="SignUp"></td></tr>
		<tr><td colspan=2>You can Login Now</td></tr>
		<tr><td colspan=2><a href="LoginPage.jsp">Login</a></td></tr>
		</table>
		</form>
	</div>
</body>
</html>