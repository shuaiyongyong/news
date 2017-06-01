package com.yc.news.service.impl;

import java.util.List;
import java.util.Map;

import com.yc.news.dao.UserDao;
import com.yc.news.dao.impl.UserDaoImpl;
import com.yc.news.entity.Topic;
import com.yc.news.entity.TopicBean;
import com.yc.news.entity.User;
import com.yc.news.entity.UserBean;
import com.yc.news.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	public UserServiceImpl() {
		userDao=new UserDaoImpl();
	}
	 
	@Override
	public boolean findUser(String username, String password) {
		return userDao.findUser(password, password) != null;
	}

	@Override
	public boolean login(String uname, String upwd) {
		return userDao.loginUser(uname, upwd) != null;
	}

	@Override
	public Map<String, Object> checkUser(String uname) {
		return userDao.checkUser(uname);
	}

	@Override
	public boolean addUser(String uname, String upwd) {
		return userDao.addUser(uname,upwd) > 0;
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user) > 0;
	}

	@Override
	public User getUser(String id) {
		return userDao.getUser(id,User.class);
	}

	@Override
	public UserBean getAllUser(String size, String page) {
		int currPage = 1;//默认当前页为第一页
		int pageSize = 30;//默认页面条数为30条
		if (size != null) {
			pageSize = Integer.parseInt(size);//取到"真实"的页面条数
		}
		int totalPage = getTotalUser(pageSize);
		if (page != null) {
			currPage = Integer.parseInt(page);
			if (currPage > totalPage) {//最后一页
				currPage = totalPage;
			} else if (currPage < 1) {//第一页
				currPage = 1;
			}
		}
		List<User> user = userDao.getPartUser(  pageSize,currPage);
		return new UserBean(currPage, totalPage, user,pageSize * totalPage);
	}

	private int getTotalUser(int pageSize) {
		return userDao.getTotalUser(  pageSize);
	}

	@Override
	public boolean modifyUser(String pwd, String id) {
		return userDao.updateUser(pwd,id) >0;
	}

	@Override
	public Map<String, Object> upCheckUser(String uname, String upwd) {
		return userDao.upCheckUser(uname,upwd);
	}

	@Override
	public boolean archiveUser(String id) {
		return userDao.archiveUser(id) > 0;
	}

	@Override
	public User getUserName(String uname) {
		return userDao.getUserName(uname,User.class);
	}
}
