package com.wise.bcms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.bcms.dto.Order;
import com.wise.bcms.dto.OrderLine;
import com.wise.bcms.dto.Product;
import com.wise.bcms.util.DBUtility;

public class ProductDAO {
	
	
	//adding products
	public int add(Product product) {
		int status = 0;
		final String QUERY = "insert into products(id,name,desp,price,picture)values(?,?,?,?,?)";
		try(Connection connection = DBUtility.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2,product.getName());
			preparedStatement.setString(3,product.getDescription());
			preparedStatement.setDouble(4,product.getPrice());
			preparedStatement.setString(5, product.getPicture());
			status = preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return status;
		
	}
	
	//returns product based on product id
	public Product get(int productId) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Product product = new Product();
		final String QUERY = "select id, name, desp, price from products where id = ?";
		try(Connection connection = DBUtility.getConnection()){
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, productId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				product.setId(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setDescription(resultSet.getString(3));
				product.setPrice(resultSet.getFloat(4));
				product.setPicture(resultSet.getString(5));
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}

		return product;
	}
	
	
	//returns list of products
	public List<Product> get(){
		final String QUERY = "select id, name, desp, price from products";
    	ResultSet resultSet = null;
    	List<Product> list = new ArrayList<Product>();
    	PreparedStatement preparedStatement = null;
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
    		resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setDescription(resultSet.getString(3));
				product.setPrice(resultSet.getFloat(4));
				product.setPicture(resultSet.getString(5));
				list.add(product);
			}
    	}catch(SQLException exception) {
    		exception.printStackTrace();
    	}
    	finally {
    		DBUtility.close(preparedStatement,resultSet);
    	}
		return list;	
	}
	public List<OrderLine> getProduct(int orderId) {
		final String QUERY = "select o.odate,ol.product_id, ol.price, p.name, p.picture from orders o, order_line ol, products p where ol.orders_id = o.id and p.id = ol.products_id and o.id = ?";
		ResultSet resultSet = null;
    	List<OrderLine> list = new ArrayList<OrderLine>();
    	PreparedStatement preparedStatement = null;
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
    		resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				Order order = new Order();
				OrderLine orderLine = new OrderLine();
				order.setDate(DBUtility.sqlToUtil(resultSet.getDate(1)));
				product.setId(resultSet.getInt(2));
				orderLine.setTotalPrice(resultSet.getDouble(3));
				product.setName(resultSet.getString(4));
				product.setPicture(resultSet.getString(5));
				orderLine.setOrder(order);
				orderLine.setProduct(product);
				list.add(orderLine);
			}
		}catch(SQLException exception) {
	   		exception.printStackTrace();
	   	}
	   	finally {
	   		DBUtility.close(preparedStatement,resultSet);
    	}
    	return list;
	}
}
