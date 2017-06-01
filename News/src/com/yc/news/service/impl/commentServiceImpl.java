package com.yc.news.service.impl;

import java.util.List;

import com.yc.news.dao.CommentDao;
import com.yc.news.dao.impl.CommentDaoImpl;
import com.yc.news.entity.Comment;
import com.yc.news.service.CommentService;

public class commentServiceImpl implements CommentService {
	private CommentDao commentDao;
	public commentServiceImpl(){
		commentDao=new CommentDaoImpl();
	}

	@Override
	public List<Comment> listCommentsByNewsId(String id) {
		
		return commentDao.getCommentsByNewsId(id);
	}

	@Override
	public boolean addComment(Comment comment) {
		
		return commentDao.addComment(comment) > 0;
	}

	
	
}
