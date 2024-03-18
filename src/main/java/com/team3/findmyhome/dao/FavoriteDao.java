package com.team3.findmyhome.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.team3.findmyhome.entity.Favorite;

@Mapper
public interface FavoriteDao {

	@Select("select * from favorite where fid=#{fid}")
	Favorite getFavorite(int fid);
	
	@Select("select * from favorite where bid=#{bid} and uid=#{uid}")
	Favorite getFavoriteByBid(int bid, String uid);
	
	@Select("select count(b.fid) from favorite b"
	         + " JOIN building u ON b.bid=u.bid")
	int getFavoriteCount(int bid);
	
	@Insert("insert into favorite values(default, #{bid}, #{uid}")
	void insertFavorite(Favorite favorite);
	
	@Delete("delete from favorite where fid=#{fid}")
	void deleteFavorite(int fid);
}
