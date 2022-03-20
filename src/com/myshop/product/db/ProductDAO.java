package com.myshop.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
