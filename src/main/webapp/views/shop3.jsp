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
<title>Grocery Shop</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<h1>Grocery Shop</h1>
	<br><br>
	<form action="/shop/toShopping;jsessionid=<%=session.getId()%>" method="post">
	<input type="hidden" name="formid" value="shop">
	<input type="hidden" name="nextPage" value="invoice">
	<input type="hidden" name="shopid" value="shop2">
		<table id="items" class="table table-striped">
			<tr><th>Name </th><th>Quantity</th><th>Price</th></tr>
			<coda:print shopId="shop3" component="${component}" />
		</table>
			<br><br>
	<button  type="submit" class="btn btn-success">Checkout</button>
	</form>
</div>
</body>
</html>