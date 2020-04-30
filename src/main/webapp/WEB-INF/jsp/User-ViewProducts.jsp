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

tr:nth-child(even) {
  background-color: #dddddd;
}

p{
color: red;
}

button{
  background-color: white;
  color: black;
  border: 2px solid #e7e7e7;
  width:100px;
  border-radius:5px;
  height:25px;
  font-size:12px;
  cursor:pointer;
  margin-left:100px;
}

button:hover {background-color: #e7e7e7;}

</style>
<title>View Products</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<h2 align="center">View Products</h2>
<nav align="right"><a href="${contextPath}/UserMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>
<br>

<fieldset>
<center>
<form action = "${contextPath}/productSearchforUser.htm" method="post" name="productSearch" id="productSearch">
Please provide product name to search : <input type = "text" name="nameSearch" id="nameSearch"/> &nbsp;&nbsp;&nbsp;
<button type="submit"> Search </button>
</form>
</center>
</fieldset>
<br><br>
<div align="right">
	<c:if test="${error == true}">
	<p>Product already exists in Cart! Please check </p>	
	</c:if>
	<c:if test="${success == true}">
	<p>Product added Successfully</p>
	</c:if>
</div>
<table>
<tr>
<th>Product Name</th>
<th>Product Image</th>
<th>Product Price</th>
<th>Product Status</th>
<th>Cart</th>
</tr>

<c:forEach items= "${productList}" var="item">
	<tr>
	<td align="center">${item.productName}</td>
	<td align="center"><img src="${item.productImagePath}" width="100px" height="100px"/></td>
	<td align="center">Rs.${item.productPrice}</td>
	<td align="center">${item.productStatus}</td>
	<td style ="width:250px">
	<c:choose>
	<c:when test="${item.productStatus == 'In Stock'}">
	<form action="${contextPath}/addToCartButton.htm" method="post" name="addToCart" id="addToCart"><button type="submit" value = "${item.pid}" name="item" style ="margin-left:75px" >Add to Cart</button>				
	</form> 
	</c:when>
	<c:when test="${item.productStatus == 'Low on Stock'}">
	<form action="${contextPath}/addToCartButton.htm" method="post" name="addToCart" id="addToCart"><button type="submit" value = "${item.pid}" name="item" style ="margin-left:75px; color:red;" >Add to Cart</button>				
	</form> 
	</c:when> 
	</c:choose>

	</td>
	</tr>
</c:forEach>
</table>

</body>
</html>