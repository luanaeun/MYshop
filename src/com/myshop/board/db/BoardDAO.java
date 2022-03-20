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
			
			sql = "insert into myshop_notice(n_idx, user_id, n_pw, n_title, n_content, n_rgdate, n_ip, n_viewcount,n_imgcount,n_filecount) "
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
			pstmt.setInt(8, 0); 
			pstmt.setInt(9, 0);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글쓰기 완료! ");	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	
	
	
	
	// getBoardCount()
//	public int getBoardCount() {
//		int result = 0;
//		
//		try {
//			con = getCon();
//			sql = "select count(*) from itwill_board";
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				result = rs.getInt(1); //만약 컬럼명으로 스고싶으면 "count(*)"이렇게 쓰면된다. 
//			}
//			System.out.println("DAO : 게시판 총 개수 - " + result);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			CloseDB();
//		}		
//		return result;
//	}
//	
//	
//	
//	// 글 목록 가져오기 getBoardList();
//	public ArrayList getBoardList() {
//		
//		ArrayList boardList = new ArrayList();
//		
//		try {
//			con = getCon();
//			sql = "select * from itwill_board";
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				// 글1개 정보는 DTO에 담아서 저장. -> DTO정보를 List 한 칸에 저장. 
//				NoticeDTO dto = new NoticeDTO();
//				dto.setContent(rs.getString("content"));
//				dto.setDate(rs.getDate("date"));
//				dto.setFile(rs.getString("file"));
//				dto.setIp(rs.getString("ip"));
//				dto.setName(rs.getString("name"));
//				dto.setNum(rs.getInt("num"));
//				dto.setPass(rs.getString("pass"));
//				dto.setRe_lev(rs.getInt("re_lev"));
//				dto.setRe_ref(rs.getInt("re_ref"));
//				dto.setRe_seq(rs.getInt("re_seq"));
//				dto.setReadcount(rs.getInt("readcount"));
//				dto.setSubject(rs.getString("subject"));
//				
//				// List 한칸에 저장.
//				boardList.add(dto);
//			}
//			System.out.println("DAO: 글정보 저장 완료(List)");
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			CloseDB();
//		}
//		
//		return boardList;
//	}
//	
//	
//	
//	// 기존 getBoardList() 오버로딩
//	public ArrayList getBoardList(int startRow, int pageSize) {
//		
//		ArrayList boardList = new ArrayList();
//		
//		try {
//			con = getCon();
//			// 글 자르기: limit
//			// 글 정렬: re_ref(내림차순) / re_seq(오름차순)
//			sql = "select * from itwill_board order by re_ref desc, re_seq asc limit ?,?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, startRow -1);
//			pstmt.setInt(2, pageSize);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				// 글1개 정보는 DTO에 담아서 저장. -> DTO정보를 List 한 칸에 저장. 
//				NoticeDTO dto = new NoticeDTO();
//				dto.setContent(rs.getString("content"));
//				dto.setDate(rs.getDate("date"));
//				dto.setFile(rs.getString("file"));
//				dto.setIp(rs.getString("ip"));
//				dto.setName(rs.getString("name"));
//				dto.setNum(rs.getInt("num"));
//				dto.setPass(rs.getString("pass"));
//				dto.setRe_lev(rs.getInt("re_lev"));
//				dto.setRe_ref(rs.getInt("re_ref"));
//				dto.setRe_seq(rs.getInt("re_seq"));
//				dto.setReadcount(rs.getInt("readcount"));
//				dto.setSubject(rs.getString("subject"));
//				
//				// List 한칸에 저장.
//				boardList.add(dto);
//			}
//			System.out.println("DAO: 글정보 저장 완료(List)");
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			CloseDB();
//		}
//		
//		return boardList;
//	}
//	
//	
//	// 글 조회수 증가 메서드
//	public void updateReadCount(int num) {
//		try {
//			con = getCon();
//			sql = "update itwill_board set readcount = readcount + 1 where num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			pstmt.executeUpdate();
//			
//			System.out.println("DAO: 조회수 1증가 완료!");
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			CloseDB();
//		}
//	}
//	
//	
//	
//	// 글 하나의 정보 가져오기
//	public NoticeDTO getBoard(int num) {
//		
//		NoticeDTO dto = null;
//		
//		try {
//			con = getCon();
//			sql = "select * from itwill_board where num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			rs = pstmt.executeQuery();
//			
//			
//			if(rs.next()) {
//				dto = new NoticeDTO();	// 객체는 미리만들지 말고 필요할때 생성하자!!
//				
//				dto.setContent(rs.getString("content"));
//				dto.setDate(rs.getDate("date"));
//				dto.setFile(rs.getString("file"));
//				dto.setIp(rs.getString("ip"));
//				dto.setName(rs.getString("name"));
//				dto.setNum(rs.getInt("num"));
//				dto.setPass(rs.getString("pass"));
//				dto.setRe_lev(rs.getInt("re_lev"));
//				dto.setRe_ref(rs.getInt("re_ref"));
//				dto.setRe_seq(rs.getInt("re_seq"));
//				dto.setReadcount(rs.getInt("readcount"));
//				dto.setSubject(rs.getString("subject"));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			CloseDB();
//		}
//
//		return dto;
//	}
//	
//	
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








