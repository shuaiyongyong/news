package com.yc.news.service;

import java.util.List;

import com.yc.news.entity.Comment;

public interface CommentService {

	List<Comment> listCommentsByNewsId(String id);


	boolean addComment(Comment comment);

	
}
