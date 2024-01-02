package com.wide.pos.web.repository.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wide.pos.web.connection.DataSource;
import com.wide.pos.web.domain.Item;
import com.wide.pos.web.exception.RepositoryException;
import com.wide.pos.web.repository.ItemRepository;



public class ItemDummyMysqlRepository implements ItemRepository{

	@Override
	public List<Item> findAll() throws RepositoryException {
		final String SQL_QUERY = "SELECT * FROM item";
		
		Item item = null;
		Connection conn = null;
		List<Item> items = new ArrayList<>();
		try {
			conn = DataSource.getDataSource();
			PreparedStatement ps = conn.prepareStatement(SQL_QUERY);
			ResultSet rs= ps.executeQuery(); 
			while(rs.next()) {
				items.add(new Item(rs.getString("item_code"),
						rs.getInt("price"),
						rs.getString("description"),
						rs.getString("type"),
						rs.getBoolean("is_taxable")
						));
			}
		}catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return items;
	}

	@Override
	public void save(Item item) throws RepositoryException {
		try (Connection connection = DataSource.getDataSource()){
			connection.setAutoCommit(false);
			String SQL_ITEM_SAVE = "INSERT INTO item (item_code,price,description,type,is_taxable) VALUES (?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(SQL_ITEM_SAVE);
			
			ps.setString(1, item.getItemCode());
			ps.setInt(2, item.getPrice());
			ps.setString(3, item.getDescription());
			ps.setString(4, item.getType());
			ps.setBoolean(5, item.isTaxable());
			
			int result = ps.executeUpdate();
				
			if(result > 0) {
				System.out.println("BERHASIL SIMPAN DATA ITEM");
			}
			
			connection.commit();
		}catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} 
		catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}
		
	}
	
	@Override
	public Item findItemById(String itemCode) throws RepositoryException {
			final String SQL_QUERY = "SELECT * FROM item WHERE item_code = ?";
			
			Item item = null;
			Connection conn = null;
		
			try {
				conn = DataSource.getDataSource();
				PreparedStatement ps = conn.prepareStatement(SQL_QUERY);
				ps.setString(1, itemCode);
				ResultSet rs= ps.executeQuery(); 
				while(rs.next()) {
					item = new Item(rs.getString("item_code"),
							rs.getInt("price"),
							rs.getString("description"),
							rs.getString("type"),
							rs.getBoolean("is_taxable")
							);
				}	
			}catch (SQLException e) {
				throw new RepositoryException(e.getMessage());
			}catch (Exception e) {
				throw new RepositoryException(e.getMessage());
			}finally {
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		return item;	
	}



	@Override
	public void update(String itemCode, Item item) throws RepositoryException {
		try (Connection connection = DataSource.getDataSource()){
			connection.setAutoCommit(false);
			String SQL_ITEM_UPDATE = "UPDATE item SET price = ?, description = ?, type = ?, is_taxable=? WHERE item_code = ?";
			
			PreparedStatement ps = connection.prepareStatement(SQL_ITEM_UPDATE);
			
			
			ps.setInt(1, item.getPrice());
			ps.setString(2, item.getDescription());
			ps.setString(3, item.getType());
			ps.setBoolean(4, item.isTaxable());
			ps.setString(5, item.getItemCode());
			
			int result = ps.executeUpdate();
				
			if(result > 0) {
				System.out.println("BERHASIL UPDATE DATA ITEM");
			}
			
			connection.commit();
		}catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} 
		catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}
		
	}

	@Override
	public void delete(String itemCode) throws RepositoryException {
		
		try (Connection connection = DataSource.getDataSource()){
			connection.setAutoCommit(false);
			String SQL_ITEM_UPDATE = "DELETE  FROM item WHERE item_code = ?";
			
			PreparedStatement ps = connection.prepareStatement(SQL_ITEM_UPDATE);
			ps.setString(1, itemCode);
			int result = ps.executeUpdate();
			
			connection.commit();
		}catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		} 
		catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		}
		
	}


}
