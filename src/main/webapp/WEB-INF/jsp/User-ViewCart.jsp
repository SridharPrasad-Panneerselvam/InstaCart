<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.text.DecimalFormat" %>
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
margin-top:5%;
}
button:hover {background-color: #e7e7e7;}

</style>
<title>View Cart</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<h2 align="center">View Cart</h2>
<nav align="right"><a href="${contextPath}/UserMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/viewCart.htm">Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>
<br>

<br><br>
<div align="right">
<c:if test="${success == true}">
	<p>Quantity updated Successfully</p>
	</c:if>
	<c:if test="${error == true}">
	<p>Quantity entered is not available, please enter a lesser quantity</p>
	</c:if>
	</div>
<table>
<tr>
<th>Product Name</th>
<th>Product Image</th>
<th>Product Price</th>
<th>Product Quantity</th>
<th>Amount Due</th>
<th>Cart</th>
</tr>


<c:set var="sum" value="0">
</c:set>
<c:forEach items= "${cartList}" var="item">
<c:set var="sum" value="${sum + item.product.productPrice * item.cartQuantity}">
</c:set>
	<tr>
	<td align="center">${item.product.productName}</td>
	<td align="center"><img src="${item.product.productImagePath}" width="100px" height="100px"/></td>
	<td align="center">Rs.${item.product.productPrice}</td>
	<td align="center" style="width:250px;">
	<form action="${contextPath}/updateQuantity.htm" name="quantityform" method="post" id="quantityform" style="width=300px; height:50px;"> 
    <input type="number" value="${item.cartQuantity}" min="1" style="width:35px; height:25px; margin-left:0px;" name="cartQuantity" required> 
    <input type="hidden" name="totalQuantity" value="${item.product.productQuantity}"><button type="submit" value="${item.cid}" name="item" style = "margin-left:10px;"> Add </button> </form>
    </td>
	<td align="center" >Rs.${(item.cartQuantity) * (item.product.productPrice)}</td>
	<td><form action="${contextPath}/removeCartButton.htm" method="post" name="removeCart" id="removeCart" style="width:100px; margin-bottom:25px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="submit" style ="width:138px; margin-left:40px; height:25px;" onclick="return Remove()" name="item" value="${item.cid}">
		 Remove from Cart</button>				
		</form></td>
	</tr>
</c:forEach>
<tr>
	<td align="right" colspan="10" ><b>Total Amount Due:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Rs.${sum}</b>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
</tr>
</table>


<c:if test="${sum == 0}">
<div class="buttonClass">
<form action="${contextPath}/checkout.htm" method="post" name="checkoutCart" id="checkoutCart">
<button type="submit" disabled ="disabled" onclick="return Checkout()" style="font-weight:bold;">CheckOut</button>
</form>
</div>
</c:if>
<c:if test="${sum > 0}">
<div class="buttonClass">
<form action="${contextPath}/checkout.htm" method="post" name="checkoutCart" id="checkoutCart">
<button type="submit" onclick="return Checkout()" name= "item" value="${item.cid}" style="font-weight:bold;">CheckOut</button>
</form>
</div>
</c:if>



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

</script>
</body>
</html>