package com.wise.bcms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.bcms.dto.Customer;
import com.wise.bcms.util.DBUtility;


public class CustomerDAO {
	public int add(Customer customer) {		
		int status = 0;
		final String QUERY = "insert into customer(id, name, surname, dob, gender, email, password, phone_no, company_id, house_no, location, city, state, country, pincode) values(?, ?, ?, ?, ?, ?, ?, ? ,? ,?, ?, ?, ?, ?, ?)";
		try(Connection connection = DBUtility.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getSurname());
			preparedStatement.setDate(4, DBUtility.utilToSql(customer.getDob()));
			preparedStatement.setString(5, customer.getGender());
			preparedStatement.setString(6, customer.getEmail());
			preparedStatement.setString(7, customer.getPassword());
			preparedStatement.setString(8, customer.getPhone());
			preparedStatement.setInt(9, customer.getCompany().getId());
			preparedStatement.setString(10, customer.getHouse());
			preparedStatement.setString(11, customer.getLocation());
			preparedStatement.setString(12, customer.getCity());
			preparedStatement.setString(13, customer.getState());
			preparedStatement.setString(14, customer.getCountry());
			preparedStatement.setInt(15, customer.getPincode());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//return customer of a particular id
	public Customer get(int customerId){	
		ResultSet resultSet = null;
		Customer customer = new Customer();
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from customer where id = ?";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(resultSet.getInt(1));
				customer.setName(resultSet.getString(2));
				customer.setSurname(resultSet.getString(3));
				customer.setDob(DBUtility.sqlToUtil(resultSet.getDate(4)));
				customer.setGender(resultSet.getString(5));
				customer.setEmail(resultSet.getString(6));
				customer.setPassword(resultSet.getString(7));
				customer.setPhone(resultSet.getString(8));
				customer.setHouse(resultSet.getString(10));
				customer.setLocation(resultSet.getString(11));
				customer.setCity(resultSet.getString(12));
				customer.setState(resultSet.getString(13));
				customer.setCountry(resultSet.getString(14));
				customer.setPincode(resultSet.getInt(15));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet, preparedStatement);
		}
		return customer;
	}
	
	// returns all the customers
	public List<Customer> get() {
		List<Customer> list = new ArrayList<Customer>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from customer";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				customer.setName(resultSet.getString(2));
				customer.setSurname(resultSet.getString(3));
				customer.setDob(DBUtility.sqlToUtil(resultSet.getDate(4)));
				customer.setGender(resultSet.getString(5));
				customer.setEmail(resultSet.getString(6));
				customer.setPassword(resultSet.getString(7));
				customer.setPhone(resultSet.getString(8));
				customer.setHouse(resultSet.getString(10));
				customer.setLocation(resultSet.getString(11));
				customer.setCity(resultSet.getString(12));
				customer.setState(resultSet.getString(13));
				customer.setCountry(resultSet.getString(14));
				customer.setPincode(resultSet.getInt(15));
				list.add(customer);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			DBUtility.close(resultSet, preparedStatement);
		}
		return list;
	}
	
	public int update(int customerId, String password) {
		int status = 0;
		final String QUERY = "update customer set password = ? where id = ?";
		try(Connection connection = DBUtility.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, customerId);
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
