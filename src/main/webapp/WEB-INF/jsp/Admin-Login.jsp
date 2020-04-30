<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
p {color:red;}

body {
	background-size: 100%;
	background-image: url("https://cdn.centsai.com/wp-content/uploads/2018/11/Featured-image-for-Instacart-Reviews-1-660x440_c.jpg");
}

</style>
<meta charset="UTF-8">
<title>InstaCart</title>
</head>
<body>
<center>
<fieldset>
<h2>InstaCart</h2>
<br><br>
<h3>Login as Admin</h3>
<form action="adminpage.htm" method = "post">
<label>Username</label>
<input type="email" id="userinput" class = "email" placeholder="Please enter your username" name="adminName" required>
<br>
<label>Password</label>
<input type = "password" id="userinput" class = "email" placeholder = "Please enter your password" name ="adminPassword" required>
<c:if test="${NoAdmin==true}" >
<p>Invalid UserName or Password</p>
</c:if>
<br>
<input type = "submit" id="usersubmit" class="btn" name = "submitadmin" value = "login">
<br>
<a href="userlogin.htm">Login as User</a><br> 
</form>
</fieldset>
</center>
</body>
</html>