package com.yc.news.dao;

import java.util.List;
import java.util.Map;

import com.yc.news.entity.ArchiveNews;
import com.yc.news.entity.News;

public interface NewsDao {

	List<News> getPartNews(int pageSize, int currPage);

	List<News> getNewsByType(int... ntids);

	Map<String, Object> getNewsById(String id);

	int getTotalPage(int pageSize);

	News getNewsById(String id, Class<News> clazz);

	int updateNews(News n);

	int archiveNews(String id);

	int insertNews(News n);

	List<News> getPartNew(int pageSize, int currPage, String tid);

	int getTotalPaget(int pageSize, String tid);

	List<ArchiveNews> getPartRecNew(int pageSize, int currPage);

	int getTotalPageRec(int pageSize);

	ArchiveNews getRNewsById(String id, Class<ArchiveNews> clazz);

	int recoverNews(String id);
 

}
