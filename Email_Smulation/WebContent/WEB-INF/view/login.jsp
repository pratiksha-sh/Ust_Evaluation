<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to Login page</h1>
${msg }
${msg2 }
${dto }
${msg3 }
<form action="loginValidation" method="post">
Email:<input type="email" name="email" required><br>
Password:<input type="password" name="password" required><br>

<input type="submit" value="Login"><br>
<a href="forgotpassword">Forgot Password</a>|
<a href="registration">Registration</a>
</form>
</body>
</html>