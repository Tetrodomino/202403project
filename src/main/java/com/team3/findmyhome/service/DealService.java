package com.team3.findmyhome.service;

import java.util.List;

import com.team3.findmyhome.entity.Deal;

public interface DealService {

	Deal getDeal(int did);
	
	List<Deal> getDealList(String field, String query);
	
	List<Deal> getDealListBybid(int bid);
	
	List<Integer> getAreaList(int bid);
	
	List<Deal> getDealListBybidArea(int bid, int area);
	
	int getDealCount(int bid);
}
