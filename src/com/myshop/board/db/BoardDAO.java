package com.myshop.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	// DB에 관련된 모든것을 처리하는 객체.
	
	// 연결 레퍼런스
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 디비 연결 동작 -> 이미 MemberDAO에서 만들어서 복붙해도되는데 공부를 위해 다시 적어보자~
	private Connection getCon() throws Exception {
		
		// 외부파일 불러오기(META-INF/context.xml)
		Context ctxInit = new InitialContext();
		DataSource ds = (DataSource) ctxInit.lookup("java:comp/env/jdbc/myshop");
		con = ds.getConnection();
		
		System.out.println("DAO: 1,2 디비 연결 완료!");
		return con;
	}
	
	
	// 디비 자원해제
	public void CloseDB() {
		try {
		  if(rs != null)	rs.close();
		  if(pstmt != null) pstmt.close();
		  if(con != null) 	con.close();	
		} catch (SQLException e) {
		  e.printStackTrace();
		}
	}
	
	
	// insertBoard(DTO) -> 외부에서 접근 가능해야되서 public
	public void writeNotice(NoticeDTO dto) {
		int bno = 0;
		try {
			con = getCon();
			sql = "select max(n_idx) from myshop_notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bno = rs.getInt(1)+1;
			}
			System.out.println("DAO : 글번호:("+bno+ ")");
			
			sql = "insert into myshop_notice(n_idx, n_userid, n_pw, n_title, n_content, n_rgdate, n_ip, n_viewcount,n_img,n_file) "
					+ "values(?,?,?,?,?,now(),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			// ???
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getIp());
			
			pstmt.setInt(7, 0); 
			pstmt.setString(8, dto.getImg()); 
			pstmt.setString(9, dto.getFile());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글쓰기 완료! ");	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	
	
	// getBoardCount() => 공지 글 개수 알아보기.
	public int getNoticeCount() {
		System.out.println("공지 글 카운트 함수");
		int result = 0;
		try {
			con = getCon();
			sql = "select count(*) from myshop_notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1); //만약 컬럼명으로 스고싶으면 "count(*)"이렇게 쓰면된다. 
			}
			System.out.println("DAO : 공지 총 개수 -> " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}		
		return result;
	}

	
	// 기존 getBoardList() 오버로딩
	public ArrayList getNoticeList(int startRow, int pageSize) {
		System.out.println("DAO: 공지 가져오는 메소드 들어옴!");
		ArrayList boardList = new ArrayList();
		
		try {
			con = getCon();
			// 글 자르기: limit
			// 글 정렬: re_ref(내림차순) / re_seq(오름차순)
			// sql = "select * from myshop_notice order by re_ref desc, re_seq asc limit ?,?";
			sql = "select * from myshop_notice limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow -1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 글1개 정보는 DTO에 담아서 저장. -> DTO정보를 List 한 칸에 저장. 
				NoticeDTO dto = new NoticeDTO();
				
				
				dto.setNum(rs.getInt("n_idx"));
				dto.setName(rs.getString("n_userid"));
				dto.setPw(rs.getString("n_pw"));
				dto.setTitle(rs.getString("n_title"));
				dto.setContent(rs.getString("n_content"));
				dto.setRgdate(rs.getTimestamp("n_rgdate"));
				dto.setViewcount(rs.getInt("n_viewcount"));
				dto.setIp(rs.getString("n_ip"));
				dto.setImg(rs.getString("n_img"));
				dto.setFile(rs.getString("n_file"));
				
				// List 한칸에 저장.
				boardList.add(dto);
			}
			System.out.println("DAO: 공지 목록 가져오기 완료(List)");
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return boardList;
	}
	

	
	// 글 조회수 증가 메서드
	public void updateViewCount(int num) {
		System.out.println("조회수 증가 메서드 호출");
		try {
			con = getCon();
			sql = "update myshop_notice set n_viewcount = n_viewcount + 1 where n_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			System.out.println("DAO: 조회수 1증가 완료!");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}

	
	// 글 하나의 정보 가져오기
	public NoticeDTO getNoticeDetail(int num) {
		System.out.println("글 하나의 정보 가져오는 메서드 호출");
		NoticeDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from myshop_notice where n_idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				dto = new NoticeDTO();	// 객체는 미리만들지 말고 필요할때 생성하자!!
								
				dto.setNum(rs.getInt("n_idx"));
				dto.setName(rs.getString("n_userid"));
				dto.setPw(rs.getString("n_pw"));
				dto.setTitle(rs.getString("n_title"));
				dto.setContent(rs.getString("n_content"));
				dto.setRgdate(rs.getTimestamp("n_rgdate"));
				dto.setViewcount(rs.getInt("n_viewcount"));
				System.out.println("글 하나 가져오기 완료!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return dto;
	}
	
	
//	public int updateBoard(NoticeDTO dto){
//		int result = -1;
//		try {
//			con = getCon();
//			//헤당글이 있는지 체크. 비밀번호는 notnull이기 때문에 비번이 없으면 없는 글이라는 말. 
//			sql = "select pass from itwill_board where num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, dto.getNum());
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()){	// 글이있다
//				if(dto.getPass().equals(rs.getString("pass"))){	// 본인글이 맞다.
//					System.out.printf("글번호: " + dto.getNum());	
//					
//					sql = "update itwill_board set name=?,subject=?,content=? where num=?";
//					pstmt = con.prepareStatement(sql);
//					pstmt.setString(1, dto.getName());
//					pstmt.setString(2, dto.getSubject());
//					pstmt.setString(3, dto.getContent());
//					pstmt.setInt(4, dto.getNum());
//					
//					result = pstmt.executeUpdate();
//
//				} else {
//					result = 0;
//				}
//			} else {
//				result = -1;
//			}
//			
//			System.out.printf("DAO : 글 수정 완료! (%d)",result);	
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			CloseDB();
//		}
//		return result;
//	}  // updateBoard
//	
//	
//	public int deleteBoard(NoticeDTO dto) {
//		System.out.println("삭제 메서드");
//		int result = -1;
//		try {
//			con = getCon();
//			//헤당글이 있는지 체크. 비밀번호는 notnull이기 때문에 비번이 없으면 없는 글이라는 말. 
//			sql = "select pass from itwill_board where num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, dto.getNum());
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()){	// 글이있다
//				if(dto.getPass().equals(rs.getString("pass"))){	// 본인글이 맞다.
//					System.out.printf("글번호: " + dto.getNum());	
//					
//					sql = "delete from itwill_board where num=?";
//					pstmt = con.prepareStatement(sql);
//					pstmt.setInt(1, dto.getNum());
//					
//					result = pstmt.executeUpdate();
//
//				} else {
//					result = 0;
//				}
//			} else {
//				result = -1;
//			}
//			
//			System.out.printf("DAO : 글 삭제 완료! (%d)",result);	
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			CloseDB();
//		}
//		return result;
//	}
	
	
}	// 전체 함수 끝








