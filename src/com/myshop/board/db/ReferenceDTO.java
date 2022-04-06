package com.myshop.board.db;

import java.sql.Timestamp;

public class ReferenceDTO {
	int idx;
	String author;
	String title;
	private String file;
	private int downcount;
	private Timestamp rgdate;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getDowncount() {
		return downcount;
	}
	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}
	public Timestamp getRgdate() {
		return rgdate;
	}
	public void setRgdate(Timestamp rgdate) {
		this.rgdate = rgdate;
	}
	
	
	
}
