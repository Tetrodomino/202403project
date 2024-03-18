package com.team3.findmyhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.findmyhome.dao.CommentDao;
import com.team3.findmyhome.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired private CommentDao cDao;

	@Override
	public Comment getComment(int cid) {
		return cDao.getComment(cid);
	}

	@Override
	public List<Comment> getCommentList(int bid, String query) {
		query = "%" + query + "%";
		return cDao.getCommentList(bid, query);
	}

	@Override
	public void insertComment(Comment comment) {
		cDao.insertComment(comment);
	}

	@Override
	public void updateComment(Comment comment) {
		cDao.updateComment(comment);
	}

	@Override
	public void deleteComment(int cid) {
		cDao.deleteComment(cid);
	}

}
