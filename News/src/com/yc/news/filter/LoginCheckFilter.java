package com.yc.news.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;

/**
 * 校验用户登录过滤器
 * @author 7G-5HI7
 */
@WebFilter("/*")
public class LoginCheckFilter extends AbstractFilter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LogManager.getLogger().debug("使用校验用户登录过滤器,进行权限控制,我在登陆过滤器里...");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// 1.过滤那些请求页面
		HttpServletRequest request = (HttpServletRequest) req;
		String requri = request.getRequestURI();

		if (request.getSession().getAttribute("loginUser") == null) {//当没有登录时,为空.当其他用户登录时,这里会被清空
			if (requri.endsWith("admin.jsp") || requri.endsWith("user/list") || requri.endsWith("add.jsp")) {
				// 2.满足过滤的过滤条件就终止继续请求,
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				if(request.getSession().getAttribute("errorMsg") == null){//登录成功后errorMsg本身就为空
					request.setAttribute("errorMsg", "请登录后,再操作....");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
				
				System.out.println("被过滤了");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
				return;
			}
		}

		// 2.不满足过滤的过滤条件就继续请求,
		chain.doFilter(req, resp);

	}

}
