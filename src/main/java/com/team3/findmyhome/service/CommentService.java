package com.team3.findmyhome.service;

import java.util.List;

import com.team3.findmyhome.entity.Comment;

public interface CommentService {

	Comment getComment(int cid);
	
	List<Comment> getCommentList(int bid, String query);
	
	void insertComment(Comment comment);
	
	void updateComment(Comment comment);
	
	void deleteComment(int cid);
	
}
