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
	<h1>Detail Order Transaksi</h1>
	<div class="table-responsive">
		<form action="#" method="post">
			<table border="1" id="table-pembayaran" class="table table-striped table-sm">
				<tr>
					<th>Item Code</th>
					<th>Description</th>
					<th>Type</th>
					<th>Taxable</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Price</th>
				</tr>
				<c:forEach items="${sales.getSaleItem()}" var="sale">
					<c:set var="item" value="${sale.getItem()}" />
					<tr>
						<td>${item.getItemCode()}</td>
						<td>${item.getDescription()}</td>
						<td>${item.getType()}</td>
						<td>${item.taxable}</td>
						<td>${item.getPrice()}</td>
						<td style="text-align: center;">${sale.getQuantity()}</td>
						<td style="text-align: center;">${sale.getTotalPrice()}</td>
					</tr>
				</c:forEach>
				<tfoot>
					<tr>
						<td colspan="6" style="text-align: center;">Total Harga</td>
						<td style="text-align: center;"><c:out
								value="${sales.getTotalPrice()}"></c:out></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: center;">Tax</td>
						<td style="text-align: center;"><c:out
								value="${sales.getTotalTax()}"></c:out></td>
					</tr>
					<tr>
						<td colspan="6" style="text-align: center;">Total Biaya</td>
						<td style="text-align: center;"><c:out
								value="${sales.getTotalPricePlusTotalTax()}"></c:out></td>
						<input type="hidden" name="totalPriceHidden"
							value="${sales.getTotalPricePlusTotalTax()}">
					</tr>
					<tr>
						<td colspan="6" style="text-align: center;">Jenis Pembayaran</td>
						<td colspan="6" style="text-align: center;">${sales.getPayment().getType()}</td>
					</tr>
					<c:if test="${sales.getPayment().getType() != 'Qris'}">
						<tr>

							<td colspan="6" style="text-align: center;">Uang Yang di
								bayar</td>
							<td colspan="6" style="text-align: center;">${sales.getPayment().getCashInHand()}</td>

						</tr>

						<tr>
							<td colspan="6" style="text-align: center;">Uang Kembalian</td>
							<td colspan="6" style="text-align: center;">${sales.getPayment().change()}</td>
						</tr>

					</c:if>
				</tfoot>
			</table>
		</form>
	</div>
	
</body>
</html>