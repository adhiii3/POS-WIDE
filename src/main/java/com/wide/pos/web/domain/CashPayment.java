package com.wide.pos.web.domain;

public class CashPayment extends Payment{
	private int cashInHand;
	
	public CashPayment(int cashInhand, int amount) {
		this.cashInHand = cashInhand;
		this.setAmount(amount);
		this.setType("Cash");
	}
	
	public int change() {
		return this.cashInHand - getAmount();
	}

	public int getCashInHand() {
		return cashInHand;
	}

	public void setCashInHand(int cashInHand) {
		this.cashInHand = cashInHand;
	}
	
	
}
