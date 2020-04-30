<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: centre;
  padding: 8px;
}

tr:nth-child(even){
  background-color: #dddddd;
}
</style>

<title>${user.firstName}'s Profile</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<center>
<h2>${user.firstName}'s Profile</h2>
<br><br><br>
</center>
<nav align="right"><a href="${contextPath}/UserMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>

<fieldset>

<br><br><br>
<table>
<tr>
<td>First Name :</td>
<td><h3>${user.firstName}</h3></td>
</tr>
<tr>
<td>Last Name :</td>
<td><h3>${user.lastName}</h3></td>
</tr>
<tr>
<td>Username :</td>
<td><h3>${user.userName}</h3></td>
</tr>
<tr>
<td>Mobile Number :</td>
<td><h3>${user.userMobile}</h3></td>
</tr>
<tr>
<td>Address :</td>
<td><h3>${user.userAddress}</h3></td>
</tr>
</table>
</fieldset>
</body>
</html>