<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<title>Admin Edit Product</title>
</head>
<body>

<style>
p{
color: red;
}
</style>
<center>
<h2>Edit Product</h2>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav align="right"><a href="${contextPath}/AdminMainPage.htm">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${contextPath}/logout.htm">Logout</a></nav>

<fieldset>
<form action="${contextPath}/editProduct.htm" id="productForm" name="productForm" method="post" >
		<label>Product Name</label>
		<input type="text" id="productName" required placeholder="Enter Product Name"  name="productName" value = "${product.productName}"  /> 
		<br><br>
		<label>Product Price </label>
		$<input type="number" min=0 value = "${product.productPrice}" onchange="setTwoNumberDecimal(event)" name= "productPrice" id="productPrice" required  placeholder="Enter Product Price" name="productPrice" /><br><br>
		
		<label>Product Quantity</label>
		<input type="number" id="productQuantity" required  placeholder="Enter Product Quantity" name="productQuantity" value = "${product.productQuantity}" /><br><br>
		
		<label>Product Image</label>
		<input type="text" id="productImagePath" required  placeholder="Enter Product Image URL" name="productImagePath" value = "${product.productImagePath}" /><br><br>
		
		<input type="hidden" name="pid" value="${product.pid}">
		<button type="submit" >Edit Product</button>
		
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