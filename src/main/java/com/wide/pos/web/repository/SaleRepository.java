package com.wide.pos.web.repository;

import java.util.List;

import com.wide.pos.web.domain.Sale;
import com.wide.pos.web.exception.RepositoryException;

public interface SaleRepository {
	
	public void save(Sale sale) throws RepositoryException;
	public Sale findeBySaleNumber(String saleNumber) throws RepositoryException;
	public List<Sale> findAll() throws RepositoryException;
}
