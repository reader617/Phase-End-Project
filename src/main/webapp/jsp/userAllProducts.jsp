<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
	<form class = "logout" action = "/logout">
		<button>Logout</button>
	</form>
	<table>
	<tr>
	<th>ID </th>
	<th>Name </th>
	<th>Quantity </th>
	<th>Price </th>
	<th>Total </th>
	</tr>
	<tr>
	<c:set var = "total" value = "${0}"/>
  	<c:forEach items="${pList}" var="item">
    	<c:set var = "total" value = "${total + item.calculateTotal()}"/>
    	<tr>
      	<td><c:out value="${item.getPrdId()}" />
      	</td>
      	<td>
      		<c:out value="${item.getPrdName()}"/>
      	</td>
      	<td>
      		<c:out value="${item.getPrdQuantity()}"/>
      	</td>
      	<td>
      		<c:out value="${item.getPrdPrice()}"/>
      	</td>
      	<td>
      		<c:out value="${item.calculateTotal()}"/>
      	</td>
      	<td>
      	<form action="/userUpdate">
      		<input type = "hidden" name="id" value="${item.getPrdId()}">
      		<button>Update</button>
      		</form>
      	</td>
      	<td>
      		<form action="/userDelete">
      		<input type = "hidden" name="id" value="${item.getPrdId()}">
      		<button>Delete</button>
      		</form>
      	</td>
      	
    	</tr>
  	</c:forEach>
  		<tr>
  		<th>Total Price</th>
  		<td></td>
  		<td></td>
  		<td></td>
  		<td>
  		<fmt:formatNumber type = "number" maxFractionDigits="2" value = "${total}"/>
  		<c:out value = "${total}"/>
  		</td>
  		</tr>
	</table>
 
 
</body>
</html>