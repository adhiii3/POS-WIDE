<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Histori Transaksi</h2>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<th>Nama Kasir</th>
					<th>Tanggal Transaksi</th>
					<th>Jenis Pembayaran</th>
					<th>Total Price</th>
					<th>Option</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${historyTransaksi}" var="sale">
					<tr>
						<td>${sale.getCashier().getName()}</td>
						<td>${sale.getTransDate()}</td>
						<td>${sale.getPayment().getType()}</td>
						<td>${sale.getPayment().getAmount()}</td>
						<td>
							 <a href="pos.do?action=detail-history-transaksi&id=${sale.getSalesNumber()}">
	    						<button type="button" class="btn btn-primary btn-sm">Lihat Detail</button>
	  						</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


</body>
</html>