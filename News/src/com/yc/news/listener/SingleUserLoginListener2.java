package com.yc.news.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.logging.log4j.LogManager;

@WebListener
public class SingleUserLoginListener2 implements HttpSessionAttributeListener {

	public SingleUserLoginListener2() {
		LogManager.getLogger().debug("单登陆监听器启动");
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	public void attributeReplaced(HttpSessionBindingEvent hsbe) {

	}

	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		LogManager.getLogger().debug("属性替换");
		if (hsbe.getName().intern() == "username") {// 判断是否是登录用户信息存入session
			HttpSession currsession = hsbe.getSession();//
			ServletContext application = currsession.getServletContext();

			// 已经登录的用户信息(session)
			List<HttpSession> hss = (List<HttpSession>) application.getAttribute("userSession");
			if (hss.size() > 0) {
				String name = (String) currsession.getAttribute("username");
				for (int i = hss.size() - 1; i >= 0; i--) {
					if (hss.get(i).getAttribute("username").toString().intern() == name.intern()) {
						hss.get(i).setAttribute("errorMsg", "您在异地登陆!!");
						hss.get(i).removeAttribute("username");
						hss.remove(hss.get(i));
						LogManager.getLogger().debug("您成功挤掉对方,session");
						break;
					}
				}
			}
			hss.add(currsession);// 添加自己到登录用户信息内

		}
	}

}
