package com.team3.findmyhome.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.team3.findmyhome.dao.FavoriteDao;
import com.team3.findmyhome.entity.Favorite;

public class FavoriteServiceImpl implements FavoriteService {
	@Autowired private FavoriteDao fDao;

	@Override
	public Favorite getFavorite(int fid) {
		return fDao.getFavorite(fid);
	}

	@Override
	public Favorite getFavoriteByUid(int bid, String uid) {
		return fDao.getFavoriteByBid(bid, uid);
	}

	@Override
	public int getFavoriteCount(int bid) {
		return fDao.getFavoriteCount(bid);
	}

	@Override
	public void insertFavorite(Favorite favorite) {
		fDao.insertFavorite(favorite);
	}

	@Override
	public void deleteFavorite(int fid) {
		fDao.deleteFavorite(fid);
	}

}
