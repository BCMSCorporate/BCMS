package com.wise.bcms.dto;

import java.util.Date;

public class CustomerCoupon {
	private Coupon coupon;
	private Customer customer;
	private Date validDate;
	private String month;
	private int year;
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
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
		return "CustomerCoupon [coupon=" + coupon + ", customer=" + customer + ", validDate=" + validDate + ", month="
				+ month + ", year=" + year + "]";
	}
	
}
