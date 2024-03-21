package com.team3.findmyhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.findmyhome.dao.DealDao;
import com.team3.findmyhome.entity.Deal;

@Service
public class DealServiceImpl implements DealService {
	@Autowired private DealDao dDao;

	@Override
	public Deal getDeal(int did) {
		return dDao.getDeal(did);
	}

	@Override
	public List<Deal> getDealList(String field, String query) {
		query = "%" + query + "%";
		return dDao.getDealList(field, query);
	}

	@Override
	public List<Deal> getDealListBybid(int bid) {
		return dDao.getDealListBybid(bid);
	}

	@Override
	public List<Integer> getAreaList(int bid) {
		return dDao.getAreaList(bid);
	}

	@Override
	public List<Deal> getDealListBybidArea(int bid, int area) {
		return dDao.getDealListBybidArea(bid, area);
	}

	@Override
	public int getDealCount(int bid) {
		return dDao.getDealCount(bid);
	}
}
