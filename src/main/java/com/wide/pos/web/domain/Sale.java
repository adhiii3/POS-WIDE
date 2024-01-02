package com.wide.pos.web.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sale {
	private String salesNumber;
	private LocalDate transDate;
	private Cashier cashier;
	private List<SaleItem> saleItem = new ArrayList<>();	
	
	private int totalTax;
	private Payment payment;
	
	
	public Sale(Cashier cashier) {
		super();
		this.salesNumber = UUID.randomUUID().toString();
		this.transDate = transDate.now();
		this.cashier = cashier;
	}

	public Sale(String salesNumber, Cashier cashier, LocalDate transDate) {
		this.salesNumber = salesNumber;
		this.cashier = cashier;
		this.transDate = transDate;
	}
	
	
	public void setSalesNumber(String salesNumber) {
		this.salesNumber = salesNumber;
	}
	
	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}
	
	public void deleteSaleItem(String itemCode) {
		if(!this.saleItem.isEmpty()) {
			for(int i = 0; i < this.saleItem.size(); i++) {
				if(this.saleItem.get(i).getItem().getItemCode().equals(itemCode)) {
					this.saleItem.remove(i);
				}
			}
		}
	}
	
	public void deleteSaleItemsAll() {
		this.saleItem.clear();
	}
	
	
	public void addSaleItem(SaleItem saleItem) {
		boolean isGetting = true;
		if(!this.saleItem.isEmpty()) {
			for(int i = 0; i < this.saleItem.size(); i++) {
				if(saleItem.getItem().getItemCode().equals(this.saleItem.get(i).getItem().getItemCode())) {
					this.saleItem.get(i).setQuantity((saleItem.getQuantity() + this.saleItem.get(i).getQuantity()));
					isGetting = false;
				}
			}
				if(isGetting) {
					this.saleItem.add(saleItem);
					return;
				}
		}else {
			this.saleItem.add(saleItem);
		}
	}
	
	public List<SaleItem> getSaleItem() {
		List<SaleItem> saleItems = this.saleItem.stream().collect(Collectors.toList());
		return saleItems;
	}
	
	public int getTotalPrice() {
		int result = 0;
		for(SaleItem saleItem : this.saleItem) {
			result += saleItem.getTotalPrice();
		}
		return result;
	}
	
	public int getTotalTax() {
		int result = 0;
		for(SaleItem s: this.saleItem) {
			if(s.getItem().isTaxable()) {
				result += s.getItem().getPrice() * Tax.TAX * s.getQuantity();
			}
		}
		return result;
	}
	
	public int getTotalPricePlusTotalTax() {
		return getTotalTax() + getTotalPrice();
	}

	public String getSalesNumber() {
		return salesNumber;
	}

	public LocalDate getTransDate() {
		return transDate;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Cashier getCashier() {
		return new Cashier(
				this.cashier.getNip(),
				this.cashier.getName());
	}
	
	
	
}
