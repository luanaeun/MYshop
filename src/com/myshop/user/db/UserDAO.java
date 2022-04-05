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
				+ "user_buycount, user_pcount, user_qacount, user_reviewcount, user_wishcount, user_cartcount, "
				+ "user_psending, user_pcellok, user_ordercount, user_isadmin) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,0,0,0,0,0,0,0,0,?)";
		
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
		pstmt.setInt(16,  dto.getIsAdmin());
		
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
  
  
  
  // 유저 정보 가져오는 메서드
  public UserDTO getUserInfo(String userid) {
	  
	  UserDTO dto = null;
	  
	  try {
			con = getCon();
			
			sql = "select user_idx, user_id, user_name, user_birth, user_gender, user_phone, user_email, "
					+ "user_post, user_roadaddr, user_detailaddr, "
					+ "user_buycount, user_pcount, user_wishcount, user_cartcount, user_qacount, user_reviewcount, "
					+ "user_psending, user_pcellok, user_ordercount "
					+ "from myshop_user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDTO();	// 객체는 미리만들지 말고 필요할때 생성하자!!
				
				dto.setIdx(rs.getInt("user_idx"));
				dto.setId(rs.getString("user_id"));
				dto.setName(rs.getString("user_name"));
				dto.setBirth(rs.getString("user_birth"));
				dto.setPhone(rs.getString("user_phone"));
				dto.setEmail(rs.getString("user_email"));
				dto.setGender(rs.getString("user_gender"));
				System.out.println("DAO에서 성별 나오는지: " + dto.getGender());

				dto.setPost(rs.getInt("user_post"));
				dto.setRoadAddr(rs.getString("user_roadaddr"));
				dto.setDetailAddr(rs.getString("user_detailaddr"));

				dto.setBuyCount(rs.getInt("user_buycount"));
				dto.setpCount(rs.getInt("user_pcount"));
				dto.setWishCount(rs.getInt("user_wishcount"));
				dto.setCartCount(rs.getInt("user_cartcount"));
				dto.setQaCount(rs.getInt("user_qacount"));
				dto.setReviewCount(rs.getInt("user_reviewcount"));
				
				dto.setpSending(rs.getInt("user_psending"));
				dto.setpCellOk(rs.getInt("user_pcellok"));
				dto.setOrderCount(rs.getInt("user_ordercount"));
		
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	  return dto;
  }
  
  
  // 회원 탈퇴 메서드
  public int userDelete(Timestamp dropDay, String id, String pw){
	  int result = 0;
	  try {
			con = getCon();

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
  
  
  // 유저 정보 수정 메서드
  public int updateUserInfo(UserDTO dto, String original_id) {
	System.out.println("DAO: updateUserInfo() 호출");
	int result = 0;
	try {
		con = getCon();
		
		sql = "update myshop_user set user_id=?, user_name=?, user_birth=?, user_gender=?, "
				+ "user_phone=?, user_email=?, user_post=?, user_roadaddr=?, user_detailaddr=?, user_emailagree=? "
				+ "where user_id=?";

		pstmt = con.prepareStatement(sql);
			
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getName());
		pstmt.setString(3, dto.getBirth());
		pstmt.setString(4, dto.getGender());
			
		pstmt.setString(5, dto.getPhone());
		pstmt.setString(6, dto.getEmail());
		pstmt.setInt(7, dto.getPost());
		pstmt.setString(8, dto.getRoadAddr());
		pstmt.setString(9, dto.getDetailAddr());
		pstmt.setInt(10, dto.getEmailAgree());
		
		pstmt.setString(11, original_id);
			
		pstmt.executeUpdate();
		System.out.println("DAO: 회원정보 수정 완료");
		result = 1;
			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}
	System.out.println("DAO : updateUserInfo() 끝!");
	return result;
  }
  
  
  // 비밀번호가 맞는지 체크 메서드
  public int userPwCheck(UserDTO dto) {
	  int result = 0;
	  try {
			con = getCon();
			
			sql = "select user_pw from myshop_user where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("user_pw"))) {
					result = 1;
				} else {
					result = 0;
				}
	 		} else {
	 			result = -1;
	 		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	  
	  return result;
  }
  
  
  
} // DAO 끝
	

