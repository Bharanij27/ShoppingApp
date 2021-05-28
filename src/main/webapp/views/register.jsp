<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<%@page import="java.util.ResourceBundle" buffer="8kb" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
	<link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="container">
	<div class="screen">
		<div class="screen__content">
			<spring:form method="post" action="newUser" modelAttribute="loginForm" class="login">
				<div class="login__field">
					<i class="login__icon fas fa-user"></i>
					<spring:input path="uname" type="text" class="login__input" placeholder="User name"/>
					<p class="red">${message} </p>
				</div>
				<div class="login__field">
					<i class="login__icon fas fa-lock"></i>
					<spring:password path="upass" class="login__input" placeholder="Password"/>
				</div>
				<button class="button login__submit">
					<span class="button__text">Register</span>
					<i class="button__icon fas fa-chevron-right"></i>
				</button>				
			</spring:form>
		<div class="social-login">
			<h3>Already a User? <a href="/">Login</a></h3>
		</div>	
		</div>
		<div class="screen__background">
			<span class="screen__background__shape screen__background__shape4"></span>
			<span class="screen__background__shape screen__background__shape3"></span>		
			<span class="screen__background__shape screen__background__shape2"></span>
			<span class="screen__background__shape screen__background__shape1"></span>
		</div>		
	</div>
</div>
</body>
</html>
