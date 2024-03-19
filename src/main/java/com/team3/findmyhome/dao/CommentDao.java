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

	@Select("select * from comment where cid=#{cid}")
	Comment getComment(int cid);

	@Select("select b.*, u.uname from comment b"
			+ " join user u on b.uid=u.uid"
			+ " where b.bid=#{bid} and b.content like #{query}"
			+ " order by b.regDateTime desc")
	List<Comment> getCommentList(int did, String query);
	
	@Insert("insert into comment values (default, #{bid}, #{uid}, #{content}, #{file}, default")
	void insertComment(Comment comment);
	
	@Update("update comment set content=#{content}, file=#{file} where cid=#{cid}")
	void updateComment(Comment comment);
	
	@Delete("delete from comment where cid=#{cid}")
	void deleteComment(int cid);
}
