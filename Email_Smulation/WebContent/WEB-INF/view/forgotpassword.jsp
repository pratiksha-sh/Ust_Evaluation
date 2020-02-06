<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Forgot Password</h1><br>
${msg1 }
<form action="forgotdata" method="post">
Email:<input type="text" name="email">

<h1>Security Question!!!!!!!!</h1><br>
<select name="Questions">
<option value="Questions">Questions</option>
<option value="What is your pet name?">What is your pet name?</option>
<option value="What is your favourite colour?">What is your favourite colour?</option>
<option value="What is your favourite place to visit?">What is your favourite place to visit?</option>
<option value="How many siblings do you have?">How many siblings do you have?</option>
</select><br>

<h2>Answer</h2><br>
<input type="answer" name="answer" required><br>
<input type="submit" value="submit">
<br>
<a href="login">Login</a>
</pre>
</form>
</body>
</html>