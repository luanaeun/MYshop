package com.myshop.board.db;

import java.sql.Date;
import java.sql.Timestamp;

public class NoticeDTO {
	private int num;
	private String name;
	private String pw;
	private String title;
	private String content;
	private int viewcount;
	private Timestamp rgdate;
	private String ip;

	private String img;
	private String file;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public Timestamp getRgdate() {
		return rgdate;
	}
	public void setRgdate(Timestamp rgdate) {
		this.rgdate = rgdate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	
	@Override
	public String toString() {
		return "NoticeDTO [num=" + num + ", name=" + name + ", pw=" + pw + ", title=" + title + ", content=" + content
				+ ", viewcount=" + viewcount + ", rgdate=" + rgdate + ", ip=" + ip + ", img=" + img
				+ ", file=" + file + "]";
	}
	
	
	
}
