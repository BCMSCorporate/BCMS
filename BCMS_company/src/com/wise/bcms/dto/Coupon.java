package com.wise.bcms.dto;

import com.wise.bcms.dto.Denomination;

public class Coupon {
	private int id;
	private String validity;
	private Denomination denomination;
	private int active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public Denomination getDenomination() {
		return denomination;
	}
	public void setDenomination(Denomination denomination) {
		this.denomination = denomination;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", validity=" + validity + ", denomination=" + denomination + ", active=" + active
				+ "]";
	}

}
