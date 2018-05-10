package com.wise.bcms.dto;

import com.wise.bcms.dto.Order;
import com.wise.bcms.dto.Product;

public class OrderLine {
	private int qty;
	private Product product;
	private Order order;
	private double totalPrice;
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "OrderLine [qty=" + qty + ", product=" + product + ", order=" + order + ", totalPrice=" + totalPrice
				+ "]";
	}
	
}
