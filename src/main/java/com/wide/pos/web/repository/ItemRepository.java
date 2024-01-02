package com.wide.pos.web.repository;

import java.util.List;
import java.util.Map;

import com.wide.pos.web.domain.Item;
import com.wide.pos.web.exception.RepositoryException;


public interface ItemRepository {

	public Item findItemById(String itemCode) throws RepositoryException;
	
	public List<Item> findAll() throws RepositoryException;
	
	public void save(Item item) throws RepositoryException;
	
	public void update(String itemCode, Item item) throws RepositoryException;
}
