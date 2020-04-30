<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
p{
color:red;
}
</style>
<title>Welcome ${admin.firstName}</title>
</head>
<body>

<div align="center">
<h2>Welcome ${admin.firstName}</h2>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<br>
<br>
<c:if test="${Update == true}">
<p>Product edited Successfully!</p>
</c:if>
<c:if test="${Delete == true}">
<p>Product removed Successfully</p>
</c:if>
<br>
<fieldset>
<br>

<a href="${contextPath}/viewProducts.htm">View Products</a>
<br><br><br><br>
<a href="${contextPath}/addProducts.htm">Add Products</a>
<br><br><br><br>
<a href="${contextPath}/viewAdminProfile.htm">View Profile</a>
<br><br><br><br>
<a href="${contextPath}/logout.htm">Logout</a>
<br><br><br><br>
</div>
</fieldset>
</body>
</html>