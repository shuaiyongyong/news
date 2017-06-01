package com.yc.news.service.impl;

import java.util.List;
import java.util.Map;

import com.yc.news.dao.TopicDao;
import com.yc.news.dao.impl.TopicDaoImpl;
import com.yc.news.entity.News;
import com.yc.news.entity.NewsBean;
import com.yc.news.entity.Topic;
import com.yc.news.entity.TopicBean;
import com.yc.news.service.TopicService;

public class TopicServiceImpl implements TopicService {

	private TopicDao topicDao;
	public TopicServiceImpl() {
		topicDao=new TopicDaoImpl();
	}
	@Override
	public List<Topic> getAllTopic() {
		return topicDao.getAllTopic();
	}
	@Override
	public boolean findTopic(Topic topic) {
		return topicDao.addTopicName(topic) > 0;
	}
	@Override
	public Topic getTopic(String id) {
		return topicDao.getTopicById(id,Topic.class);
	}
	@Override
	public boolean modifyTopic(Topic topic) {
		return topicDao.updateTopic(topic) > 0;
	}
	@Override
	public boolean modifyTopic(String name,String id) {
		return topicDao.updateTopic(name,id) >0;
	}
	@Override
	public TopicBean getAllTopic(String size, String page) {
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
			List<Topic> topic = topicDao.getPartTopic(  pageSize,currPage);
			//assert newses.size()>0:"newses 是空的";
			//System.out.println(newses);t56	
			return new TopicBean(currPage, totalPage, topic,pageSize * totalPage);
	}
	private int getTotalPage(int pageSize) {
		return topicDao.getTotalPage(  pageSize);
	}
	@Override
	public Map<String, Object> checkTopic(String tname) {
		return topicDao.checkTopic(tname);
	}
	@Override
	public boolean archiveTopic(String id) {
		return topicDao.archiveTopic(id) > 0;
	}
}
