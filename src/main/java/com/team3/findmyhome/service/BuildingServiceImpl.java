package com.team3.findmyhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.findmyhome.dao.BuildingDao;
import com.team3.findmyhome.entity.Building;

@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired private BuildingDao bDao;

	@Override
	public Building getBuilding(int bid) {
		return bDao.getBuilding(bid);
	}

	@Override
	public List<Building> getBuildingList(String field, String query) {
		query = "%" + query + "%";
		return bDao.getBuildingList(field, query);
	}

	@Override
	public void updateBuildingLatLon(Building building) {
		bDao.updateBuidingLatLon(building);
	}

	@Override
	public void deleteBuilding(int bid) {
		bDao.deleteBuilding(bid);
	}

	@Override
	public float getAvgLat(String field, String query) {
		query = "%" + query + "%";
		return bDao.getAvgLat(field, query);
	}

	@Override
	public float getAvgLon(String field, String query) {
		query = "%" + query + "%";
		return bDao.getAvgLon(field, query);
	}

	@Override
	public float getLatBid(int bid) {
		return bDao.getLatBid(bid);
	}

	@Override
	public float getLonBid(int bid) {
		return bDao.getLonBid(bid);
	}
}
