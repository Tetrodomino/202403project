package com.team3.findmyhome.entity;

public class Deal {

	private int did;
	private int bid;
	private String bname;
	private double area;
	private String checkYear;
	private String checkday;
	private int money;
	private String dong;
	private int floor;
	private int builtYear;
	
	public Deal() {
	}

	public Deal(int did, String bname) {
		this.did = did;
		this.bname = bname;
	}

	public Deal(int did, String bname, double area, String checkYear, String checkday, int money, String dong, int floor,
			int builtYear) {
		this.did = did;
		this.bname = bname;
		this.area = area;
		this.checkYear = checkYear;
		this.checkday = checkday;
		this.money = money;
		this.dong = dong;
		this.floor = floor;
		this.builtYear = builtYear;
	}

	public Deal(int did, int bid, String bname, double area, String checkYear, String checkday, int money, String dong,
			int floor, int builtYear) {
		this.did = did;
		this.bid = bid;
		this.bname = bname;
		this.area = area;
		this.checkYear = checkYear;
		this.checkday = checkday;
		this.money = money;
		this.dong = dong;
		this.floor = floor;
		this.builtYear = builtYear;
	}

	@Override
	public String toString() {
		return "Deal [did=" + did + ", bname=" + bname + ", area=" + area + ", checkYear=" + checkYear + ", checkday="
				+ checkday + ", money=" + money + ", dong=" + dong + ", floor=" + floor + ", builtYear=" + builtYear
				+ "]";
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getCheckYear() {
		return checkYear;
	}

	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}

	public String getCheckday() {
		return checkday;
	}

	public void setCheckday(String checkday) {
		this.checkday = checkday;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(int builtYear) {
		this.builtYear = builtYear;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}
	
}
