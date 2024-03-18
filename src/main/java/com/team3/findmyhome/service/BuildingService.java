package com.team3.findmyhome.service;

import java.util.List;

import com.team3.findmyhome.entity.Building;

public interface BuildingService {

	Building getBuilding(int bid);
	
	List<Building> getBuildingList(String field, String query);
}
