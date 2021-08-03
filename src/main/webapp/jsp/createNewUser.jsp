<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
 
 Enter a username and password
 
 <form method="post" action="/createNewUser">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 	Name: <input type="text" name="name"><br>
 	Password: <input type="password" name="password"><br>
 	<input type="submit" value="Submit">
 </form><br>
 <br>
 
</body>
</html>