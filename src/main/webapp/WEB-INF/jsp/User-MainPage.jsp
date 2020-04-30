<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome ${user.firstName}</title>
</head>
<body>

<div align="center">
<h2>Welcome ${user.firstName}</h2>
<br><br>
<fieldset>
<br>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/viewUserProducts.htm">View Products</a>
<br><br><br>
<a href="${contextPath}/viewCart.htm">View Cart</a>
<br><br><br>
<a href="${contextPath}/viewOrderHistory.htm">View Order History</a>
<br><br><br>
<a href="${contextPath}/viewUserProfile.htm">View Profile</a>
<br><br><br>
<a href="${contextPath}/logout.htm">Logout</a>
<br><br><br>
</div>
</fieldset>
</body>
</html>