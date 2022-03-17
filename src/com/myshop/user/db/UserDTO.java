package com.myshop.user.db;

import java.sql.Timestamp;

public class UserDTO {
	// member 테이블 정보를 한번에 저장하는 객체
	
	private int idx;
	private String id;
	private String pw;
	private String name;
	private int phone;
	private int birth;
	private String gender;
	private String email;
	private Timestamp regdate;
	private Timestamp lastLogin;
	private String state;
	
	private String agree;
	private int buyCount;
	private int prodCount;
	private int qaCount;
	private int reviewCount;
	private int likeCount;
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
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public int getProdCount() {
		return prodCount;
	}
	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
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
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
	
	
	@Override
	public String toString() {
		return "UserDTO [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", birth="
				+ birth + ", gender=" + gender + ", email=" + email + ", regdate=" + regdate + ", state=" + state
				+ ", agree=" + agree + ", buyCount=" + buyCount + ", prodCount=" + prodCount + ", qaCount=" + qaCount
				+ ", reviewCount=" + reviewCount + ", likeCount=" + likeCount + ", cartCount=" + cartCount + "]";
	}
	
	
	
	

	
}