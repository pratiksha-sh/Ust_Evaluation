<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">Welcome to Login page</h1>
${msg }
${msg2 }
${dto }
${msg3 }
<h4 align="center">
<form action="loginValidation" method="post">
Email<br>
<input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" name="email" title="Email must contain '@' and '.'" required><br>
Password
<br><input type="password" name="password" required><br>

<input type="submit" value="Login"><br>
<a href="registration">Registration</a>
</form>
</h4>
</body>
</html>