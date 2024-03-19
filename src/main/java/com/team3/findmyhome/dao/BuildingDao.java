package com.team3.findmyhome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.team3.findmyhome.entity.Building;

@Mapper
public interface BuildingDao {

	@Select("select * from building where bid=#{bid}")
	Building getBuilding(int bid);
	
	@Select("select * from building where ${field} like #{query}")
	List<Building> getBuildingList(String field, String query);
	
	@Update("update building set lat=#{lat}, lon=#{lon} where bid=#{bid}")
	void updateBuidingLatLon(Building buidling);
	
	@Delete("delete from building where bid=#{bid}")
	void deleteBuilding(int bid);
	
	@Select("select cast(lat as decimal(15, 10)) from building where ${field} like #{query} limit 1")
	float getAvgLat(String field, String query);
	
	@Select("select cast(lon as decimal(15, 10)) from building where ${field} like #{query} limit 1")
	float getAvgLon(String field, String query);
	
	@Select("select cast(lat as decimal(15, 10)) from building where bid=#{bid}")
	float getLatBid(int bid);
	
	@Select("select cast(lon as decimal(15, 10)) from building where bid=#{bid}")
	float getLonBid(int bid);
}
