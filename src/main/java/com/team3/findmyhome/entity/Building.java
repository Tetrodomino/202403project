package com.team3.findmyhome.entity;

public class Building {

	private int bid;
	private String location;
	private String bname;
	private String btype;
	private String address;
	private String addressroad;
	private String number;
	private String approve;
	
	private String lat;
	private String lon;
	
	public Building() {
	}

	public Building(int bid, String location, String bname, String btype, String address, String addressroad,
			String number, String approve) {
		this.bid = bid;
		this.location = location;
		this.bname = bname;
		this.btype = btype;
		this.address = address;
		this.addressroad = addressroad;
		this.number = number;
		this.approve = approve;
	}
	
	public Building(int bid, String location, String bname, String btype, String address, String addressroad,
			String number, String approve, String lat, String lon) {
		this.bid = bid;
		this.location = location;
		this.bname = bname;
		this.btype = btype;
		this.address = address;
		this.addressroad = addressroad;
		this.number = number;
		this.approve = approve;
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public String toString() {
		return "Building [bid=" + bid + ", location=" + location + ", bname=" + bname + ", btype=" + btype
				+ ", address=" + address + ", addressroad=" + addressroad + ", number=" + number + ", approve="
				+ approve + "]";
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressroad() {
		return addressroad;
	}

	public void setAddressroad(String addressroad) {
		this.addressroad = addressroad;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
	
}
