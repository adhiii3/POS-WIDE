package com.wide.pos.web.service;

import java.util.List;

import com.wide.pos.web.domain.Item;
import com.wide.pos.web.exception.RepositoryException;
import com.wide.pos.web.exception.UseCaseSaleException;
import com.wide.pos.web.repository.ItemRepository;
import com.wide.pos.web.repository.impl.ItemDummyMysqlRepository;

public class ItemService {
	private ItemRepository itemRepository;
	
	public ItemService() {
		itemRepository = new ItemDummyMysqlRepository();
	}
	
	public Item findItemByCode(String itemCode) throws UseCaseSaleException {
		Item item = null;
		try {
			item = itemRepository.findItemById(itemCode);
		} catch (RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		return item;
	}
	
	public List<Item> findAll() throws UseCaseSaleException{
		List<Item> items = null;
		try {
			items = itemRepository.findAll();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			throw new UseCaseSaleException(e.getMessage());
		}
		return items;
	}
	
	
	
	public void saveItem(Item item) throws UseCaseSaleException{
		
		try {
			itemRepository.save(item);
		}catch(RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		
	}
	
	public void updateItem(String itemCode, Item item) throws UseCaseSaleException{
		
		try {
			itemRepository.update(itemCode, item);
		}catch(RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		
	}
	
}
