package com.yc.news.entity;

public class User {
	private Integer usid;
	private String uname;
	private String upwd;

	public User() {
	}

	public User(Integer usid, String uname, String upwd) {
		super();
		this.usid = usid;
		this.uname = uname;
		this.upwd = upwd;
	}

	@Override
	public String toString() {
		return "User [usid=" + usid + ", uname=" + uname + ", upwd=" + upwd + "]";
	}

	public Integer getUsid() {
		return usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

}
