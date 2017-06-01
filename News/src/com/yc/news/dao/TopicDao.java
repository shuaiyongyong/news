package com.yc.news.dao;

import java.util.List;
import java.util.Map;

import com.yc.news.entity.News;
import com.yc.news.entity.Topic;

public interface TopicDao {

	List<Topic> getAllTopic();

	int addTopicName(Topic topic);

	Topic getTopicById(String id, Class<Topic> clazz);

	int updateTopic(Topic topic);

	int updateTopic(String id, String name);

	List<Topic> getPartTopic(int pageSize, int currPage);

	int getTotalPage(int pageSize);

	Map<String, Object> checkTopic(String tname);

	int archiveTopic(String id);

}
