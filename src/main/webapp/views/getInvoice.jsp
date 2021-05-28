<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<%@page import="java.util.ResourceBundle" buffer="8kb" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="container">
	<div class="screen">
		<div class="screen__content">
			<form method="post" action="sendEmail;jsessionid=<%=session.getId()%>" class="login form-login">
				<input type="hidden" name="formid" value="email">
				<div class="login__field">
					<i class="login__icon fas fa-user"></i>
					<input type="email" name="emailto" required placeholder="Enter Email Id" class="login__input"/>
				</div>
				
				<button class="button login__submit">
					<span class="button__text">Send Email</span>
					<i class="button__icon fas fa-chevron-right"></i>
				</button>				
			</form>
			
			<form method="post" action="sendSMS;jsessionid=<%=session.getId()%>" class="login" style="padding-top:0px">
				<input type="hidden" name="formid" value="sms">
				<div class="login__field">
					<i class="login__icon fas fa-user"></i>
					<input type="text" name="smsto" required placeholder="Enter SMS Number" class="login__input"/>
				</div>
				
				<button class="button login__submit">
					<span class="button__text">Send SMS</span>
					<i class="button__icon fas fa-chevron-right"></i>
				</button>				
			</form>
			
		</div>
		<div class="screen__background">
			<span class="screen__background__shape screen__background__shape4"></span>
			<span class="screen__background__shape screen__background__shape3"></span>		
			<span class="screen__background__shape screen__background__shape2"></span>
			<span class="screen__background__shape screen__background__shape1"></span>
		</div>		
	</div>
</div>		
<%-- 	<form action="sms.do;jsessionid=<%=session.getId()%>" method="post">
		<input type="hidden" name="formid" value="sms">	
		<input type="text" name="sms" required max=12 min=10 placeholder="Enter Phone Number">
		<input type="submit" value="Send SMS">
	</form> --%>
	
</body>
</html>