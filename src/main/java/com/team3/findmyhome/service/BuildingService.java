package com.team3.findmyhome.service;

import java.util.List;

import com.team3.findmyhome.entity.Building;

public interface BuildingService {

	Building getBuilding(int bid);
	
	List<Building> getBuildingList(String field, String query);
	
	void updateBuildingLatLon(Building building);
	
	void deleteBuilding(int bid);
	
	float getAvgLat(String field, String query);

	float getAvgLon(String field, String query);
	
	float getLatBid(int bid);

	float getLonBid(int bid);
}
