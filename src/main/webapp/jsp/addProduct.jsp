<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
 Enter the product information
 
 <form method="post" action="/addProduct">
 	Product Name: <input type="text" name="pName"><br>
 	Product Price: <input type="number" name="pPrice"><br>
 	<input type="submit" value="Submit">
 </form><br>
 <br>
 
</body>
</html>