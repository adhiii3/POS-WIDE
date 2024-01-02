package com.wide.pos.web.domain;

public class QrisPayment extends Payment{

	
	public QrisPayment(int amount) {
		this.setAmount(amount);
		this.setType("Qris");
	}
	
	
	
}
