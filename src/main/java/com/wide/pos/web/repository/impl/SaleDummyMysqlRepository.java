package com.wide.pos.web.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wide.pos.web.connection.DataSource;
import com.wide.pos.web.domain.CashPayment;
import com.wide.pos.web.domain.Cashier;
import com.wide.pos.web.domain.Item;
import com.wide.pos.web.domain.Payment;
import com.wide.pos.web.domain.QrisPayment;
import com.wide.pos.web.domain.Sale;
import com.wide.pos.web.domain.SaleItem;
import com.wide.pos.web.exception.RepositoryException;
import com.wide.pos.web.repository.SaleRepository;

public class SaleDummyMysqlRepository implements SaleRepository {

	@Override
	public void save(Sale sale) throws RepositoryException {

		try (Connection connection = DataSource.getDataSource()) {
			connection.setAutoCommit(false);
			String SQL_SALE_SAVE = "INSERT INTO sale (sale_id,trans_date,cashier_id,total_price,payment_id,tax) VALUES (?,?,?,?,?,?)";

			PreparedStatement ps = connection.prepareStatement(SQL_SALE_SAVE);
			String paymentID = sale.getPayment().getPaymentId();

			ps.setString(1, sale.getSalesNumber());
			ps.setString(2, sale.getTransDate().toString());
			ps.setString(3, sale.getCashier().getNip());
			ps.setInt(4, sale.getTotalPricePlusTotalTax());
			ps.setString(5, paymentID);
			ps.setInt(6, sale.getTotalTax());

			int result = ps.executeUpdate();

			String SQL_SALE_ITEM_SAVE = "INSERT INTO sale_item (quantity,sale_id,item_code,total_price) VALUES(?,?,?,?)";
			PreparedStatement ps2 = connection.prepareStatement(SQL_SALE_ITEM_SAVE);

			int result2 = 0;
			for (SaleItem saleItem : sale.getSaleItem()) {
				ps2.setInt(1, saleItem.getQuantity());
				ps2.setString(2, sale.getSalesNumber());
				ps2.setString(3, saleItem.getItem().getItemCode());
				ps2.setInt(4, saleItem.getTotalPrice());
				result2 = ps2.executeUpdate();
			}

			String SQL_PAYMENT_SAVE = "INSERT INTO payment (payment_id,type,amount,uang_pembayaran) VALUES(?,?,?,?)";
			PreparedStatement ps3 = connection.prepareStatement(SQL_PAYMENT_SAVE);
			int result3 = 0;

			ps3.setString(1, paymentID);
			ps3.setString(2, sale.getPayment().getType());
			ps3.setInt(3, sale.getPayment().getAmount());

			if (sale.getPayment() instanceof CashPayment) {
				ps3.setInt(4, ((CashPayment) sale.getPayment()).getCashInHand());
			} else {
				ps3.setInt(4, sale.getPayment().getAmount());
			}

			result3 = ps3.executeUpdate();

			connection.commit();
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	@Override
	public Sale findeBySaleNumber(String saleNumber) throws RepositoryException {
		StringBuilder sqlFindBySaleNumber = new StringBuilder();

		sqlFindBySaleNumber.append("SELECT ");
		sqlFindBySaleNumber.append("item.item_code, item.description, item.type, item.price, item.is_taxable, ");
		sqlFindBySaleNumber.append("sale_item.quantity, sale_item.total_price, ");
		sqlFindBySaleNumber.append("sale.trans_date, sale.total_price AS total_price_sale, sale.tax, sale.sale_id, ");
		sqlFindBySaleNumber.append("cashier.cashier_id, cashier.name, ");
		sqlFindBySaleNumber
				.append("payment.payment_id, payment.type AS payment_type, payment.amount, payment.uang_pembayaran ");
		sqlFindBySaleNumber.append("FROM item INNER JOIN sale_item ON item.item_code = sale_item.item_code ");
		sqlFindBySaleNumber.append("INNER JOIN sale ON sale_item.sale_id = sale.sale_id ");
		sqlFindBySaleNumber.append("INNER JOIN cashier ON cashier.cashier_id = sale.cashier_id ");
		sqlFindBySaleNumber.append("INNER JOIN payment ON payment.payment_id = sale.payment_id ");
		sqlFindBySaleNumber.append("WHERE sale.sale_id = ?");

		Cashier cashier = null;
		Sale sale = null;
		Payment payment = null;

		try (Connection connection = DataSource.getDataSource()) {

			connection.setAutoCommit(false);

			PreparedStatement ps = connection.prepareStatement(sqlFindBySaleNumber.toString());
			ps.setString(1, saleNumber);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (cashier == null && sale == null) {
					cashier = new Cashier(rs.getString("cashier_id"), rs.getString("name"));
					sale = new Sale(rs.getString("sale_id"), cashier, LocalDate.parse(rs.getString("trans_date")));
				}

				Item item = new Item(rs.getString("item_code"), rs.getInt("price"), rs.getString("description"),
						rs.getString("type"), rs.getBoolean("is_taxable"));

				SaleItem saleItem = new SaleItem(rs.getInt("quantity"), item);

				sale.addSaleItem(saleItem);

				if (sale.getPayment() == null) {
					if (rs.getString("payment_type").equalsIgnoreCase("qris")) {
						sale.setPayment(new QrisPayment(rs.getInt("amount")));
					} else {
						sale.setPayment(new CashPayment(rs.getInt("uang_pembayaran"), rs.getInt("amount")));
					}
				}
			}

			connection.commit();
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}
		return sale;
	}

	@Override
	public List<Sale> findAll() throws RepositoryException {
		List<Sale> sales = new ArrayList<>();
		
		String sqlFindAll = "SELECT sale.sale_id, sale.trans_date, sale.cashier_id, sale.total_price, sale.payment_id,sale.tax, cashier.cashier_id, cashier.name,\r\n"
				+ "    payment.payment_id, payment.type AS payment_type, payment.amount, payment.uang_pembayaran\r\n"
				+ "    FROM sale LEFT JOIN cashier ON sale.cashier_id = cashier.cashier_id LEFT JOIN payment ON payment.payment_id = sale.payment_id";


		try (Connection connection = DataSource.getDataSource()) {

			connection.setAutoCommit(false);

			PreparedStatement ps = connection.prepareStatement(sqlFindAll.toString());
			ResultSet rs = ps.executeQuery();

			Sale sale = null;
			while (rs.next()) {

				Cashier	cashier = new Cashier(rs.getString("cashier_id"), rs.getString("name"));
				sale = new Sale(rs.getString("sale_id"), cashier, LocalDate.parse(rs.getString("trans_date")));
				

//				Item item = new Item(rs.getString("item_code"), rs.getInt("price"), rs.getString("description"),
//						rs.getString("type"), rs.getBoolean("is_taxable"));

//				SaleItem saleItem = new SaleItem(rs.getInt("quantity"), item);
//
//				sale.addSaleItem(saleItem);

				if (sale.getPayment() == null) {
					if (rs.getString("payment_type").equalsIgnoreCase("qris")) {
						sale.setPayment(new QrisPayment(rs.getInt("amount")));
					} else {
						sale.setPayment(new CashPayment(rs.getInt("uang_pembayaran"), rs.getInt("amount")));
					}
				}
				sales.add(sale);
			}

			connection.commit();
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}
		return sales;
	}

}
