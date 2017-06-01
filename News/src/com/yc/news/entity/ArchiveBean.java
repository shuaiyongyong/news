package com.yc.news.entity;

import java.io.Serializable;
import java.util.List;

public class ArchiveBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -541475228302438374L;
	private int currPage ;//当前页面
	private int totalPage ;//页面总数
	//private  List<ArchiveNews> ArchiveNewses;
	private  List<ArchiveNews> rows;
	private int total;
	
	public ArchiveBean() {
		super();
	}

	
	public ArchiveBean(int currPage, int totalPage, List<ArchiveNews> rows, int total) {
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

	public List<ArchiveNews> getRows() {
		return rows;
	}

	public void setRows(List<ArchiveNews> rows) {
		this.rows = rows;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ArchiveNewsBean [currPage=" + currPage + ", totalPage=" + totalPage + ", rows=" + rows + "]";
	}
		
}
