package com.yc.news.listener;


import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

import com.yc.news.service.NewsService;
import com.yc.news.service.impl.NewsServiceImpl;

public class LoadDataListener implements ServletContextListener {

	@Override
	//容器销毁时调用的方法
	public void contextDestroyed(ServletContextEvent arg0) {
		LogManager.getLogger().debug("容器销毁了");
	}
	
	//容器初始化时调用的方法
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LogManager.getLogger().debug("容器创建并初始化");
		//sce.getServletContext();
		//application是ServletContext的实例。Servlet中调用getServletContext()方法得到ServletContext的实例。
		// getServletContext()方法得到application的实例。
		ServletContext application=sce.getServletContext();
		NewsService us=new NewsServiceImpl();
		
		//存放到application(以空间换时间),要用时调用application即可
		//application.setAttribute("userInfo", us.getAllUser());
		LogManager.getLogger().debug("把所有的用户信息加载到application中做缓存....");
		
		application.setAttribute("userSession", new ArrayList<HttpSession>());
		LogManager.getLogger().debug("创建所有登录用户信息,加载到application中做缓存....");
		
		
		
		
		
		
		
		
		
		
		
	}

}
