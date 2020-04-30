<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="test">
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

.buttonClass{
margin-left:39.5%;
margin-top:2%;
}
button:hover {background-color: #e7e7e7;}

</style>
<title>Order Placed</title>
</head>
<body ng-app>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<br>
<nav align="right"><a href="${contextPath}/UserMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>
<br>

<nav align="right"><a href="${contextPath}/generatepdf.pdf">Receipt as PDF</a></nav>
<h3>Thank you for placing order at InstaCart! Your order will be delivered by an InstaCart person within a few hours! Please visit again!</h3>
<br>
<h2 align="center">Customer details</h2>
<br>
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
<td><h3>${deliveryMobile}</h3></td>
</tr>
<tr>
<td>Address :</td>
<td><h3>${deliveryAddress}</h3></td>
</tr>
</table>
<br>
<h2 align="center">Order details</h2>
<br>
<table>
<tr>
<th>Product Name</th>
<th>Product Image</th>
<th>Product Price</th>
<th>Product Quantity</th>
<th>Amount Due</th>
</tr>

<c:forEach items= "${cartList}" var="item">
<c:set var="sum" value="0">
</c:set>
<c:set var="sum" value="${sum + item.product.productPrice * item.cartQuantity}">
</c:set>
	<tr>
	<td align="center">${item.product.productName}</td>
	<td align="center"><img src="${item.product.productImagePath}" width="100px" height="100px"/></td>
	<td align="center">Rs.${item.product.productPrice}</td>
	<td align="center">${item.cartQuantity}</td>
	<td align="center">$${item.cartQuantity * item.product.productPrice}</td>
</c:forEach>
<tr>
	<td align="right" colspan="10"><b>Total Amount Due:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>$${sum}</b>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
</table>
<br>

</body>
</html>