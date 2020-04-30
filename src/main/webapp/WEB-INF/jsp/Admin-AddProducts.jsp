<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<title>Admin AddProduct</title>
</head>
<body>

<style>
p{
color: red;
}
</style>
<center>
<h2>Add Product</h2>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav align="right"><a href="${contextPath}/AdminMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>

<fieldset>
<form action="${contextPath}/addProductSuccess.htm" id="productForm" name="productForm" method="post" >
		<label>Product Name</label>
		<input type="text" id="productName" placeholder="Enter Product Name"  name="productName" required /> 
		<br><br>
		<label>Product Price </label>
		Rs.<input type="number" min=0 value="0"  onchange="setTwoNumberDecimal(event)" name= "productPrice" id="productPrice" required  placeholder="Enter Product Price" name="productPrice"/><br><br>

		<label>Product Quantity</label>
		<input type="number" id="productQuantity" placeholder="Enter Product Quantity" name="productQuantity" required/><br><br>
		
		<label>Product Image</label>
		<input type="text" id="productImagePath" placeholder="Enter Product Image URL" name="productImagePath" required /><br><br>
		
	
		<button type="submit" >Add Product</button>
		
<c:if test="${errorMessage == true}">
<p>Product already exists! Please update the quantity instead</p>
</c:if>

</form> 
</fieldset>

</center>
</body>
<script type="text/javascript">
 
function setTwoNumberDecimal(event){
	this.value = parseFloat(this.value).toFixed(2);
	}
 </script>
</html>