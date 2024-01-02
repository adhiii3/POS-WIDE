$(document).ready(function() {
	$("input[name='jenis_pembayaran']").click(function() { 
	  	$("#input-cash").prop("disabled",true);
		   $('#input-cash').val(""); 
	  	if($(this).hasClass('INPUT-CASH')) {
	      $("#input-cash").prop("disabled",false);
	  	}
	  	 $(".prosesBayar").prop("disabled",false);
	});
	
	$(".prosesBayar").click(function(e) {
		let uangYangHarusDibayar = $('[name="totalPriceHidden"]').val();
		let uangCashDariCustomer = $('[name="input-cash"]').val();
		let jenisPembayaran = $('[name="jenis_pembayaran"]:checked').val();
		if(jenisPembayaran == 'Cash'){
			if(parseInt(uangCashDariCustomer) < parseInt(uangYangHarusDibayar)){
				alert("Jumlah Uang Anda Tidak Mencukupi");	
			}
		}
	});
	
	
	
});