package com.myshop.user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.myshop.user.db.UserDTO;

public class UserDAO {
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
  
  
  // 회원가입 메서드 
  public void userSignUp(UserDTO dto) {
	System.out.println("DAO: signUpUser(dto) 호출");
	int uNum = 0;
	
	try {
		con = getCon();
		
		// 회원이 몇명있는지 알아보고 idx를 하나 늘리기
		sql = "select max(user_idx) from myshop_user";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			// getInt()는 컬럼 인덱스를 가져오는거. bno = 
			uNum = rs.getInt(1)+1;
		}
		
		
		sql = "insert into myshop_user(user_idx, user_id, user_pw, user_name, user_birth, user_gender, "
				+ "user_phone, user_email, user_post, user_roadaddr, user_detailaddr, "
				+ "user_rgdate, user_status, user_infoagree, user_emailagree, "
				+ "user_buycount, user_pcount, user_qacount, user_reviewcount, user_wishcount, user_cartcount) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,0,0,0,0,0)";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, uNum);
		pstmt.setString(2, dto.getId());
		pstmt.setString(3, dto.getPw());
		pstmt.setString(4, dto.getName());
		pstmt.setString(5, dto.getBirth());
		pstmt.setString(6, dto.getGender());
		
		pstmt.setString(7, dto.getPhone());
		pstmt.setString(8, dto.getEmail());
		pstmt.setInt(9, dto.getPost());
		pstmt.setString(10, dto.getRoadAddr());
		pstmt.setString(11, dto.getDetailAddr());
		
		pstmt.setTimestamp(12, dto.getRegdate());
		pstmt.setInt(13, dto.getStatus());
		pstmt.setInt(14, dto.getInfoAgree());
		pstmt.setInt(15, dto.getEmailAgree());
		
		pstmt.executeUpdate();
		System.out.println("DAO: 회원가입 완료");
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	System.out.println("DAO : registerUser() 끝!");
  }
  
  
  
  // 로그인 메서드
  public int userSignIn(UserDTO dto){
	  int loginResult = -1;
	  
	  try {
			con = getCon();
			
			// 아이디로 비밀번호 가져오기
			sql = "select user_pw from myshop_user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
	 			if(dto.getPw().equals(rs.getString("user_pw")) ) {	
	 				sql = "update myshop_user set user_lastlogin=? where user_id=?";
	 				pstmt = con.prepareStatement(sql);
	 				pstmt.setTimestamp(1, dto.getLastLogin());
	 				pstmt.setString(2, dto.getId());
	 				pstmt.executeUpdate();
	 				
	 				loginResult = 1;
	 			} else {	
	 				loginResult = 0;
	 			}
	 		} else {	// 데이터가 없을때 -> 비회원
	 			loginResult = -1;
	 		}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	  
		return loginResult;
  }
  
  
  // 아이디 중복체크 메서드
  public int userIdCheck(String tempID){
	  int result = 0;
	  
	  try {
			con = getCon();
			
			sql = "select user_id from myshop_user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tempID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
	 			result = 0;
	 		} else {
	 			result = 1;
	 		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
  }
  
  
  
  
  // 회원 탈퇴 메서드
  public int userDelete(Timestamp dropDay, String id, String pw){
	  int result = 0;
	  try {
			con = getCon();
			// 로그인할때처럼 비번이 맞는지 먼저 확인해야겠지..?
			
			
			// 회원을 -> 탈퇴로 변경!!
			sql = "update myshop_user set user_status=?, user_id=?, user_lastlogin=? where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setString(2, "탈퇴");
			pstmt.setTimestamp(3, dropDay);
			pstmt.setString(4, id);
			result = pstmt.executeUpdate();
			
			System.out.println("회원탈퇴 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	  
	  return result;
  }
  
  
  
} // DAO 끝
	

