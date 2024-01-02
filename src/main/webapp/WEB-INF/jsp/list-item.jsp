<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Items</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<h2>List Items</h2>
	
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
							<button type="button" id=<c:out value="${item.itemCode}"></c:out>
								class="addToCart btn btn-primary btn-sm">Add Cart</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="div-sale-item">
			<c:forEach items="${sessionScope.itemCartDetail.values()}" var="item">
				<h2>Sale Item</h2>
				<form>
					<div class="mb-3 row">
						<label for="staticItemCode" class="col-sm-2 col-form-label">Item
							Code</label>
						<div class="col-sm-10">
							<input type="text" readonly
								class="form-control-plaintext form-control-sm"
								id="itemCode_saleItem" name='itemCode' value="${item.itemCode}">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="staticDescription" class="col-sm-2 col-form-label">Description</label>
						<div class="col-sm-10">
							<input type="text" readonly
								class="form-control-plaintext form-control-sm"
								id="description_saleItem" name='description'
								value="${item.description}">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="staticType" class="col-sm-2 col-form-label">Type</label>
						<div class="col-sm-10">
							<input type="text" readonly
								class="form-control-plaintext form-control-sm"
								id="type_saleItem" name='type' value="${item.type}">
						</div>
					</div>

					<div class="mb-3 row">
						<label for="staticTaxable" class="col-sm-2 col-form-label">Taxable</label>
						<div class="col-sm-10">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="isTax"
									id="yes" value=true ${item.taxable == 'true' ?  'checked':''}
									onclick="return false;"> <label
									class="form-check-label" for="yes">Yes</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="isTax"
									id="no" value=false ${item.taxable == 'false' ?  'checked':''}
									onclick="return false;"> <label
									class="form-check-label" for="no">No</label>
							</div>
						</div>
					</div>
					<div class="mb-3 row">
						<label for="staticQuantity" class="col-sm-2 col-form-label">Quantity</label>
						<div class="col-sm-10">
							<input type='number' class="form-control form-control-sm"
								id="quantity_saleItem" name="quantity_detail_item"
								id="quantity_detail_item" value=1 min="1">
						</div>
					</div>


					<div class="mb-3 row">
						<label for="staticPrice" class="col-sm-2 col-form-label">Price</label>
						<input type="hidden" value="${item.price}"
							name="price_detail_item">
						<div class="col-sm-10">
							<input type='text' class="form-control-plaintext form-control-sm"
								name='price_detail' id="price_detail" value="${item.price}"
								readonly>
						</div>
					</div>

					<div class="mb-3 row">
						<div class="col-sm-12">
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<button type="button"
									class="addSaleItem btn btn-primary me-md-2">Add</button>
								<button type="button"
									class="cancelSaleItem btn btn-primary me-md-2">Cancel</button>
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
		</div>

		<c:if test="${!empty sessionScope.sales.getSaleItem()}">
			<h1>CART</h1>
			<form>
				<table border="1" id="cart_table"
					class="table table-striped table-sm">
					<tr>
						<th>Item Code</th>
						<th>Description</th>
						<th>Type</th>
						<th>Taxable</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
						<th>Option</th>
					</tr>
					<c:forEach items="${sessionScope.sales.getSaleItem()}" var="sale">
						<c:set var="item" value="${sale.getItem()}" />
						<tr>
							<td>${item.getItemCode()}</td>
							<td>${item.getDescription()}</td>
							<td>${item.getType()}</td>
							<td>${item.taxable}</td>
							<td style="text-align: center;">${item.getPrice()}</td>
							<td style="text-align: center;">${sale.getQuantity()}</td>
							<td style="text-align: center;">${sale.getTotalPrice()}</td>
							<td>
								<button type="button"
									id=<c:out value="${item.itemCode}"></c:out>
									class="deleteItemCart btn btn-danger  btn-sm">Delete</button>
							</td>
						</tr>
					</c:forEach>
					<tfoot>

						<tr>
							<td colspan="6" style="text-align: center; border: none;">Total
								Harga</td>
							<td style="text-align: center; border: none;"><c:out
									value="${sessionScope.sales.getTotalPrice()}"></c:out></td>
						</tr>
						<tr>
							<td colspan="8"><a href="pos.do?action=pembayaran">
									<button type="button" class="btn btn-success btn-sm"
										style="width: 100%;">Bayar</button>
							</a></td>
						</tr>

					</tfoot>
				</table>
			</form>
		</c:if>

	</div>
</body>
</html>