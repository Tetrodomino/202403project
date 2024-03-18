package com.team3.findmyhome.entity;

import java.time.LocalDateTime;

public class Reply {

	private int rid;
	private int cid;
	private int bid;
	private String uid;
	private String content;
	private LocalDateTime regDateTime;
	private String uname;
	
	public Reply() {
	}
	
	public Reply(int cid, int bid, String uid, String content) {
		this.cid = cid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
	}

	public Reply(int rid, int cid, int bid, String uid, String content, String uname) {
		this.rid = rid;
		this.cid = cid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
		this.uname = uname;
	}

	public Reply(int rid, int cid, int bid, String uid, String content, LocalDateTime regDateTime, String uname) {
		this.rid = rid;
		this.cid = cid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
		this.regDateTime = regDateTime;
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Reply [rid=" + rid + ", cid=" + cid + ", uid=" + uid + ", content=" + content + ", regDateTime="
				+ regDateTime + "]";
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(LocalDateTime regDateTime) {
		this.regDateTime = regDateTime;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getbid() {
		return bid;
	}

	public void setbid(int bid) {
		this.bid = bid;
	}
	
}
