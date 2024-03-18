package com.team3.findmyhome.entity;

public class Favorite {

	private int fid;
	private String uid;
	private int bid;
	
	public Favorite() {
	}

	public Favorite(int fid, String uid, int bid) {
		this.fid = fid;
		this.uid = uid;
		this.bid = bid;
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
}
