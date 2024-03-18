package com.team3.findmyhome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.team3.findmyhome.entity.Deal;

@Mapper
public interface DealDao {

	@Select("select * from deal where did=#{bid}")
	Deal getDeal(int did);
	
	@Select("select * from deal where ${field} like #{query} order by bid, checkYear, checkDay")
	List<Deal> getDealList(String field, String query);

	@Select("select * from deal where bid=#{bid} order by checkYear, checkDay")
	List<Deal> getDealListBybid(int bid);
	
	@Select("select distinct truncate(area, 0) from deal where bid=#{bid}")
	List<Integer> getAreaList(int bid);
	
	@Select("select * from deal"
			+ " where bid=#{bid} and truncate(area, 0)=#{area}"
			+ " order by checkYear, checkDay")
	List<Deal> getDealListBybidArea(int bid, int area);
}
