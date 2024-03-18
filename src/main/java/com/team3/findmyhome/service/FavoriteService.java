package com.team3.findmyhome.service;

import com.team3.findmyhome.entity.Favorite;

public interface FavoriteService {

	Favorite getFavorite(int fid);
	
	Favorite getFavoriteByUid(int bid, String uid);
	
	int getFavoriteCount(int bid);
	
	void insertFavorite(Favorite favorite);

	void deleteFavorite(int fid);
}
