package com.yc.news.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;

@WebFilter("/*")
/**
 * 乱码处理的过滤器
 * @author 7G-5HI7
 */
public class CharacterEncodingFilter extends AbstractFilter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LogManager.getLogger().debug("使用校验用户登录过滤器,进行权限控制,我在乱码处理器里...");

	}
		
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 请求字符编码集,
		request.setCharacterEncoding("utf-8");
		// 响应字符编码集
		response.setContentType("text/html charset='utf-8'");
		response.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response);
	}

}
