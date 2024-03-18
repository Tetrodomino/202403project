package com.team3.findmyhome.entity;

import java.util.List;

public class DealList {

	private int bid;
	private int area;
	private List<Deal> dealList;
	
	public DealList() {
	}
	
	public DealList(int bid, int area, List<Deal> dealList) {
		this.bid = bid;
		this.area = area;
		this.dealList = dealList;
	}

	@Override
	public String toString() {
		return "DealList [bid=" + bid + ", area=" + area + ", dealList=" + dealList + "]";
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public List<Deal> getDealList() {
		return dealList;
	}

	public void setDealList(List<Deal> dealList) {
		this.dealList = dealList;
	}
}
