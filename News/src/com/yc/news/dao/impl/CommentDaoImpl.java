package com.yc.news.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.yc.news.dao.CommentDao;
import com.yc.news.entity.Comment;
import com.yc.news.util.DbHelper;

public class CommentDaoImpl implements CommentDao {

	@Override
	public List<Comment> getCommentsByNewsId(String id) {
		String sql="select * from comments where cnid="+id;
		try {
			return DbHelper.list(Comment.class, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addComment(Comment comment) {
		
		String sql="insert into comments values(seq_comment.nextval,?,?,?,?,?)";
		
		//DbHelper.save()
		return DbHelper.doUpdate(sql, comment.getCnid(),comment.getCcontent(),comment.getCdate(),comment.getCip(),comment.getCauthor());
	}


}
