package com.team3.findmyhome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.team3.findmyhome.entity.Building;

@Mapper
public interface BuildingDao {

	@Select("select * from building where bid=#{bid}")
	Building getBuilding(int bid);
	
	@Select("select * from building where ${field} like #{query}")
	List<Building> getBuildingList(String field, String query);
	
}
