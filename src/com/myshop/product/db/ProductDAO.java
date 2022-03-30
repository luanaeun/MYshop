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
import javax.sql.DataSource;

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
  public void registerProduct(ProductDTO dto) {
	System.out.println("DAO: registerProduct(dto) 호출");
	
	try {
		con = getCon();
		sql = "insert into itwill_member(id, pw, name, birth, gender, phone, email, regdate) "
				+ "values(?,?,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPw());
		pstmt.setString(3, dto.getName());
		pstmt.setInt(4, dto.getBirth());
		pstmt.setString(5, dto.getGender());
		pstmt.setInt(6, dto.getPhone());
		pstmt.setString(7, dto.getEmail());
		pstmt.setTimestamp(8, dto.getRegdate());
		
		pstmt.executeUpdate();
		System.out.println("DAO: 제품등록 완료");
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	System.out.println("DAO : registerProduct() 끝!");
  }
	
  
} // 전체 메서드
