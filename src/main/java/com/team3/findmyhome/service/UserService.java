package com.team3.findmyhome.service;

import java.util.List;

import com.team3.findmyhome.entity.User;

public interface UserService {
	public static final int CORRECT_LOGIN = 0;
	public static final int WRONG_PASSWORD = 1;
	public static final int USER_NOT_EXIST = 2;

	User getUserByUid(String uid);
	
	List<User> getUserList();
	
	void registerUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(String uid);
	
	int login(String uid, String pwd);
	
}
