package com.team3.findmyhome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.team3.findmyhome.entity.Comment;

@Mapper
public interface CommentDao {

	@Select("select * from comments where cid=#{cid}")
	Comment getComment(int cid);

	@Select("select b.*, u.uname from comments b"
			+ " join users u on b.uid=u.uid"
			+ " where b.bid=#{bid} and content like b.#{query}"
			+ " order by b.regDateTime desc")
	List<Comment> getCommentList(int did, String query);
	
	@Insert("insert into comments values (default, #{bid}, #{uid}, #{content}, #{file}, default")
	void insertComment(Comment comment);
	
	@Update("update comments set content=#{content}, file=#{file} where cid=#{cid}")
	void updateComment(Comment comment);
	
	@Delete("delete from comments where cid=#{cid}")
	void deleteComment(int cid);
}
