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

.buttonClass{
margin-left:39.5%;
margin-top:2%;
}
button:hover {background-color: #e7e7e7;}

</style>
<title>CheckOut</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<h2 align="center">Check Out</h2>
<nav align="right"><a href="${contextPath}/UserMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>
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
	<td align="center">Rs.${item.cartQuantity * item.product.productPrice}</td>
</c:forEach>
<tr>
	<td align="right" colspan="10"><b>Total Amount Due:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>${sum}</b>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
</tr>
</table>
<br>
<center>
<h2>Delivery Details</h2>

<br>
<form action="${contextPath}/placeOrder.htm" id="deliveryForm" name="deliveryForm" method="post">
<fieldset>
		<label>First Name</label>
		<input type="text" size="25" readonly id="firstName"  value ="${user.firstName}" placeholder="Enter First Name" name="firstName" required/><br><br>
		
		<label>Last Name</label>
		<input type="text" size="25" readonly id="lastName"  value ="${user.lastName}"  placeholder="Enter Last Name" name="lastName" required/><br><br>
		
		<label>Mobile</label>
		<input type="text" value="${user.userMobile}" size = "25" pattern= "[0-9]{10}" id="userMobile" placeholder="Enter mobile number" name="userMobile"  required/><br><br>
		
		<label>E-Mail</label>
		<input type="email" size="25" readonly id="userName" placeholder="Enter email" value ="${user.userName}" name="userName" required/><br><br>
		
		<label>Delivery Address</label>
		<input type="text" size="25"  id="userAddress" placeholder="Enter Address" name="userAddress" value ="${user.userAddress}" required/><br><br>		
 
</fieldset>
</center>
<div class="buttonClass">
<button type="submit" onclick="return placeOrder()" style="font-weight: bold">Place Order</button>
</div>
</form>

<script>
function Remove(){
	var remove= confirm("This action will remove the selected cart data entirely. Are you sure?");
	if(remove)
		return true;
	else 
		return false;	
}
function Checkout(){
	var remove= confirm("Do you want to Proceed to checkout?");
	if(remove)
		return true;
	else 
		return false;	
}
function placeOrder(){
	var remove= confirm("Are you sure you wanna place the order?");
	if(remove)
		return true;
	else 
		return false;	
}

</script>
</body>
</html>