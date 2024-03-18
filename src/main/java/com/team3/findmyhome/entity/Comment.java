package com.team3.findmyhome.entity;

import java.time.LocalDateTime;

public class Comment {

	private int cid;
	private int bid;
	private String uid;
	private String content;
	private String file;
	private LocalDateTime regDateTime;
	private String uname;
	
	public Comment() {
	}
	
	public Comment(int cid, int bid, String uid, String content) {
		this.cid = cid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
	}

	public Comment(int bid, String uid, String content, String file) {
		this.bid = bid;
		this.uid = uid;
		this.content = content;
		this.file = file;
	}

	public Comment(int cid, int bid, String uid, String content, String file, LocalDateTime regDateTime) {
		this.cid = cid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
		this.file = file;
		this.regDateTime = regDateTime;
	}

	public Comment(int cid, int bid, String uid, String content, String file, LocalDateTime regDateTime, String uname) {
		this.cid = cid;
		this.bid = bid;
		this.uid = uid;
		this.content = content;
		this.file = file;
		this.regDateTime = regDateTime;
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", bid=" + bid + ", uid=" + uid + ", content=" + content + ", file=" + file
				+ ", regDateTime=" + regDateTime + "]";
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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
	
}
