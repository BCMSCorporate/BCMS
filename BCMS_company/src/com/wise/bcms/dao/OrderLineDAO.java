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

public class OrderLineDAO {
	public int add(OrderLine orderLine) {
		int status = 0;
		final String QUERY = "insert into orders_line(qty, products_id, orders_id, price) values(?,?,?,?)";
		try(Connection connection = DBUtility.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,orderLine.getQty());
			preparedStatement.setInt(2,orderLine.getProduct().getId());
			preparedStatement.setInt(3,orderLine.getOrder().getId());
			preparedStatement.setDouble(4,orderLine.getProduct().getPrice() * orderLine.getQty());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public List<OrderLine> get(int customerId) {
		final String QUERY = "select o.id, o.odate, p.id, p.name, p.picture, ol.qty, ol.price from orders o, order_line ol , products p where ol.order_id = o.order_id and p.product_id = ol.product_id and o.customer_id = '"+customerId+"' and o.status is null";
		ResultSet resultSet =  null;
		List<OrderLine> list = new ArrayList<OrderLine>();
		PreparedStatement preparedStatement = null; 
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				OrderLine orderLine = new OrderLine();
				Order order = new Order();
				Product product = new Product();
				order.setId(resultSet.getInt(1));
				order.setDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				orderLine.setOrder(order);
				product.setId(resultSet.getInt(3));
				product.setName(resultSet.getString(4));
				product.setPicture(resultSet.getString(5));
				orderLine.setProduct(product);
				orderLine.setQty(resultSet.getInt(6));
				orderLine.setTotalPrice(resultSet.getDouble(7));
				list.add(orderLine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return list;
		
		
		}
}