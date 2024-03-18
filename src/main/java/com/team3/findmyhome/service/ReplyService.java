package com.team3.findmyhome.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team3.findmyhome.entity.Reply;

@Service
public interface ReplyService {

	Reply getReply(int rid);
	
	List<Reply> getReplyList(int bid);
	
	void insertReply(Reply reply);
	
	void updateReply(Reply reply);
	
	void deleteReply(int rid);
}
