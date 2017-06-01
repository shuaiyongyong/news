package com.yc.news.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import com.yc.news.dao.NewsDao;
import com.yc.news.entity.ArchiveNews;
import com.yc.news.entity.News;
import com.yc.news.util.DbHelper;

public class NewsDaoImpl implements NewsDao {

	@Override
	public List<News> getPartNews(int pageSize, int currPage) {
		String sql = String.format(
				"select * from (select n.*,rownum rn from(select  * from NEWS order by 1)n where rownum<=%d) where rn>%d",
				pageSize * currPage, (currPage - 1) * pageSize);
		try {
			return DbHelper.list(News.class,sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug(e);
		}
		return null;
	}
	

	@Override
	public List<News> getNewsByType(int... ntids) {
		String sql = "select nid,ntid,ntitle from news";
		if (ntids != null && ntids.length != 0) {
			sql += " where ";
			for (int ntid : ntids) {
				sql += "nid in(select nid from(select * from news where ntid=" + ntid
						+ " order by 5) where rownum<=5 ) or ";
			}
			sql = sql.substring(0, sql.length() - 4);//去掉最后面的  “ or ”共四个字节
		}
		try {
			return DbHelper.list(News.class,sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:",e);
		}
		return null;
	}

	@Override
	public Map<String, Object> getNewsById(String id) {
		String sql = "select * from news where nid=" + id;
		try {
			return DbHelper.findSingleObject(sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:",e);
		}
		return null;
	}


	@Override
	public int getTotalPage(int pageSize) {
		String sql = String.format("select ceil(count(1)/%d) tp from news   ", pageSize);
		try {
			return ((BigDecimal) DbHelper.findSingleObject(sql).get("TP")).intValue();
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:",e);
		}
		return 0;
	}


	@Override
	public News getNewsById(String id, Class<News> clazz) {
		String sql = "select * from news where nid=" + id;
		News news = null;
		try {
			news = DbHelper.get(clazz,sql);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return news;
	}


	@Override
	public int updateNews(News n) {
		String  sql = "update news set ntid = ? , ntitle = ? , nsummary = ? , "
					+" ncontent = ? , nmodifyDate = sysdate , nauthor = ? "
					+ (n.getNpicpath() != null ? "," + n.getNpicpath() : " " ) +"where nid = ?";
		
		return DbHelper.doUpdate(sql, n.getNtid(),n.getNtitle(),n.getNsummary(),n.getNcontent(),n.getNauthor(),n.getNid());
	}


	@Override
	public int archiveNews(String id) {
		//News n = getNewsById(id, class);
		String sql01 = "insert into news_archive select NID,NTID,"
					+"NTITLE,NAUTHOR,NCREATEDATE,NPICPATH,to_lob(NCONTENT),"
					+"sysdate ,NSUMMARY from news where nid = ?";
		String sql02 = "delete from news where nid = ?";
		List<String> sqls = Arrays.asList(sql01, sql02);
		try {
			return DbHelper.doUpdate(sqls, new String[]{id}, new String[]{id} );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}


	@Override
	public int insertNews(News n) {
		String sql = "insert into news values(seq_news.nextval,?,?,?,sysdate,?,?,null,?)";
		return DbHelper.doUpdate(sql, n.getNtid(),n.getNtitle(),n.getNauthor(),
					n.getNpicpath() == null ? "" : n.getNpicpath(),
					n.getNcontent(),n.getNsummary());
	}


	@Override
	public List<News> getPartNew(int pageSize, int currPage,String tid) {
		String sql = String.format(
				"select * from (select n.*,rownum rn from(select  * from NEWS where ntid = "+tid+" )n where rownum<=%d) where rn>%d",
				pageSize * currPage, (currPage - 1) * pageSize);
		try {
			return DbHelper.list(News.class,sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug(e);
		}
		return null;
	}


	@Override
	public int getTotalPaget(int pageSize , String tid) {
		String sql = String.format("select ceil(count(1)/%d) tp from news  where ntid = "+tid+"  ", pageSize);
		try {
			return ((BigDecimal) DbHelper.findSingleObject(sql).get("TP")).intValue();
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:",e);
		}
		return 0;
	}


	@Override
	public List<ArchiveNews> getPartRecNew(int pageSize, int currPage) {
		String sql = String.format(
				"select * from (select n.*,rownum rn from(select  * from news_archive order by 1)n where rownum<=%d) where rn>%d",
				pageSize * currPage, (currPage - 1) * pageSize);
		try {
			System.out.println(sql);
			return DbHelper.list(ArchiveNews.class,sql);
		} catch (SQLException e) {
			LogManager.getLogger().debug(e);
		}
		return null;
	}


	@Override
	public int getTotalPageRec(int pageSize) {
		String sql = String.format("select ceil(count(1)/%d) tp from news_archive   ", pageSize);
		try {
			return ((BigDecimal) DbHelper.findSingleObject(sql).get("TP")).intValue();
		} catch (SQLException e) {
			LogManager.getLogger().debug("错误:",e);
		}
		return 0;
	}


	@Override
	public ArchiveNews getRNewsById(String id, Class<ArchiveNews> clazz) {
		String sql = "select * from news_archive where nid=" + id;
		ArchiveNews rnews = null;
		try {
			rnews = DbHelper.get(clazz,sql);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rnews;
	}


	@Override
	public int recoverNews(String id) {
		String sql01 = "insert into news select NID,NTID,"
				+"NTITLE,NAUTHOR,NCREATEDATE,NPICPATH,NCONTENT,"
				+"NMODIFYDATE ,NSUMMARY from news_archive where nid = ?";
	String sql02 = "delete from news_archive where nid = ?";
	List<String> sqls = Arrays.asList(sql01, sql02);
	try {
		return DbHelper.doUpdate(sqls, new String[]{id}, new String[]{id} );
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return 0;
	}

}
