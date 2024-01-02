<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Crud</title>
</head>
<body>
	<h2>List Items</h2>
	 <a href="pos.do?action=form-new-item">
    	<button type="button" class="btn btn-primary">Add New Item</button>
  	  </a>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<th>Item Code</th>
					<th>Price</th>
					<th>Description</th>
					<th>Type</th>
					<th>Taxable</th>
					<th>Option</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.itemCode}</td>
						<td>${item.price}</td>
						<td>${item.description}</td>
						<td>${item.type}</td>
						<td>${item.taxable}</td>
						<td>
							<a href = "pos.do?action=form-update&id=${item.itemCode}">
								<button type="button" class="btn btn-primary btn-sm">Update</button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>