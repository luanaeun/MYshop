package com.myshop.product.db;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ProductDTO {
	// product_info 테이블 정보를 한번에 저장하는 객체
	
	private int num;
	private String userid;
	
	private String name;
	private int price;
	private int stock;
	private String category;
	
	private String deliDays;
	private int deliCharge;
	private String howDeli;
	
	private String content;
	private Timestamp regdate;
	private String ip;
	
	private String sumbnail;
	private ArrayList images;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeliDays() {
		return deliDays;
	}

	public void setDeliDays(String deliDays) {
		this.deliDays = deliDays;
	}

	public int getDeliCharge() {
		return deliCharge;
	}

	public void setDeliCharge(int deliCharge) {
		this.deliCharge = deliCharge;
	}

	public String getHowDeli() {
		return howDeli;
	}

	public void setHowDeli(String howDeli) {
		this.howDeli = howDeli;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getSumbnail() {
		return sumbnail;
	}

	public void setSumbnail(String sumbnail) {
		this.sumbnail = sumbnail;
	}

	public ArrayList getImages() {
		return images;
	}

	public void setImages(ArrayList images) {
		this.images = images;
	}
	
	
	

}