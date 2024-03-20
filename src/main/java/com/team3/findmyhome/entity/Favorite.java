package com.team3.findmyhome.entity;

public class Favorite {

	private int fid;
	private String uid;
	private int bid;
	private String bname;
	
	public Favorite() {
	}

	public Favorite(String uid, int bid) {
		this.uid = uid;
		this.bid = bid;
	}

	public Favorite(int fid, String uid, int bid) {
		this.fid = fid;
		this.uid = uid;
		this.bid = bid;
	}

	public Favorite(int fid, String uid, int bid, String bname) {
		this.fid = fid;
		this.uid = uid;
		this.bid = bid;
		this.bname = bname;
	}

	@Override
	public String toString() {
		return "Favorite [fid=" + fid + ", uid=" + uid + ", bid=" + bid + "]";
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}
}
