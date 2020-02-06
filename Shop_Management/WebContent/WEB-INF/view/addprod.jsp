<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<h1>Welcome...</h1>
<br>
${dto1 }
<form action="addData" method="post">
<pre>
Product Name: <input type="text" name="name" required><br>
Category:<input type="text" name="category" required><br>
Company Name:<input type="text" name="company" required><br>
Quantity: <input type="number" name="quantity" required><br>
Price: <input type="number" name="price" required><br>
<input type="submit" value="Add Product">
</pre>
</form>
</body>
</html>