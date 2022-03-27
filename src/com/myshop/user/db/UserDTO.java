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
	private Timestamp regdate;
	
	private int post;
	private String roadAddr;
	private String detailAddr;
	
	private int infoAgree;
	private int emailAgree;

	private int status;
	private Timestamp lastLogin;
	
	private int buyCount;
	private int pCount;
	private int qaCount;
	private int reviewCount;
	private int wishCount;
	private int cartCount;
	
	
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
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public int getpCount() {
		return pCount;
	}
	public void setpCount(int pCount) {
		this.pCount = pCount;
	}
	public int getQaCount() {
		return qaCount;
	}
	public void setQaCount(int qaCount) {
		this.qaCount = qaCount;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
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
	
	
	@Override
	public String toString() {
		return "UserDTO [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", birth="
				+ birth + ", gender=" + gender + ", email=" + email + ", regdate=" + regdate + ", post=" + post
				+ ", roadAddr=" + roadAddr + ", detailAddr=" + detailAddr + ", infoAgree=" + infoAgree + ", emailAgree="
				+ emailAgree + ", status=" + status + ", lastLogin=" + lastLogin + ", buyCount=" + buyCount
				+ ", pCount=" + pCount + ", qaCount=" + qaCount + ", reviewCount=" + reviewCount + ", wishCount="
				+ wishCount + ", cartCount=" + cartCount + "]";
	}
	
	
}