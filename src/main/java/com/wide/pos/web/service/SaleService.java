package com.wide.pos.web.service;

import java.util.List;

import com.wide.pos.web.domain.Sale;
import com.wide.pos.web.exception.RepositoryException;
import com.wide.pos.web.exception.UseCaseSaleException;
import com.wide.pos.web.repository.SaleRepository;
import com.wide.pos.web.repository.impl.SaleDummyMysqlRepository;

public class SaleService {
	private SaleRepository saleRepository;
	
	public SaleService(){
		saleRepository = new SaleDummyMysqlRepository();
	}
	
	public Sale findSaleBySaleNumber(String salesNumber) throws UseCaseSaleException {
		Sale sale = null;
		try {
			sale = saleRepository.findeBySaleNumber(salesNumber);
		} catch (RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		return sale;
	}
	
	public List<Sale> findAllSales() throws UseCaseSaleException{
		List<Sale> sales = null;
		try {
			sales = saleRepository.findAll();
		} catch (RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
		return sales;
	}
}
