<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/style.css" rel="stylesheet" type = "text/css">
</head>
<body>
 
 Enter the product information to update
 
 <form method="post" action="/userUpdate">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 	Product Quantity:<input type="number" name="pQuantity"><br>
 	${error}
 	<input type="submit" value="Submit">
 </form><br>
 <br>
 
</body>
</html>