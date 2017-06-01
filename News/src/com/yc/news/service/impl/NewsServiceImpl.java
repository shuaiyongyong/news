package com.yc.news.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.news.dao.NewsDao;
import com.yc.news.dao.impl.NewsDaoImpl;
import com.yc.news.entity.ArchiveBean;
import com.yc.news.entity.ArchiveNews;
import com.yc.news.entity.News;
import com.yc.news.entity.NewsBean;
import com.yc.news.service.NewsService;

public class NewsServiceImpl implements NewsService {

	private NewsDao newsDao;
	public NewsServiceImpl() {
		newsDao=new NewsDaoImpl();
	}
	@Override
	public boolean login(String name, String pwd) throws SQLException {
		return false;
	}

	@Override
	public NewsBean getPartNews(String size, String page) {
		int currPage = 1;//默认当前页为第一页
		int pageSize = 30;//默认页面条数为30条
		if (size != null) {
			pageSize = Integer.parseInt(size);//取到"真实"的页面条数
		}
		int totalPage = getTotalPage(pageSize);
		if (page != null) {
			currPage = Integer.parseInt(page);
			if (currPage > totalPage) {//最后一页
				currPage = totalPage;
			} else if (currPage < 1) {//第一页
				currPage = 1;
			}
		}
		List<News> newses = newsDao.getPartNews(  pageSize,currPage);
		//assert newses.size()>0:"newses 是空的";
		//System.out.println(newses);t56	
		return new NewsBean(currPage, totalPage, newses,pageSize * totalPage);
	}

	private int getTotalPage(int pageSize) {
		return newsDao.getTotalPage(  pageSize);
	}

	@Override
	public Map<String, Object> getNewsById(String id) {
		 
		return newsDao.getNewsById(  id);
	}

	@Override
	public List<News> getNewsByType(int ...ntids) {
		return newsDao.getNewsByType(  ntids);
	}
	
	
	@Override
	public News getNews(String id) {
		return newsDao.getNewsById(id,News.class);
	}
	@Override
	public boolean modifyNews(News n) {
		return newsDao.updateNews(n) > 0;
	}
	@Override
	public boolean archiveNews(String id) {

		return newsDao.archiveNews(id) > 0;
	}
	@Override
	public boolean addNews(News n) {
		return newsDao.insertNews(n) > 0;

	}
	@Override
	public NewsBean getPartNew(String size, String page,String tid) {
			int currPage = 1;//默认当前页为第一页
			int pageSize = 30;//默认页面条数为30条
			if (size != null) {
				pageSize = Integer.parseInt(size);//取到"真实"的页面条数
			}
			int totalPage = getTotalPaget(pageSize,tid);
			if (page != null) {
				currPage = Integer.parseInt(page);
				if (currPage > totalPage) {//最后一页
					currPage = totalPage;
				} else if (currPage < 1) {//第一页
					currPage = 1;
				}
			}
			List<News> newses = newsDao.getPartNew(  pageSize,currPage,tid);
			//assert newses.size()>0:"newses 是空的";
			//System.out.println(newses);t56	
			return new NewsBean(currPage, totalPage, newses,pageSize * totalPage);
		}
	private int getTotalPaget(int pageSize, String tid) {
		return newsDao.getTotalPaget(  pageSize , tid);
	}
	@Override
	public ArchiveBean getPartRecNews(String size, String page) {
		int currPage = 1;//默认当前页为第一页
		int pageSize = 30;//默认页面条数为30条
		if (size != null) {
			pageSize = Integer.parseInt(size);//取到"真实"的页面条数
		}
		int totalPage = getTotalPageRec(pageSize);
		if (page != null) {
			currPage = Integer.parseInt(page);
			if (currPage > totalPage) {//最后一页
				currPage = totalPage;
			} else if (currPage < 1) {//第一页
				currPage = 1;
			}
		}
		List<ArchiveNews> newses = newsDao.getPartRecNew(  pageSize,currPage);
		//assert newses.size()>0:"newses 是空的";
		//System.out.println(newses);t56	
		return new ArchiveBean(currPage, totalPage, newses,pageSize * totalPage);
	}
	private int getTotalPageRec(int pageSize) {
		return newsDao.getTotalPageRec(  pageSize);
	}
	@Override
	public ArchiveNews getRNews(String id) {
		return newsDao.getRNewsById(id,ArchiveNews.class);
	}
	@Override
	public boolean recoverNews(String id) {
		return newsDao.recoverNews(id) > 0;
	}
}
