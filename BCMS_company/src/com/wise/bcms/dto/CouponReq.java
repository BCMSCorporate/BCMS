package com.wise.bcms.dto;

import com.wise.bcms.dto.Customer;

public class CouponReq {
	private Customer customer;
	private double amount;
	private String month;
	private int year;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "CouponReq [customer=" + customer + ", amount=" + amount + ", month=" + month + ", year=" + year + "]";
	}
	
}
