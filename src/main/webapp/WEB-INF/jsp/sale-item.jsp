<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sale Item</title>
</head>
<body>

<table border="1">
	<tr>
		<th>Item Code</th>
		<th>Description</th>
		<th>Type</th>
		<th>Price</th>
		<th>Quantity</th>
		<th>TOTAL PRICE</th>
		<th>Option</th>
	</tr>
	<c:forEach items="${sales.getSaleItem()}" var="sale">
    	<c:set var = "item" value ="${sale.getItem()}" />
    	<tr>
    		<td>${item.getItemCode()}</td>
			<td>${item.getDescription()}</td>
			<td>${item.getType()}</td>
			<td>${item.getPrice()}</td>
			<td>${sale.getQuantity()}</td>
			<td>${sale.getTotalPrice()}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>