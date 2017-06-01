package com.yc.news.dao;

import java.util.List;

import com.yc.news.entity.Comment;

public interface CommentDao {

	List<Comment> getCommentsByNewsId(String id);

	int addComment(Comment comment);
	
}
