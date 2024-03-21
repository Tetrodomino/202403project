package com.team3.findmyhome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.team3.findmyhome.entity.Reply;

@Mapper
public interface ReplyDao {

	@Select("select * from reply where rid=#{rid}")
	Reply getReply(int rid);
	
	@Select("select r.*, u.uname from reply r"
			+ " join user u on r.uid=u.uid"
			+ " where bid=#{bid} order by regDateTime")
	List<Reply> getReplyList(int bid);
	
	@Insert("insert into reply values(default, #{cid}, #{bid}, #{uid}, #{content}, default)")
	void insertReply(Reply reply);
	
	@Update("update reply set content=#{content}, regDateTime=now() where rid=#{rid}")
	void updateReply(Reply reply);
	
	@Delete("delete from reply where rid=#{rid}")
	void deleteReply(int rid);
}
