package com.myshop.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.myshop.board.db.NoticeDTO;
import com.myshop.user.db.UserDTO;

public class ProductDAO {
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  String sql = "";
  
  
  private Connection getCon() throws Exception {

    Context initCTX = new InitialContext();
	DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/myshop");
	con = ds.getConnection();
	System.out.println("DAO: 1.2.디비연결 성공 " + con);
	  
	return con;
  }
  
  
  public void closeDB() {
	try {
	  if(rs != null)	rs.close();
	  if(pstmt != null) pstmt.close();
	  if(con != null) 	con.close();	
	} catch (SQLException e) {
	  e.printStackTrace();
	}
  }
  
  
  // 카테고리 가져오기 메서드
  public LinkedHashMap bringCategory() {
	  System.out.println("DAO: bringCategory 호출");
	  LinkedHashMap totalCate = new LinkedHashMap();
	  
	  int cateCount = 0;
	  
	  try {
		  	// top카테고리의 개수를 알아본다.
			con = getCon();
			sql = "select count(*) from top_category";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) { cateCount = rs.getInt(1); }
			
			
			for(int i=1; i<=cateCount; i++){
				ArrayList detailCate = new ArrayList();
				
				// 탑 카테고리 
				String topName = "";
				sql = "select topc_name from top_category where topc_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				if(rs.next()) { topName = rs.getString(1); }
				
				
				// 탑 카테고리를 가진 세부 카테고리 가져오기
				sql = "select dct_name from detail_category where dct_topcate=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, topName);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					detailCate.add(rs.getString("dct_name"));
				}
				totalCate.put(topName, detailCate);
				
			}
//			for (Object i : totalCate.keySet()) {
//		        System.out.println("key: " + i + " value: " + totalCate.get(i));
//		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	  	System.out.println("카테고리 가져오는 함수 끝!");
	  	return totalCate;
	  	
  }
  
  
  // 제품등록 메서드 
  public void addProduct(ProductDTO dto) {
	System.out.println("DAO: addProduct(dto) 호출");
	int pnum = 0;
	
	try {
		con = getCon();
		
		sql = "select count(*) from product_info";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			pnum = rs.getInt(1)+1;
		}
		
		sql = "insert into product_info values(?,?,?,?,?,?,now(), ?,?,0,0, ?,?,?,?, ?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, pnum);
		pstmt.setString(2, dto.getUserid());
		pstmt.setString(3, dto.getCategory());
		pstmt.setString(4, dto.getName());
		pstmt.setInt(5, dto.getPrice());
		pstmt.setInt(6, dto.getStock());
		
		pstmt.setString(7, dto.getSumbnail());
		pstmt.setString(8, dto.getContent());
		// p_viewcount, p_wishcount 이 두개는 0으로 저장. 
		
		
		int len = dto.getImages().size();
		for(int i=0; i<4; i++) {
			System.out.printf("리스트 길이: %d, i값: %d, j값: %d", len, i, i+9);
			if(i <= len) { pstmt.setString(i+9, (String)dto.getImages().get(i)); }
			else { pstmt.setString(i+9, ""); }
		}
		
		pstmt.setInt(13, dto.getDeliCharge());
		pstmt.setString(14, dto.getHowDeli());
		pstmt.setString(15, dto.getDeliDays());
		pstmt.setString(16, dto.getIp());
		
		pstmt.executeUpdate();
		System.out.println("DAO: 제품등록 완료");
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	System.out.println("DAO : AddProduct() 끝!");
  }
	
  
  // 제품 가져오기 메서드
  public ArrayList getProductList() {
	System.out.println("DAO: 제품 가져오는 메소드 들어옴!");
	ArrayList prodList = new ArrayList();
		
		try {
			con = getCon();
			// 글 자르기: limit
			// 글 정렬: re_ref(내림차순) / re_seq(오름차순)
			// sql = "select * from myshop_notice order by re_ref desc, re_seq asc limit ?,?";
			sql = "select * from product_info order by p_rgdate desc";
			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, startRow -1);
//			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 글1개 정보는 DTO에 담아서 저장. -> DTO정보를 List 한 칸에 저장. 
				ProductDTO dto = new ProductDTO();
				
				dto.setNum(rs.getInt("p_idx"));
				dto.setUserid(rs.getString("p_userid"));
				
				dto.setName(rs.getString("p_name"));
				dto.setPrice(rs.getInt("p_price"));
				dto.setCategory(rs.getString("p_category"));
				dto.setStock(rs.getInt("p_stock"));
				dto.setContent(rs.getString("p_content"));
				dto.setRegdate(rs.getTimestamp("p_rgdate"));
				
				dto.setDeliCharge(rs.getInt("p_delicharge"));
				dto.setDeliDays(rs.getString("p_delidays"));
				dto.setHowDeli(rs.getString("p_howdelivery"));
				
				dto.setIp(rs.getString("p_userid"));
				
				dto.setSumbnail(rs.getString("p_sumbnail"));
				
				ArrayList images = new ArrayList<>();
				images.add(rs.getString("p_img01"));
				images.add(rs.getString("p_img02"));
				images.add(rs.getString("p_img03"));
				images.add(rs.getString("p_img04"));
				dto.setImages(images);
				
				dto.setViewCount(rs.getInt("p_viewcount"));
				dto.setWishCount(rs.getInt("p_wishcount"));
				
				// List 한칸에 저장.
				prodList.add(dto);
			}
			System.out.println("DAO: 제품 목록 가져오기 완료(List)");
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	  
	  
	  return prodList;
  }
  
  
  // 제퓸 조회수 증가 메서드
  public void updateProductView(int num) {
	System.out.println("조회수 증가 메서드 호출");
	try {
		con = getCon();
		sql = "update product_info set p_viewcount = p_viewcount + 1 where p_idx=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
			
		System.out.println("DAO: 조회수 1증가 완료!");
			
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
  }
  
  
  
  // 제품 세부 정보 가져오기
  public ProductDTO getDetailProduct(int num) {
	  System.out.println("제품 세부 정보 가져오는 메서드");
	  ProductDTO dto = null;
	  
	  try {
		con = getCon();
		sql = "select * from product_info where p_idx = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
			
		if(rs.next()) {
			dto = new ProductDTO();
				
			dto.setNum(rs.getInt("p_idx"));
			dto.setUserid(rs.getString("p_userid"));
				
			dto.setName(rs.getString("p_name"));
			dto.setPrice(rs.getInt("p_price"));
			dto.setCategory(rs.getString("p_category"));
			dto.setStock(rs.getInt("p_stock"));
			dto.setContent(rs.getString("p_content"));
			dto.setRegdate(rs.getTimestamp("p_rgdate"));
				
			dto.setDeliCharge(rs.getInt("p_delicharge"));
			dto.setDeliDays(rs.getString("p_delidays"));
			dto.setHowDeli(rs.getString("p_howdelivery"));
				
			dto.setIp(rs.getString("p_userid"));
				
			dto.setSumbnail(rs.getString("p_sumbnail"));
				
			ArrayList images = new ArrayList<>();
			images.add(rs.getString("p_img01"));
			images.add(rs.getString("p_img02"));
			images.add(rs.getString("p_img03"));
			images.add(rs.getString("p_img04"));
			dto.setImages(images);
				
			dto.setViewCount(rs.getInt("p_viewcount"));
			dto.setWishCount(rs.getInt("p_wishcount"));
		}
		System.out.println("가져온거: " + dto);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	return dto;
  }

  
} // 전체 메서드

  
  
  
  
  

