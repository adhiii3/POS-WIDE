<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	
	
	<h1>Pembayaran</h1>
	<c:if test="${!empty sessionScope.sales.getSaleItem()}">
	<form action="pos.do?action=prosesPembayaranItems" method="post">
		<div class="table-responsive">
				<p> Sale Number : ${sessionScope.sales.getSalesNumber()}</p>
				<p> Nama Kasir : ${sessionScope.sales.getCashier().getName()}</p>
				<p> Sale Date : ${sessionScope.sales.getTransDate()}</p>
			<table id="table-pembayaran" class="table table-striped table-sm">
				
				<thead>
					<tr>
						<th>Item Code</th>
						<th>Description</th>
						<th>Type</th>
						<th>Taxable</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
					</tr>
				</thead>
				<c:forEach items="${sessionScope.sales.getSaleItem()}" var="sale">
					<c:set var="item" value="${sale.getItem()}" />
					<tr>
						<td>${item.getItemCode()}</td>
						<td>${item.getDescription()}</td>
						<td>${item.getType()}</td>
						<td>${item.taxable}</td>
						<td>${item.getPrice()}</td>
						<td>${sale.getQuantity()}</td>
						<td>${sale.getTotalPrice()}</td>
					</tr>
				</c:forEach>
				<tfoot>
					<tr>
						<td colspan="6" style="text-align: center;">Total Harga</td>
						<td><c:out
								value="${sessionScope.sales.getTotalPrice()}"></c:out></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: center;">Tax</td>
						<td><c:out
								value="${sessionScope.sales.getTotalTax()}"></c:out></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: center;">Total Biaya</td>
						<td><c:out
								value="${sessionScope.sales.getTotalPricePlusTotalTax()}"></c:out></td>
						<input type="hidden" name="totalPriceHidden"
							value="${sessionScope.sales.getTotalPricePlusTotalTax()}">
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;">Jenis Pembayaran</td>
						<td style="border: none;"><input type='radio' id='qris'
							name='jenis_pembayaran' value="Qris"><label for='QRIS'> QRIS</label></td>
						<td style="border: none;"><input type='radio'
							class="INPUT-CASH" id='cash' name='jenis_pembayaran' value="Cash"><label
							for='Cash'> CASH </label></td>
						<td style="border: none;"><input type='number'
							id='input-cash' name='input-cash' min='0' disabled="disabled"></td>
					</tr>
					<tr>
						<td colspan="8">
							<button type="submit" class="prosesBayar btn btn-primary  btn-success" style="width: 100%;" disabled="disabled">Bayar</button>
					</tr>
				</tfoot>
			</table>
	</form>
	</c:if>

	<c:if test="${empty sessionScope.sales.getSaleItem()}">
		<h3>Anda Belum Menambahkan Sale Item</h3>
	</c:if>

	<script src="aresource/js/pembayaran.js"></script>
</body>
</html>