<%@ taglib uri="/WEB-INF/myjsptags.tld" prefix="coda" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<%@page import="java.util.ResourceBundle" buffer="8kb" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/header.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/css/shop.css">
	<link rel="stylesheet" href="/css/checkout.css">
<title>Grocery Shop</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<h1>Invoice Page.... You invoice id is : <%= session.getAttribute("invoiceId") %></h1>
	<table id="items">
	<tr><th>Product Name</th><th>Quantity</th><th>Total Price</th></tr>
		<coda:invoice component="${component}" />
	</table>
	
	<br><br>	
	<form action="/invoice/create;jsessionid=<%=session.getId()%>" method="post">
		<input type="hidden" name="formid" value="invoice">
		<div>
				<label class="form-label">Customer Id </label>
				<input type="text" name="customerId" required>
				<input type="submit" value="Generate">
			</div>		
	</form>	
	<div><p> (or) </p></div>
	<br><br>
	
	<form action="/invoice/create;jsessionid=<%=session.getId()%>" method="post">
		<input type="hidden" name="formid" value="invoice">
			<div>
				<label class="form-label">Customer Name </label>
				<input type="text" name="cname" required>
			</div>		
			<br><br>
			
			<div>
				<label class="form-label"> Address </label>
				<input type="text" name="address" required>
			</div>
			<br><br>
			
			<div>
				<label class="form-label"> Phone Number </label>
				<input type="text" name="phnum" required>
			</div>
			<br><br>
			
			<div>
				<label class="form-label"> Account Number </label>
				<input type="text" name="accnum" required>
			</div>
			
			<br><br>
			
			<div>
				<label class="form-label"> GST Number </label>
				<input type="text" name="gstnum" required>
			</div>
		
			<br><br>
			
			
		<input class="transfer" type="submit" value="Generate">
	</form>
</div>
</body>
</html>