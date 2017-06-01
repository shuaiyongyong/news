package com.yc.news.service;

import java.util.Map;

import com.yc.news.entity.User;
import com.yc.news.entity.UserBean;

public interface UserService {

	boolean findUser(String username, String password);

	boolean login(String uname, String upwd);

	Map<String, Object> checkUser(String uname);

	boolean addUser(String uname, String upwd);

	boolean addUser(User user);

	User getUser(String id);

	UserBean getAllUser(String pageSize, String currPage);

	boolean modifyUser(String pwd, String id);

	Map<String, Object> upCheckUser(String uname, String upwd);

	boolean archiveUser(String id);

	User getUserName(String uname);


}
