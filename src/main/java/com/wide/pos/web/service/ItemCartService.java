package com.wide.pos.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.wide.pos.web.domain.Item;

public class ItemCartService {
	private Map<String,Item> itemCart = new HashMap<>();
	private Map<String,Item> itemCartDetailItem = new HashMap<>();
	
	public void addCart(Item item) {
		this.itemCart.put(item.getItemCode(), item);
	}
	
	public Map<String,Item> getItemCart(){
		return this.itemCart;
	}
	
	public void removeItemById(String itemCode) {
		this.itemCart.remove(itemCode);
	}
	
	public void addCartItemDetail(Item item) {
		if(this.itemCartDetailItem.size() == 0) {
			this.itemCartDetailItem.put(item.getItemCode(), item);
		}else {
			this.itemCartDetailItem.clear();
			this.itemCartDetailItem.put(item.getItemCode(), item);
		}

	}
	
	public void clearCartItemDetail() {
		this.itemCartDetailItem.clear();
	}
	
	public Map<String,Item> getItemCartDetail(){
		return this.itemCartDetailItem;
	}
	
	public void removeItemDetailById(String itemCode) {
		this.itemCartDetailItem.remove(itemCode);
	}
	
	
	
}
