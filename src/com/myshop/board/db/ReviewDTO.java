package com.myshop.board.db;

import java.sql.Timestamp;

public class ReviewDTO {
	private int vidx;
	private int pidx;
	
	private String author;
	private String title;
	private String content;
	private float score;
	private int viewcount;
	private Timestamp rgdate;
	private String ip;

	private String img;

	public int getVidx() {
		return vidx;
	}

	public void setVidx(int vidx) {
		this.vidx = vidx;
	}

	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
	
	

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
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
	
	
}
