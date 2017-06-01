package com.yc.news.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import com.yc.news.dao.TopicDao;
import com.yc.news.entity.News;
import com.yc.news.entity.Topic;
import com.yc.news.util.DbHelper;

public class TopicDaoImpl implements TopicDao {

	@Override
	public List<Topic> getAllTopic() {
		String sql = "select * from topic";
		try {
			return DbHelper.list(Topic.class,sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:"+e);
		}
		return null;
	}

	@Override
	public int addTopicName(Topic topic) {
		String  sql = "insert into topic values(seq_topicId.nextval,?)";
	
	return DbHelper.doUpdate(sql,topic.getTname());
	}

	@Override
	public Topic getTopicById(String id, Class<Topic> clazz) {
		String sql = "select * from topic where tid=" + id;
		Topic topic = null;
		try {
			topic = DbHelper.get(clazz,sql);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topic;
	}

	@Override
	public int updateTopic(Topic topic) {
		String  sql = "update topic set tname = ? where tid = ? ";
	
		return DbHelper.doUpdate(sql,topic.getTname(),topic.getTid());
	}

	@Override
	public int updateTopic( String name, String id) {
		String  sql = "update topic set tname = ? where tid = ? ";
		return DbHelper.doUpdate(sql,name,id);
	}

	@Override
	public List<Topic> getPartTopic(int pageSize, int currPage) {
		String sql = String.format(
				"select * from (select n.*,rownum rn from(select  * from topic order by 1)n where rownum<=%d) where rn>%d",
				pageSize * currPage, (currPage - 1) * pageSize);
		try {
			return DbHelper.list(Topic.class,sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug(e);
		}
		return null;
	}

	@Override
	public int getTotalPage(int pageSize) {
		String sql = String.format("select ceil(count(1)/%d) tp from topic   ", pageSize);
		try {
			return ((BigDecimal) DbHelper.findSingleObject(sql).get("TP")).intValue();
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:",e);
		}
		return 0;
	}

	@Override
	public Map<String, Object> checkTopic(String tname) {
		String sql = "select * from topic where tname = ?";
		try {
			return  DbHelper.findSingleObject(sql,tname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int archiveTopic(String id) {
		String sql01 = "insert into news_archive select NID,NTID,"
				+"NTITLE,NAUTHOR,NCREATEDATE,NPICPATH,to_lob(NCONTENT),"
				+"sysdate ,NSUMMARY from news where ntid = ?";
		String sql02 = "delete from news where ntid = ?";
		String sql03 = "delete from topic where tid = ?";
	List<String> sqls = Arrays.asList(sql01, sql02,sql03);
	try {
		return DbHelper.doUpdate(sqls, new String[]{id}, new String[]{id},new String[]{id} );
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return 0;
	}

}
