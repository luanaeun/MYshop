package com.myshop.manage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CategoryDAO {
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
	  
	  
	  // 하위 카테고리 추가
	  public int addDetailCate(String topName, String cateName) {
		int result = 0;
		int num = 0;
		
		try {
			con = getCon();
			sql = "select max(dct_idx) from detail_category";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// getInt()는 컬럼 인덱스를 가져오는거.
				num = rs.getInt(1)+1;
			}

			sql = "insert into detail_category values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, cateName);
			pstmt.setString(3, topName);
			pstmt.executeUpdate();
			
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		  return result;
	  }
	  
}
