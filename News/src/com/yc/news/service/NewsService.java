package com.yc.news.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.news.entity.ArchiveBean;
import com.yc.news.entity.ArchiveNews;
import com.yc.news.entity.News;
import com.yc.news.entity.NewsBean;

public interface NewsService {

	public boolean login(String name, String pwd) throws SQLException ;

	public NewsBean getPartNews(String pageSize, String currPage );

	public Map<String, Object> getNewsById(String id);

	public List<News> getNewsByType(int...id);

	public News getNews(String id);

	public boolean modifyNews(News n);

	public boolean archiveNews(String id);

	public boolean addNews(News n);

	public NewsBean getPartNew(String pageSize, String currPage, String tid);

	public ArchiveBean getPartRecNews(String pageSize, String currPage);

	public ArchiveNews getRNews(String id);

	public boolean recoverNews(String id);

}
