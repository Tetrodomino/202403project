package com.team3.findmyhome.dao;

import java.util.List;

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
	
	@Select("select f.*, b.bname from favorite f"
			+ " join building b on b.bid=f.bid"
			+ " where uid=#{uid}")
	List<Favorite> getFavoriteList(String uid);
	
	@Select("select count(b.fid) from favorite b"
	         + " JOIN building u ON b.bid=u.bid"
	         + " where b.bid=#{bid}")
	int getFavoriteCount(int bid);
	
	@Insert("insert into favorite values(default, #{uid}, #{bid})")
	void insertFavorite(Favorite favorite);
	
	@Delete("delete from favorite where fid=#{fid}")
	void deleteFavorite(int fid);
}
