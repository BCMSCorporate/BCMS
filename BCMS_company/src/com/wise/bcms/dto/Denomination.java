package com.wise.bcms.dto;

import com.wise.bcms.dto.Employee;

public class Denomination {
	private int id;
	private Employee employee;
	private double amount;
	private int active;
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Denomination [id=" + id + ", employee=" + employee + ", amount=" + amount + ", active=" + active + "]";
	}
	
}
