 package com.wise.bcms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.bcms.dto.Denomination;
import com.wise.bcms.util.DBUtility;

public class DenominationDAO {

	
	//adding a denomination
	public int add(Denomination denomination,int employeeId) {
    	int status = 0;
		final String QUERY = "insert into denomination(id,employee_id,amount,active) values(?, ?, ?, ?)";
			try(Connection connection = DBUtility.getConnection()){
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
					preparedStatement.setInt(1, denomination.getId());
					preparedStatement.setInt(2, denomination.getEmployee().getId());
					preparedStatement.setDouble(3, denomination.getAmount());
					preparedStatement.setInt(4, denomination.getActive());
					status = preparedStatement.executeUpdate();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		return status;	
	}
	
	
	
	//returns all denominations
	public List<Denomination> get() {
		final String QUERY = "select id, amount from denomination";
    	ResultSet resultSet = null;
    	List<Denomination> list = new ArrayList<Denomination>();
    	PreparedStatement preparedStatement = null;
    	
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
    		resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
			    preparedStatement = connection.prepareStatement(QUERY);
			    Denomination denomination = new Denomination();
			    denomination.setId(resultSet.getInt(1));
				denomination.setAmount(resultSet.getDouble(2));
				list.add(denomination);
			}
    	}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return list;
	}
	

	
	//returns all denominations of an employee
	public List<Denomination> getDenomination(int employeeId) {
		final String QUERY = "select id, amount from denomination where employee_id=?";
    	ResultSet resultSet = null;
    	List<Denomination> list = new ArrayList<Denomination>();
    	PreparedStatement preparedStatement = null;
    	
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
    		preparedStatement.setInt(1, employeeId);
    		resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
			    preparedStatement = connection.prepareStatement(QUERY);
			    Denomination denomination = new Denomination();
			    denomination.setId(resultSet.getInt(1));
				denomination.setAmount(resultSet.getDouble(2));
				list.add(denomination);
			}
    	}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return list;
	}
	
	
	//gets denomination of a coupon
	public Denomination   getDenominationByCoupon(int couponId) {
		final String QUERY = "select d.id, d.amount from denomination d, coupons c where c.denomination_id = d.id and c.coupon_id=?";
    	ResultSet resultSet = null;
    	PreparedStatement preparedStatement = null;
    	Denomination denomination = new Denomination();
    	try(Connection connection = DBUtility.getConnection()) {
    		preparedStatement = connection.prepareStatement(QUERY);
    		preparedStatement.setInt(1, couponId);
    		resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
			    preparedStatement = connection.prepareStatement(QUERY);
			    denomination.setId(resultSet.getInt(1));
				denomination.setAmount(resultSet.getDouble(2));
			}
    	}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DBUtility.close(resultSet,preparedStatement);
		}
		return denomination;
	}
}