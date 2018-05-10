package com.wise.bcms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.bcms.dto.CouponReq;
import com.wise.bcms.dto.Customer;
import com.wise.bcms.util.DBUtility;

public class CouponReqDAO {
	public int add(CouponReq couponReq) {
		int status = 0;
		final String QUERY = "insert into coupon_reqs(customer_id, amount, month, year) values(?, ?, ?, ?)";
		try(Connection connection = DBUtility.getConnection()){
			
		System.out.println(couponReq.getCustomer().getId()+"  df "+couponReq.getAmount());
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, couponReq.getCustomer().getId());
			preparedStatement.setDouble(2, couponReq.getAmount());
			preparedStatement.setString(3, couponReq.getMonth());
			preparedStatement.setInt(4, couponReq.getYear());
			status = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	// returns all the coupon requests
	public List<CouponReq> get() {
		List<CouponReq> list = new ArrayList<CouponReq>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select * from coupon_reqs";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				CouponReq couponReq = new CouponReq();
				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				couponReq.setCustomer(customer);
				couponReq.setAmount(resultSet.getDouble(2));
				couponReq.setMonth(resultSet.getString(3));
				couponReq.setYear(resultSet.getInt(4));
				list.add(couponReq);
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
	
	
	// returns coupon requests of a particular customer
	public List<CouponReq> get(int companyId) {
		List<CouponReq> list = new ArrayList<CouponReq>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select cr.customer_id, cr.amount, cr.month, cr.year from coupon_reqs cr, customer c where c.id = cr.customer_id and c.company_id = ?";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, companyId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				CouponReq couponReq = new CouponReq();
				Customer customer = new Customer();
				customer.setId(resultSet.getInt(1));
				couponReq.setCustomer(customer);
				couponReq.setAmount(resultSet.getDouble(2));
				couponReq.setMonth(resultSet.getString(3));
				couponReq.setYear(resultSet.getInt(4));
				list.add(couponReq);
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
}