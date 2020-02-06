<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to Registration page</h1>
<br>
${dto }<center>
<form action="registrationData" method="post">
<pre>
User Name: <input type="text" name="User_name" required><br>
<label for="email">Enter your gmail.com Email:</label>

<input type="email" id="email"
       pattern=".+@gmail.com" size="30" name="email" required><br>
<!-- Email:<input type="email" name="email" required ><br> -->
Password:<input type="password" name="password" required><br>
<h5>Security Question</h5>
<select name="Questions">
<option value="Questions">Questions</option>
<option value="What is your Lucky number">What is your pet name?</option>
<option value="What is your favourite colour?">What is your favourite colour?</option>
<option value="What is your favourite place to visit?">What is your favourite place to visit?</option>
<option value="How many siblings do you have?">How many siblings do you have?</option>
</select>
<h3>Answer</h3>
<input type="answer" name="answer" required><br>
<input type="submit" value="Register"><br>
<a href="login">Login</a>
</pre>
</form>
</center>
</body>
</html>