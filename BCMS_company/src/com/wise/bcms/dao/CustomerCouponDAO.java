package com.wise.bcms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wise.bcms.dto.Coupon;
import com.wise.bcms.dto.Customer;
import com.wise.bcms.dto.CustomerCoupon;
import com.wise.bcms.dto.Denomination;
import com.wise.bcms.util.DBUtility;

public class CustomerCouponDAO {
	public int add(CustomerCoupon customerCoupon) {
			int status = 0;
			final String QUERY = "insert into customer_coupons(coupon_id, customer_id, valid_date, month, year) values(?, ?, ?, ?, ?)";
			try(Connection connection = DBUtility.getConnection()){
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
				preparedStatement.setDouble(1, customerCoupon.getCoupon().getId());
				preparedStatement.setInt(2, customerCoupon.getCustomer().getId());
				preparedStatement.setDate(3, DBUtility.utilToSql(customerCoupon.getValidDate()));
				preparedStatement.setString(4, customerCoupon.getMonth());
				preparedStatement.setInt(5, customerCoupon.getYear());
				status = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return status;
	}
	
	// return coupons of a particular customer
	public List<CustomerCoupon> get(int customerId) {
		List<CustomerCoupon> list = new ArrayList<CustomerCoupon>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select cc.coupon_id, cc.valid_date, cc.month, cc.year, d.amount from customer_coupons cc, coupons c, denomination d where cc.customer_id = ? and c.coupon_id = cc.coupon_id and d.id = c.denomination_id";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				CustomerCoupon customerCoupon = new CustomerCoupon();
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				customer.setId(customerId);
				customerCoupon.setCustomer(customer);
				customerCoupon.setValidDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				customerCoupon.setMonth(resultSet.getString(3));
				customerCoupon.setYear(resultSet.getInt(4));
				Denomination denomination = new Denomination();
				denomination.setAmount(resultSet.getDouble(5));
				coupon.setDenomination(denomination);
				customerCoupon.setCoupon(coupon);
				list.add(customerCoupon);
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
	
	//return coupons assigned to all customers
	public List<CustomerCoupon> get() {	
		List<CustomerCoupon> list = new ArrayList<CustomerCoupon>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select cc.coupon_id, cc.valid_date, cc.month, cc.year, d.amount, cc.customer_id from customer_coupons cc, coupons c, denomination d where c.coupon_id = cc.coupon_id and d.id = c.denomination_id";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				CustomerCoupon customerCoupon = new CustomerCoupon();
				Customer customer = new Customer();
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				customer.setId(resultSet.getInt(6));
				customerCoupon.setCustomer(customer);
				customerCoupon.setValidDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				customerCoupon.setMonth(resultSet.getString(3));
				customerCoupon.setYear(resultSet.getInt(4));
				Denomination denomination = new Denomination();
				denomination.setAmount(resultSet.getDouble(5));
				coupon.setDenomination(denomination);
				customerCoupon.setCoupon(coupon);
				list.add(customerCoupon);
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

	public List<CustomerCoupon> validCoupons(int customerId) {
		List<CustomerCoupon> list = new ArrayList<CustomerCoupon>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select cc.coupon_id, cc.valid_date, cc.month, cc.year, d.amount from customer_coupons cc, coupons c, denomination d where cc.customer_id = ? and c.coupon_id = cc.coupon_id and d.id = c.denomination_id and valid_date >= curdate()";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				CustomerCoupon customerCoupon = new CustomerCoupon();
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				customer.setId(customerId);
				customerCoupon.setCustomer(customer);
				customerCoupon.setValidDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				customerCoupon.setMonth(resultSet.getString(3));
				customerCoupon.setYear(resultSet.getInt(4));
				Denomination denomination = new Denomination();
				denomination.setAmount(resultSet.getDouble(5));
				coupon.setDenomination(denomination);
				customerCoupon.setCoupon(coupon);
				list.add(customerCoupon);
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
	
	public List<CustomerCoupon> getUsedCoupons(int customerId) {
		List<CustomerCoupon> list = new ArrayList<CustomerCoupon>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		final String QUERY = "select cc.coupon_id, cc.valid_date, cc.month, cc.year, d.amount from customer_coupons cc, coupons c, denomination d, used_coupons uc where cc.coupon_id=? and c.coupon_id = cc.coupon_id and d.id = c.denomination_id and uc.coupon_id = cc.coupon_id";
		try (Connection connection = DBUtility.getConnection()) {
			preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Customer customer = new Customer();
				CustomerCoupon customerCoupon = new CustomerCoupon();
				Coupon coupon = new Coupon();
				coupon.setId(resultSet.getInt(1));
				customer.setId(customerId);
				customerCoupon.setCustomer(customer);
				customerCoupon.setValidDate(DBUtility.sqlToUtil(resultSet.getDate(2)));
				customerCoupon.setMonth(resultSet.getString(3));
				customerCoupon.setYear(resultSet.getInt(4));
				Denomination denomination = new Denomination();
				denomination.setAmount(resultSet.getDouble(5));
				coupon.setDenomination(denomination);
				customerCoupon.setCoupon(coupon);
				list.add(customerCoupon);
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
  