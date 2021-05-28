<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<%@page import="java.util.ResourceBundle" buffer="8kb" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/header.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Go Shopping...</title>
</head>
<body>
		<jsp:include page="header.jsp" />  
	<div class="container">	
		<h1>Welcome...<%=session.getAttribute("uname") %></h1>
		<hr>
		<h1>Go Shopping....</h1>
		<form action="shop/toShopping;jsessionid=<%=session.getId()%>" method="post" class="row g-3">
			<input type="hidden" name="formid" value="goshop" required>
			<input type="hidden" name="nextPage" value="shop1" required>
			<div class="col-auto">
				<button type="submit" class="btn btn-success">Shopping........</button>
			</div>
		</form>
		
		<br>
		<form action="invoice/ByInvoiceId;jsessionid=<%=session.getId()%>" method="post" class="row g3">
			<input type="hidden" name="formid" value="createExcel">
			<div class="input-group mb-3  col-auto w-auto">
				  <span>
					  <input type="text"  name="invoiceId"  class="form-control" placeholder="Invoice Id" aria-describedby="basic-addon1" required>
				  </span>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-dark">Get Inoice</button>
			</div>
		</form>
		
		<br>
		<form action="invoice/byDate;jsessionid=<%=session.getId()%>" method="post" class="row g-3">
			<input type="hidden" name="formid" value="byDate">
			<div class="input-group mb-3  col-auto w-auto">
				  <span>
					  <input type="date" name="fromDate" placeholder="fromDate" required class="form-control">
				  </span>
			</div>
			<div class="input-group mb-3  col-auto w-auto">
				  <span>
					  <input type="date" name="toDate" placeholder="toDate" required class="form-control">
				  </span>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-light">Get Inoice</button>
			</div>
		</form>
		
		<br>
		<form action="invoice/ByCustomer;jsessionid=<%=session.getId()%>" method="post" class="row g-3">
			<input type="hidden" name="formid" value="byCustomer">
			<div class="input-group mb-3  col-auto w-auto">
				  <span>
					<input type="text" name="customerId" placeholder="customer id" required class="form-control">
				</span>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary">Get Inoice</button>
			</div>
		</form>
	</div>
</body>
</html>