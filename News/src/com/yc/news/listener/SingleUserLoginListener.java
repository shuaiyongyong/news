package com.yc.news.listener;

import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

 
/*@WebListener*/
/*注释就没有启用监听*/
public class SingleUserLoginListener implements ServletContextAttributeListener {

    
    public SingleUserLoginListener() {
    	LogManager.getLogger().debug("application存储属性监听器启动");
    }

	 //在application中添加属性,调用方法,触发事件
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	System.out.println("s属性添加");
    }

  //在application中删除属性,调用方法,触发事件
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
    	System.out.println("s属性删除");
    }

	 
  //在application中替换属性,调用方法,触发事件
    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
    	System.out.println("s属性替换");
    	List<HttpSession> hss=(List<HttpSession>) scae.getServletContext().getAttribute("userSession");
    	if(hss.size()>1){
			String name=(String) hss.get(hss.size()-1).getAttribute("username");
    		for (int i = hss.size()-2; i >=0; i--) {
				if(hss.get(i).getAttribute("username").toString().intern() == name.intern()){
					
					//scae.getServletContext().setAttribute("erorr", "您在异地登陆!!");
					hss.get(i).setAttribute("errorMsg", "您在异地登陆!!");
					hss.get(i).removeAttribute("username");
					hss.remove(hss.get(i));
					LogManager.getLogger().debug("您成功挤掉对方,application");
					break;
				}
			}
    	}
    	
    	
    }
	
}
