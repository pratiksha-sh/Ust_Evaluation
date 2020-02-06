<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<center><h1>GMAIL</h1></center>
<h3>Welcome ${dto.getUser_name() }</h3>
${msg1 }
${msg }
<h3><a href="inbox">Inbox</a>|

<a href="sentitem">Sent items</a>|
<a href="deleteitem">Delete items</a>|
<a href="draft">Draft Mails</a>|
<a href="composemail">Compose Mail</a>|
<a href="logout">Logout</a>
</h3>

</body>
</html>