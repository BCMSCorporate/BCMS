package com.wise.bcms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.bcms.dto.Customer;
import com.wise.bcms.dto.Order;
import com.wise.bcms.util.DBUtility;

public class OrderDAO {
	public int add(Order order) {
		int status = 0;
		final String QUERY = "insert into orders(id, odate, customer_id, status) values(?,?,?,?)";
		try(Connection connection = DBUtility.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,order.getId());
			preparedStatement.setDate(2,DBUtility.utilToSql(order.getDate()));
			preparedStatement.setInt(3,order.getCustomer().getId());
			preparedStatement.setString(4,order.getStatus());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public List<Order> get(int customerId) {
		final String QUERY = "select * from orders where customer_id = '"+customerId+"';";
		ResultSet resultSet =  null;
		List<Order> list = new ArrayList<Order>();
		PreparedStatement preparedStatement = null; 
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				order.setStatus(resultSet.getString(3));
				list.add(order);
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
	public List<Order> get() {
		final String QUERY = "select * from orders";
		ResultSet resultSet =  null;
		List<Order> list = new ArrayList<Order>();
		PreparedStatement preparedStatement = null; 
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				customer.setId(resultSet.getInt(3));
				order.setCustomer(customer);
				order.setStatus(resultSet.getString(4));
				list.add(order);
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
	public Order getOrder(int id) {
		final String QUERY = "select * from orders where id = ?";
		Customer customer = new Customer();
		ResultSet resultSet =  null;
		Order order = new Order();
		PreparedStatement preparedStatement = null; 
		try(Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1,id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				order.setId(resultSet.getInt(1));
				order.setDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				customer.setId(resultSet.getInt(3));
				order.setCustomer(customer);
				order.setStatus(resultSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return order;
	}
}