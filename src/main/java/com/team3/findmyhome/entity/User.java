package com.team3.findmyhome.entity;

import java.time.LocalDate;

public class User {

	private String uid;
	private String pwd;
	private String uname;
	private String email;
	private LocalDate regDate;
	private String location;
	private String tel;
	
	public User() {
	}

	public User(String uid, String pwd, String uname) {
		this.uid = uid;
		this.pwd = pwd;
		this.uname = uname;
	}

	public User(String uid, String pwd, String uname, String email, String location, String tel) {
		this.uid = uid;
		this.pwd = pwd;
		this.uname = uname;
		this.email = email;
		this.location = location;
		this.tel = tel;
	}

	public User(String uid, String pwd, String uname, String email, LocalDate regDate, String location, String tel) {
		this.uid = uid;
		this.pwd = pwd;
		this.uname = uname;
		this.email = email;
		this.regDate = regDate;
		this.location = location;
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", pwd=" + pwd + ", uname=" + uname + ", email=" + email + ", regDate=" + regDate
				+ ", location=" + location + ", tel=" + tel + "]";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
