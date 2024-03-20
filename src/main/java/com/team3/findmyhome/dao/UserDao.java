package com.team3.findmyhome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.team3.findmyhome.entity.User;

@Mapper
public interface UserDao {
	
	@Select("select * from user where uid=#{uid}")
	User getUser(String uid);
	
	@Select("select * from user"
			+ " order by regDate desc")
	List<User> getUserList();
	
	@Insert("insert into user values (#{uid}, #{pwd}, #{uname}"
			+ ", #{email}, default, #{location}, #{tel})")
	void insertUser(User user);
	
	@Update("update user set pwd=#{pwd}, uname=#{uname}, email=#{email}"
			+ ", location=#{location}, tel=#{tel}"
			+ " where uid=#{uid}")
	void updateUser(User user);
	
	@Delete("delete user where uid=#{uid}")
	void deleteUser(String uid);
}
