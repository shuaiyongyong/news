package com.yc.news.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * 做filter的适配类
 * 
 * @author 7G-5HI7
 *
 */
public abstract class AbstractFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
