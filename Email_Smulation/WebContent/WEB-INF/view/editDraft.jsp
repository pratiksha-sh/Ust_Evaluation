<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center><h2>Mail </h2></center>
<form action="editDraftMail" method="post">
<pre>
<input type="hidden" name="Id" value="${From_id.getId() }">
To:<input type="text" name="To" value="${From_id.getSentto() }" required><br>
Subject:<input type="text" name="Subject" value="${From_id.getSubject() }" ><br>
Message: <input type="text" name="Message" value="${From_id.getMessage() }" required><br>

<input type="submit" value="Send">
</pre>
</form>
</body>
</html>