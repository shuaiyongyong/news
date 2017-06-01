package com.yc.news.entity;

import java.util.Date;

public class Comment {
	private Integer cid;
	private Integer cnid;
	private String ccontent;
	private Date cdate = new Date();
	private String cip;
	private String cauthor;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCnid() {
		return cnid;
	}
	public void setCnid(Integer cnid) {
		this.cnid = cnid;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	public String getCauthor() {
		return cauthor;
	}
	public void setCauthor(String cauthor) {
		this.cauthor = cauthor;
	}
	@Override
	public String toString() {
		return "\nComment [cid=" + cid + ", cnid=" + cnid + ", ccontent=" + ccontent + ", cdate=" + cdate + ", cip=" + cip
				+ ", cauthor=" + cauthor + "]";
	}
	
	
}
