<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Update Item</title>
</head>
<body>
<h1>Update Item <c:out>${item.itemCode}</c:out></h1>

<form action="pos.do?action=update_item" method="post" >
		<div class="mb-3">
			<label for="inputItemCode" class="form-label">Item Code</label> <input
				type="text" name='itemCode' class="form-control" id="inputItemCode"
				aria-describedby="itemCode" value="${item.itemCode}" readonly>
		</div>
		<div class="mb-3">
			<label for="inputPrice" class="form-label">Price</label> <input
				type="number" name='price' class="form-control" id="inputPrice" value="${item.price}" required>
		</div>
		<div class="mb-3">
			<label for="inputDescription" class="form-label">Description</label>
			<div class="input-group">
				<input type="text" name='description' class="form-control"
					id="inputDescription" value="${item.description}" required>
    		</div>
		</div>
		<div class="mb-3">
			<label for="inputType" class="form-label">Type</label> 
			<input type="text" name='type' class="form-control" id="inputType" value="${item.type}" required>
		</div>
		<div class="mb-3">
			<label class="form-label">Taxable </label>
			<input type='radio' id='yes' name='isTax' value=true ${item.taxable == 'true' ?  'checked':''}> 
			<label for='yes' class="form-label">Yes</label>
			<input type='radio' id='no' name='isTax' value=false ${item.taxable == 'false' ?  'checked' : ''}> 
			<label for='no' class="form-label">No</label>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</body>
</html>