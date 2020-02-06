<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update</title>
</head>
<body>
<h1 align='center'>Update Page</h1>
<h3 style="color:#00bfff">${msg1 }</h3>
	<h4 align='center'>
			<form action='UpdateData' method='post'>
			<input type='hidden' name='id' value="${obj.getProductid() }"><br>
			Product Name<br>
			<input type='text' name='name' value="${obj.getName() }"><br>
			Product Category<br>
			<input type='text' name='category' value="${obj.getCategory() }"><br>
			Product Company<br>
			<input type='text' name='company' value="${obj.getCompany() }"><br>
			Product Quantity<br>
			<input type='number' name='quantity' value="${obj.getQuantity() }"><br>
			Product Price<br>
			<input type='number' name='price' value="${obj.getPrice() }"><br>
			<input type='submit' value='Update'>
			</form>
			</h4>
</body>
</html>