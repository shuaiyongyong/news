package com.yc.news.entity;

import java.util.Date;

public class ArchiveNews {
	private Integer nid;
	private Integer ntid;
	private String ntitle;
	private String nauthor;
	private Date ncreateDate;
	private String npicpath;
	private String ncontent;
	private Date nmodifyDate;
	private String nsummary;
	public ArchiveNews() {
	}
	
	public ArchiveNews(Integer ntid, String ntitle, String nauthor, Date ncreateDate, String npicpath, String ncontent,
			 String nsummary) {
		super();
		this.ntid = ntid;
		this.ntitle = ntitle;
		this.nauthor = nauthor;
		this.ncreateDate = ncreateDate;
		this.npicpath = npicpath;
		this.ncontent = ncontent;
		this.nsummary = nsummary;
	}



	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public Integer getNtid() {
		return ntid;
	}
	public void setNtid(Integer ntid) {
		this.ntid = ntid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNauthor() {
		return nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}
	public Date getNcreateDate() {
		return ncreateDate;
	}
	public void setNcreateDate(Date ncreateDate) {
		this.ncreateDate = ncreateDate;
	}
	public String getNpicpath() {
		return npicpath;
	}
	public void setNpicpath(String npicpath) {
		this.npicpath = npicpath;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Date getNmodifyDate() {
		return nmodifyDate;
	}
	public void setNmodifyDate(Date nmodifyDate) {
		this.nmodifyDate = nmodifyDate;
	}
	public String getNsummary() {
		return nsummary;
	}
	public void setNsummary(String nsummary) {
		this.nsummary = nsummary;
	}
	public ArchiveNews(Integer nid, Integer ntid, String ntitle, String nauthor, Date createDate, String npicpath,
			String ncontent, Date nmodifyDate, String nsummary) {
		super();
		this.nid = nid;
		this.ntid = ntid;
		this.ntitle = ntitle;
		this.nauthor = nauthor;
		this.ncreateDate = createDate;
		this.npicpath = npicpath;
		this.ncontent = ncontent;
		this.nmodifyDate = nmodifyDate;
		this.nsummary = nsummary;
	}
	@Override
	public String toString() {
		return "News [nid=" + nid + ", ntid=" + ntid + ", ntitle=" + ntitle + ", nauthor=" + nauthor + ", createDate="
				+ ncreateDate + ", npicpath=" + npicpath + ", ncontent=" + ncontent + ", nmodifyDate=" + nmodifyDate
				+ ", nsummary=" + nsummary + "]";
	}
	
	
	
}
