package com.myshop.board.db;

import java.sql.Timestamp;

public class CommentDTO {
	int idx;
	int pidx;
	String comment;
	String userid;
	Timestamp rgdate;
	
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPidx() {
		return pidx;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Timestamp getRgdate() {
		return rgdate;
	}
	public void setRgdate(Timestamp rgdate) {
		this.rgdate = rgdate;
	}
	
	
}
