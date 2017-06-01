package com.yc.news.service;

import java.util.List;
import java.util.Map;

import com.yc.news.entity.NewsBean;
import com.yc.news.entity.Topic;
import com.yc.news.entity.TopicBean;

public interface TopicService {

	List<Topic> getAllTopic();

	boolean findTopic(Topic topic);

	Topic getTopic(String id);

	boolean modifyTopic(Topic topic);

	boolean modifyTopic( String name,String id);

	public TopicBean getAllTopic(String pageSize, String currPage);

	Map<String, Object> checkTopic(String tname);

	boolean archiveTopic(String id);



}
