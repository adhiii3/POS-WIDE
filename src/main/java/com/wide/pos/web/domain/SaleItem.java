package com.wide.pos.web.domain;

import java.util.Objects;

public class SaleItem {
	
	private int quantity;
	private Item item;
	
	public SaleItem(int quantity, Item item) {
		this.quantity = quantity;
		this.item = item;
	}
	
	public int getTotalPrice() {
		return this.item.getPrice() * this.getQuantity();
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return new Item(
				this.item.getItemCode(),
				this.item.getPrice(),
				this.item.getDescription(),
				this.item.getType(),
				this.item.isTaxable());
	}
	
}
