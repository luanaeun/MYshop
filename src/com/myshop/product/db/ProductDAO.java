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
	System.out.println("DAO: 디비연결 성공");
	  
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
	  
	int topCateCount = 0;
	  
	try {
		// top카테고리의 개수를 알아본다.
		con = getCon();
		sql = "select count(*) from top_category";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) { topCateCount = rs.getInt(1); }
			
		
		if(topCateCount > 0) {
			for(int i=1; i<=topCateCount; i++){		// top카테고리 개수만큼 반복문 돌기.
				ArrayList detailCate = new ArrayList();
				// 탑 카테고리 
				String topName = "";
				sql = "select topcate_name from top_category where topcate_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, i);
				rs = pstmt.executeQuery();
				if(rs.next()) { topName = rs.getString(1); }
				
				// 탑 카테고리를 가진 세부 카테고리 가져오기
				sql = "select dcate_name from detail_category where dcate_topcate=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, topName);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					detailCate.add(rs.getString("dcate_name"));
				}
				totalCate.put(topName, detailCate);
			}
		} else {
			totalCate.put("전체", "");
		}
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
		
		sql = "select max(p_idx) from product_info";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) { pnum = rs.getInt(1)+1; }
		
		
		sql = "insert into product_info values(?,?,?,?,?,?,now(), ?,?,0,0, ?,?,?,?, ?,?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, pnum);
		pstmt.setInt(2, dto.getUseridx());
		pstmt.setString(3, dto.getCategory());
		pstmt.setString(4, dto.getName());
		pstmt.setInt(5, dto.getPrice());
		pstmt.setInt(6, dto.getStock());
		
		pstmt.setString(7, dto.getSumbnail());
		pstmt.setString(8, dto.getContent());
		// p_viewcount, p_wishcount 이 두개는 0으로 저장. 
		System.out.println("여기서 오류가 생기나?");
		for(int i=0; i<4; i++) {
			if(dto.getImages().get(i) != null) { pstmt.setString(i+9, (String)dto.getImages().get(i)); }
			else { pstmt.setString(i+9, null); }
		}
		
		pstmt.setInt(13, dto.getDeliCharge());
		pstmt.setString(14, dto.getHowDeli());
		pstmt.setString(15, dto.getDeliDays());
		pstmt.setString(16, dto.getIp());
		pstmt.setString(17, dto.getColor());
		
		pstmt.executeUpdate();
		System.out.println("DAO: 제품등록 완료");
		
		
		// 유저 정보에서 내 상품 개수 +1 하기
		sql = "update myshop_user set user_pcount = user_pcount + 1 where user_idx=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dto.getUseridx());
		pstmt.executeUpdate();
		
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	System.out.println("DAO : AddProduct() 끝!");
  }
	
  
  
  // 제품 총 개수 구하기 메서드
  public int getTodayProductCount(String detail) {
	int result = 0;
	try {
		con = getCon();
		sql = "select count(*) from product_info where DATE_FORMAT(p_rgdate, '%Y-%m-%d')=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, detail);
		System.out.println("DAO : 쿼리 완성본: " + sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			result = rs.getInt(1); //만약 컬럼명으로 스고싶으면 "count(*)"이렇게 쓰면된다. 
		}
		System.out.println("DAO : 오늘 들어온 상품 총 개수 -> " + result);
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}		
	return result;
  }
  
  
  // 오늘 제품 가져오기 메서드
  public ArrayList getTodayProductList(int startRow, int pageSize, String today) {
	System.out.println("DAO: 제품 가져오는 메소드 들어옴!");
	ArrayList prodList = new ArrayList();
		
		try {
			con = getCon();

			sql = "select * from product_info where DATE_FORMAT(p_rgdate, '%Y-%m-%d')=? order by p_rgdate desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, today);
			pstmt.setInt(2, startRow -1);
			pstmt.setInt(3, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 글1개 정보는 DTO에 담아서 저장. -> DTO정보를 List 한 칸에 저장. 
				ProductDTO dto = new ProductDTO();
				
				dto.setNum(rs.getInt("p_idx"));
				dto.setUseridx(Integer.parseInt(rs.getString("p_useridx")));
				
				dto.setName(rs.getString("p_name"));
				dto.setPrice(rs.getInt("p_price"));
				dto.setCategory(rs.getString("p_category"));
				dto.setStock(rs.getInt("p_stock"));
				dto.setContent(rs.getString("p_content"));
				dto.setSumbnail(rs.getString("p_sumbnail"));
				dto.setRegdate(rs.getTimestamp("p_rgdate"));
				
				dto.setDeliCharge(rs.getInt("p_delicharge"));
				dto.setDeliDays(rs.getString("p_delidays"));
				dto.setHowDeli(rs.getString("p_howdelivery"));
				
				dto.setViewCount(rs.getInt("p_viewcount"));
				dto.setWishCount(rs.getInt("p_wishcount"));
				
				ArrayList images = new ArrayList<>();
				for(int i=1; i<5; i++) {
					String temp_img = rs.getString("p_img0"+i);
					if(temp_img != null) {
						images.add(temp_img);
					}
				}
				dto.setImages(images);
				
				
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
  
  
  
  // 카테고리별 상품 개수
  public int getCateProductCount(String cate) {
	int result = 0;
	try {
		con = getCon();
		if(cate.equals("전체")) {
			sql = "select count(*) from product_info";
			pstmt = con.prepareStatement(sql);
		} else {
			sql = "select count(*) from product_info where p_category=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cate);
		}
		System.out.println("DAO : 쿼리 완성본: " + sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			result = rs.getInt(1); //만약 컬럼명으로 스고싶으면 "count(*)"이렇게 쓰면된다. 
		}
		System.out.println("DAO : 오늘 들어온 상품 총 개수 - " + result);
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}		
		return result;
  }
  
  
  
  // 상품 카테고리 별로가져오기 메서드
  public ArrayList getCateProductList(int startRow, int pageSize, String cate) {
	System.out.println("DAO: 카테고리별로 제품 가져오는 메소드 들어옴!");
	ArrayList prodList = new ArrayList();
			
	try {
		con = getCon();
		
		if(cate.equals("전체")) {
			System.out.println("전체카테고리야!!");
			sql = "select * from product_info order by p_rgdate desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow -1);
			pstmt.setInt(2, pageSize);
		} else {
			sql = "select * from product_info where p_category=? order by p_rgdate desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cate);
			pstmt.setInt(2, startRow -1);
			pstmt.setInt(3, pageSize);
		}
		rs = pstmt.executeQuery();
				
		while(rs.next()) {
			// 글1개 정보는 DTO에 담아서 저장. -> DTO정보를 List 한 칸에 저장. 
			ProductDTO dto = new ProductDTO();
					
			dto.setNum(rs.getInt("p_idx"));
			dto.setUseridx(Integer.parseInt(rs.getString("p_useridx")));
					
			dto.setName(rs.getString("p_name"));
			dto.setPrice(rs.getInt("p_price"));
			dto.setSumbnail(rs.getString("p_sumbnail"));
			dto.setRegdate(rs.getTimestamp("p_rgdate"));


			dto.setViewCount(rs.getInt("p_viewcount"));
			dto.setWishCount(rs.getInt("p_wishcount"));
			
			ArrayList images = new ArrayList<>();
			for(int i=1; i<5; i++) {
				String temp_img = rs.getString("p_img0"+i);
				if(temp_img != null) {
					images.add(temp_img);
				}
			}
			dto.setImages(images);
					
	
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
			dto.setUseridx(Integer.parseInt(rs.getString("p_useridx")));
				
			dto.setName(rs.getString("p_name"));
			dto.setPrice(rs.getInt("p_price"));
			dto.setCategory(rs.getString("p_category"));
			dto.setStock(rs.getInt("p_stock"));
			dto.setColor(rs.getString("p_color"));
			dto.setContent(rs.getString("p_content"));
			dto.setSumbnail(rs.getString("p_sumbnail"));
			dto.setRegdate(rs.getTimestamp("p_rgdate"));
				
			dto.setDeliCharge(rs.getInt("p_delicharge"));
			dto.setDeliDays(rs.getString("p_delidays"));
			dto.setHowDeli(rs.getString("p_howdelivery"));
				
			dto.setViewCount(rs.getInt("p_viewcount"));
			dto.setWishCount(rs.getInt("p_wishcount"));
				
			ArrayList images = new ArrayList<>();
			for(int i=1; i<5; i++) {
				String temp_img = rs.getString("p_img0"+i);
				if(temp_img != null) {
					images.add(temp_img);
				}
			}
			dto.setImages(images);
				
			
		}
		System.out.println("가져온거: " + dto);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	return dto;
  }

  
  // 제품 수정 메서드
  public void updateProduct(ProductDTO dto) {
	System.out.println("DAO: updateProduct(dto) 호출");
	int pnum = 0;
		
	try {
		con = getCon();
			
		sql = "update product_info set p_category=?, p_name=?, p_price=?, p_stock=?, p_rgdate=now(), p_sumbnail=?, p_content=?, "
				+ "p_img01=?, p_img02=?, p_img03=?, p_img04=?, p_delicharge=?, p_howdelivery=?, p_delidays=? p_ip=?"
				+ "where p_idx=?";      
		pstmt = con.prepareStatement(sql);
			
		pstmt.setString(1, dto.getCategory());
		pstmt.setString(2, dto.getName());
		pstmt.setInt(3, dto.getPrice());
		pstmt.setInt(4, dto.getStock());
			
		pstmt.setString(5, dto.getSumbnail());
		pstmt.setString(6, dto.getContent());
		
			
		int len = dto.getImages().size();
		int i = 0;
		for(i=0; i<len; i++) {
			//System.out.printf("리스트 길이: %d, i값: %d, j값: %d", len, i, i+9);
			if(i <= len) { pstmt.setString(i+7, (String)dto.getImages().get(i)); }
		}
		int j = i+7;
			
		pstmt.setInt(j+1, dto.getDeliCharge());
		pstmt.setString(j+2, dto.getHowDeli());
		pstmt.setString(j+3, dto.getDeliDays());
		pstmt.setString(j+4, dto.getIp());
			
		pstmt.executeUpdate();
		System.out.println("DAO: 제품수정 완료");
			
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	System.out.println("DAO : AddProduct() 끝!");
  }
		
  
  
  // 제품 삭제 메서드
  public int deleteProduct(String userid, int num) {
	  System.out.println("상품 삭제 메서드: " + userid);
	  int result = 0;
	  int count = 0;
	  try {
		con = getCon();

		sql = "delete from product_info where p_idx=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		result = pstmt.executeUpdate();
		
		
		
		// 회원의 상품 개수를 -1 하기
		 sql = "update myshop_user set user_pcount = user_pcount -1 where user_id=?";
//		sql = "update myshop_user set user_pcount = "
//				+ "(select * from (select user_pcount from myshop_user a where user_id=?)as a)-1 where user_id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userid);
		result = pstmt.executeUpdate();
			
		System.out.println("제품 삭제 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	  
	  return result;
  }
  
  
} // 전체 메서드

  
  
  
  
  

