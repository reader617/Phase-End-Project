<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>
<link href="/css/style.css" rel="stylesheet" type = "text/css">
</head>
<body>
 
 Enter the product information to update
 
 <form method="post" action="/update">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 	Product Id: <input type = "number" name = "pId" value = "${product.getPrdId()}" readonly = "readonly"><br>
 	Product Name: <input type="text" name="pName" value = "${product.getPrdName()}"><br>
 	Product Price: <input type="text" name="pPrice" value = "${product.getPrdPrice()}"><br>
 	${error}
 	<input type="submit" value="Submit">
 </form><br>
 <br>
 
</body>
</html>