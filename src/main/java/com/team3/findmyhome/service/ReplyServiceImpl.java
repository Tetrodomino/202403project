package com.team3.findmyhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.findmyhome.dao.ReplyDao;
import com.team3.findmyhome.entity.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired private ReplyDao rDao;

	@Override
	public Reply getReply(int rid) {
		return rDao.getReply(rid);
	}

	@Override
	public List<Reply> getReplyList(int bid) {
		return rDao.getReplyList(bid);
	}

	@Override
	public void insertReply(Reply reply) {
		rDao.insertReply(reply);
	}

	@Override
	public void updateReply(Reply reply) {
		rDao.updateReply(reply);
	}

	@Override
	public void deleteReply(int rid) {
		rDao.deleteReply(rid);
	}

}
