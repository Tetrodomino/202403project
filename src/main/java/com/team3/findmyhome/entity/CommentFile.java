package com.team3.findmyhome.entity;

public class CommentFile {

	private int cid;
	private String file;
	
	public CommentFile() {
	}

	public CommentFile(int cid, String file) {
		this.cid = cid;
		this.file = file;
	}

	@Override
	public String toString() {
		return "CommentFile [cid=" + cid + ", file=" + file + "]";
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
