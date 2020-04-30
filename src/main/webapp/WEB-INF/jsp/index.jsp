<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
p {color:red;}

body {
	background-size: 100%;
	background-image: url("https://payload.cargocollective.com/1/20/645297/13318883/Instacart-explanation_500.jpg");
}

</style>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>InstaCart</title>
</head>
<body>
<center>
<fieldset>
<h2>InstaCart</h2>
<br>
<h3>Login as User</h3>
<form action="userlogin.htm" method = "post">
<label>Username</label>
<input type="email" id="userinput" class = "email" placeholder="Please enter your username" size="30" name="userName" required>
<br><br>
<label>Password</label>
<input type = "password" id="user" class = "email" placeholder = "Please enter your password" size="30" name ="userPassword" required>
<c:if test="${NoUser==true}" >
<p>Invalid UserName or Password</p>
</c:if>
<br><br>
<input type = "submit" id="usersubmit" class="btn" name = "submituser" value = "login">
<br><br><br><br><br><br><br><br><br><br><br>
<a href="adminlogin.htm">Login as Admin</a><br> 
<a href="userregister.htm">New User? Sign up here</a><br><br>
</form>
</fieldset>
</center>
</body>
	<c:if test="NoUser">
		<script type="text/javascript">
			var userName = document.getElementById("userName");
			var userPassword = document.getElementById("userPassword");
			userName.setAttribute("value", "");
			userPassword.setAttribute("value", "");
			alert("Please provide valid inputs");
		</script>
	
	</c:if>
</html>