package com.wide.pos.web.domain;

import java.util.UUID;

public abstract class Payment {
	private int amount;
	private String type;
	private String paymentId;
	
	public String getPaymentId() {
		return UUID.randomUUID().toString();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public boolean validate() {
		return true;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
