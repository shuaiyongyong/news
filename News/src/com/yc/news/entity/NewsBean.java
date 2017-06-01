package com.yc.news.entity;

import java.io.Serializable;
import java.util.List;

public class NewsBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -541475228302438374L;
	private int currPage ;//当前页面
	private int totalPage ;//页面总数
	private  List<News> rows;
	private int total;
	
	public NewsBean() {
		super();
	}

	
	public NewsBean(int currPage, int totalPage, List<News> rows, int total) {
		this.currPage = currPage;
		this.totalPage = totalPage;
		this.rows = rows;
		this.total = total;
	}


	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<News> getRows() {
		return rows;
	}

	public void setRows(List<News> rows) {
		this.rows = rows;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "NewsBean [currPage=" + currPage + ", totalPage=" + totalPage + ", rows=" + rows + "]";
	}
		
}
