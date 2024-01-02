package com.wide.pos.web.usecase;

import java.util.List;

import com.wide.pos.web.domain.Cashier;
import com.wide.pos.web.domain.Item;
import com.wide.pos.web.domain.Payment;
import com.wide.pos.web.domain.Sale;
import com.wide.pos.web.domain.SaleItem;
import com.wide.pos.web.exception.RepositoryException;
import com.wide.pos.web.exception.UseCaseSaleException;
import com.wide.pos.web.repository.ItemRepository;
import com.wide.pos.web.repository.SaleRepository;
import com.wide.pos.web.repository.impl.ItemDummyMysqlRepository;
import com.wide.pos.web.repository.impl.SaleDummyMysqlRepository;

public class ProsesUseCaseSale {
	private ItemRepository itemRepository;
	private SaleRepository saleRepository;
	private Sale sale;
	
	public ProsesUseCaseSale() {
		itemRepository = new ItemDummyMysqlRepository();
		saleRepository = new SaleDummyMysqlRepository();
	}
	
	public void createNewSale(Cashier cashier) {
		this.sale = new Sale(cashier);
	}
	
	public void addSaleItem(String itemCode,int quantity) throws UseCaseSaleException {
		Item item1;
		try {
			item1 = itemRepository.findItemById(itemCode);
			SaleItem saleItem1 = new SaleItem(quantity,item1);
			this.sale.addSaleItem(saleItem1);
		} catch (RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}

	}
	
	public void makePayment(Payment payment) {
		this.sale.setPayment(payment);
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public void finishSale() throws UseCaseSaleException {
		try {
			saleRepository.save(this.getSale());
		} catch (RepositoryException e) {
			throw new UseCaseSaleException(e.getMessage());
		}
	}
	
	public void clearSale() {
			this.sale = null;
	}
	
	public void deleteSaleItem(String itemCode) {
		this.sale.deleteSaleItem(itemCode);
	}
	
}
