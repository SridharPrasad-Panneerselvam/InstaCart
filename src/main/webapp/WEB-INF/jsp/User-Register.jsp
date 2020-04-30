<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html data-ng-app="test">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<title>User SignUp</title>
</head>
<body ng-app>

<style>
p{
color: red;
}
</style>
<center>
<h2>User SignUp</h2>
<br><br>
<fieldset>
<form action="userSuccess.htm" id="registerForm" name="registerForm" method="post" novalidate>
		<label>User Name </label>
		<input type="email" id="userName" required  placeholder="Enter email"  name="userName" ng-model="User.userName" ng-pattern="/^[a-zA-Z][a-zA-Z_.0-9]+@gmail.com$/" ng-required="true" /> 
		<span ng-show='registerForm.userName.$touched && registerForm.userName.$invalid'>Please enter a valid emailId</span><br><br>
		<label>Password </label>
		<input type="password" id="userPassword" required placeholder="Enter password" name="userPassword" ng-model="User.userPassword" ng-required="true"/><br><br>
		
		<label>First Name</label>
		<input type="text" id="firstName" required placeholder="Enter First Name" name="firstName" ng-model="User.firstName" ng-required="true"/><br><br>
		
		<label>Last Name</label>
		<input type="text" id="lastName" required placeholder="Enter Last Name" name="lastName" ng-model="User.lastName" ng-required="true"/><br><br>
		
		<label>Delivery Mobile No</label>
		<input type="text" pattern= "[0-9]{10}" id="userMobile" placeholder="Enter mobile number" name="userMobile" ng-model="User.userMobile" ng-required="true"/><br><br>
		
		<label>Delivery Address</label>
		<input type="text" id="userAddress" placeholder="Enter Address" name="userAddress" ng-model="User.userAddress" ng-required="true"/><br><br>

		<button type="submit" ng-disabled="registerForm.$invalid">Sign up</button>
		
		<c:if test="${errorMessage == true}">
		<p>UserName already exists!</p>
</c:if>
</form> 
</fieldset>

</center>
</body>
</html>