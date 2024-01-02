$(document).ready(function() {
	
	
	$('.link-act li a').each(function () {
        if ($(this).prop('href') == window.location.href) {
            $(this).addClass('active');
        }
    });
	
	let arr = [];
	let i = 0;
	$("#cart_table > tbody > .tr").each(function () {
		item = {
				code : $(this).find('td').eq(0).text(),
				price :$(this).find('td').eq(1).text()
		}
		
		arr[i] = item;

		console.log($(this).find('td').eq(0).text() +" "+ $(this).find('td').eq(1).text() );
	
		i++;
	});
	
	$("#quantity_detail_item").on('change keyup', function() {
		let quantity = document.getElementsByName("quantity_detail_item")[0].value;
		let price = document.getElementsByName("price_detail_item")[0].value;
		let total = quantity * price;
		$("#price_detail").val(total);
    });
	
	arr.forEach((item, index) => {
		$("."+item.code+"number").on('change keyup', function() {
			let quantity = document.getElementsByName(item.code)[0].value;
			let price = item.price;
			document.getElementById(item.code+"price").innerHTML = quantity * price;
	    });
	});
	
	 $('.cancelSaleItem').click(function() {
	        $('.div-sale-item').hide();
	    });
	
	$(".addToCart").click(function(e) {
		e.preventDefault();
		$('.div-sale-item').show();
			var id = this.id; 
			$.ajax({
			url: "pos.do",
			type: "post",
			data: {
				  id : id,
				  action: "addToCart"
			      },
			success : function(data){
				 location.reload();
	        }
		});
	});
	
	$(".addSaleItem").click(function(e) {
		var itemCode = $("#itemCode_saleItem").val();
		var quantity = $("#quantity_saleItem").val();
		$.ajax({
			url: "pos.do",
			type: "post",
			data: {
				  itemCode : itemCode,
				  quantity : quantity,
				  action: "addSaleItem"
			      },
			success : function(data){
				   location.reload();
	        }
		});
	});
	
	
	$(".deleteItemCart").click(function() {
		var id = this.id; 
		$.ajax({
		url: "pos.do",
		type: "post",
		data: {
			  id : id,
			  action:"deleteItemCart"
		      },
		success : function(data){
		   location.reload(); 
        }
		});
	});
});