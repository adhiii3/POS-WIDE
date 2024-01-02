package com.wide.pos.web.domain;

import java.util.Objects;

public class Item {
	private String itemCode;
	private int price;
	private String description;
	private String type;
	
	private boolean isTaxable;
	
	public Item(String itemCode, int price, String description, String type, boolean isTaxable) {
		this.itemCode = itemCode;
		this.price = price;
		this.description = description;
		this.type = type;
		this.isTaxable = isTaxable;
	}
	public String getItemCode() {
		return itemCode;
	}
	public int getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public String getType() {
		return type;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isTaxable() {
		return isTaxable;
	}
	public void setTaxable(boolean isTaxable) {
		this.isTaxable = isTaxable;
	}
	@Override
	public int hashCode() {
		return Objects.hash(itemCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(itemCode, other.itemCode);
	}
	
	
	
}
