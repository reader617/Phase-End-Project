<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin All Products</title>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>

<h3> Welcome 
<security:authorize access="isAuthenticated()">
<security:authentication property="principal.username" /> 
</security:authorize>
</h3>

<form class = "logout" action = "/logout">
		<button>Logout</button>
	</form>
	<form class= "add" action="/addProduct">
		<button>Add Product</button>
	</form>
	<table>
	<tr>
	<th>ID </th>
	<th>Name </th>
	<th>Price </th>
	<th>Total </th>
	</tr>
	<tr>
  	<c:forEach items="${pList}" var="item">
    	<c:set var = "total" value = "${total + item.calculateTotal()}"/>
    	<tr>
      	<td><c:out value="${item.getPrdId()}" />
      	</td>
      	<td>
      		<c:out value="${item.getPrdName()}"/>
      	</td>
      	<td>
      		<c:out value="${item.getPrdPrice()}"/>
      	</td>
      	<td>
      	<form action="/update">
      		<input type = "hidden" name="id" value="${item.getPrdId()}">
      		<button>Update</button>
      		</form>
      	</td>
      	<td>
      		<form action="/delete">
      		<input type = "hidden" name="id" value="${item.getPrdId()}">
      		<button>Delete</button>
      		</form>
      	</td>
      	
    	</tr>
  	</c:forEach>
	</table>
 
 
</body>
</html>