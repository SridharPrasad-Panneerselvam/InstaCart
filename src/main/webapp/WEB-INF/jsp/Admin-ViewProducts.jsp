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
  width:70px;
  border-radius:5px;
  height:25px;
  font-size:12px;
  cursor:pointer;
}

button:hover {background-color: #e7e7e7;}

</style>
<title>View Products</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h2 align="center">View Products</h2>
<nav align="right"><a href="${contextPath}/AdminMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>
<br>

<fieldset>
<center>
<form action = "${contextPath}/productSearch.htm" method="post" name="productSearch" id="productSearch">
Please provide product name to search : <input type = "text" name="nameSearch" id="nameSearch"/> &nbsp;&nbsp;&nbsp;
<button type="submit"> Search </button>
</form>
</center>
</fieldset>
<br><br>

<table>
<tr>
<th>Product Name</th>
<th>Product Image</th>
<th>Quantity</th>
<th>Product Price</th>
<th>Product Status</th>
<th>Edit Product</th>
<th>Remove Product</th>
</tr>

<c:forEach items= "${productList}" var="item">
	<tr>
	<td align="center">${item.productName}</td>
	<td align="center"><img src="${item.productImagePath}" width="100px" height="100px"/></td>
	<td align="center">${item.productQuantity}</td>
	<td align="center">Rs.${item.productPrice}</td>
	<td align="center">${item.productStatus}</td>
	<td><form action="${contextPath}/editProductButton.htm" method="post" name"editProduct" id="editProduct">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" name="item" value="${item.pid}">Edit</button>				
		</form> </td>
	<td><form action="${contextPath}/removeProductButton.htm" method="post" name"removeProduct" id="removeProduct">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" onclick="return Remove()" name="item" value="${item.pid}">Remove</button>				
		</form></td>
	</tr>
</c:forEach>
</table>

<script>
function Remove(){
	var remove= confirm("This action will remove the product data entirely. Are you sure?");
	if(remove)
		return true;
	else 
		return false;	
}
</script>
</body>
</html>