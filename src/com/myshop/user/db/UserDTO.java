package com.myshop.user.db;

import java.sql.Timestamp;

public class UserDTO {
	// member 테이블 정보를 한번에 저장하는 객체
	
	private int idx;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String birth;
	private String gender;
	private String email;
	private String userimg;
	private Timestamp regdate;
	
	private int post;
	private String roadAddr;
	private String detailAddr;
	
	private int infoAgree;
	private int emailAgree;

	private int status;
	private Timestamp lastLogin;
	
	
	private int pCount;
	private int pSending;
	private int pCellOk;

	private int wishCount;
	private int cartCount;
	private int buyCount;
	private int orderCount;
	
	private int isAdmin;
	

	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public String getRoadAddr() {
		return roadAddr;
	}

	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public int getInfoAgree() {
		return infoAgree;
	}

	public void setInfoAgree(int infoAgree) {
		this.infoAgree = infoAgree;
	}

	public int getEmailAgree() {
		return emailAgree;
	}

	public void setEmailAgree(int emailAgree) {
		this.emailAgree = emailAgree;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getpCount() {
		return pCount;
	}

	public void setpCount(int pCount) {
		this.pCount = pCount;
	}

	public int getpSending() {
		return pSending;
	}

	public void setpSending(int pSending) {
		this.pSending = pSending;
	}

	public int getpCellOk() {
		return pCellOk;
	}

	public void setpCellOk(int pCellOk) {
		this.pCellOk = pCellOk;
	}

	public int getWishCount() {
		return wishCount;
	}

	public void setWishCount(int wishCount) {
		this.wishCount = wishCount;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	
	
	
	
	
}