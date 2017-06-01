package com.yc.news.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import com.yc.news.dao.UserDao;
import com.yc.news.entity.Topic;
import com.yc.news.entity.User;
import com.yc.news.util.DbHelper;

public class UserDaoImpl implements UserDao {

	@Override
	public Map<String, Object> findUser(String username,String password) {
		String sql = "select 1 from news_users where uname=? and upwd=?";
		try {
			return DbHelper.findSingleObject(sql, username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Map<String, Object> loginUser(String uname, String upwd) {
		String sql = "select 1 from news_users where uname=? and upwd=?";
		try {
			return DbHelper.findSingleObject(sql, uname,upwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> checkUser(String uname) {
		String sql = "select * from news_users where uname = ?";
		try {
			return  DbHelper.findSingleObject(sql,uname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addUser(String uname, String upwd) {
		String sql = "insert into users values(seq_usid.nextval,?,?)";
		return DbHelper.doUpdate(sql, uname,upwd);
	}

	@Override
	public int addUser(User user) {
		String  sql = "insert into news_users values(seq_usid.nextval,?,?,default)";
		return DbHelper.doUpdate(sql,user.getUname(),user.getUpwd());
	}

	@Override
	public User getUser(String id, Class<User> clazz) {
		String sql = "select * from news_users where usid=" + id;
		User user = null;
		try {
			user = DbHelper.get(clazz,sql);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getPartUser(int pageSize, int currPage) {
		String sql = String.format(
				"select * from (select n.*,rownum rn from(select  * from news_users order by 1)n where rownum<=%d) where rn>%d and uname != 'admin' ",
				pageSize * currPage, (currPage - 1) * pageSize);
		try {
			return DbHelper.list(User.class,sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug(e);
		}
		return null;
	}

	@Override
	public int getTotalUser(int pageSize) {
		String sql = String.format("select ceil(count(1)/%d) tp from news_users   ", pageSize);
		try {
			return ((BigDecimal) DbHelper.findSingleObject(sql).get("TP")).intValue();
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:",e);
		}
		return 0;
	}

	@Override
	public int updateUser(String pwd, String id) {
		String  sql = "update news_users set upwd = ? where usid = ? ";
		return DbHelper.doUpdate(sql,pwd,id);
	}

	@Override
	public Map<String, Object> upCheckUser(String uname, String upwd) {
		String sql = "select * from news_users where uname = ? and upwd = ?";
		try {
			return  DbHelper.findSingleObject(sql,uname,upwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int archiveUser(String id) {
		String sql = "delete from news_users where usid = ?";
		return DbHelper.doUpdate(sql,id);
	}

	@Override
	public User getUserName(String uname, Class<User> clazz) {
		String sql = "select * from news_users where uname=" + uname;
		User user = null;
		try {
			user = DbHelper.get(clazz,sql);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
