package com.yc.news.dao;

import java.util.List;
import java.util.Map;

import com.yc.news.entity.Topic;
import com.yc.news.entity.User;

public interface UserDao {

	Map<String, Object> findUser(String username,String password);

	Map<String, Object> loginUser(String uname, String upwd);

	Map<String, Object> checkUser(String uname);

	int addUser(String uname, String upwd);

	int addUser(User user);

	User getUser(String id, Class<User> clazz);

	List<User> getPartUser(int pageSize, int currPage);

	int getTotalUser(int pageSize);

	int updateUser(String pwd, String id);

	Map<String, Object> upCheckUser(String uname, String upwd);

	int archiveUser(String id);

	User getUserName(String uname, Class<User> clazz);

}
