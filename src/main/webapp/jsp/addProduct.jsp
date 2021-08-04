<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
 
 Enter the product information
 
 <form method="post" action="/addProduct">
 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 	Product Name: <input type="text" name="pName"><br>
 	Product Price: <input type="text" name="pPrice"><br>
 	${error}
 	<input type="submit" value="Submit">
 </form><br>
 <br>
 
</body>
</html>