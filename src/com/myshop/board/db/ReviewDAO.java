package com.myshop.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

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
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// 리뷰 개수 가져오기
	public int getReviewCount() {
		int result = 0;
		try {
			con = getCon();
			sql = "select count(*) from myshop_review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1); //만약 컬럼명으로 스고싶으면 "count(*)"이렇게 쓰면된다. 
			}
			//System.out.println("DAO : 공지 총 개수 -> " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}		
		return result;
	}
	
	
	
	// 리뷰 리스트 불러오기
	public ArrayList getReviewList() {
		ArrayList reviewList = new ArrayList();
		try {
			con = getCon();

			sql = "select * from myshop_review order by rev_rgdate asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setVidx(rs.getInt("rev_idx"));
				dto.setPidx(rs.getInt("rev_pidx"));
				dto.setAuthor(rs.getString("rev_author"));
				dto.setTitle(rs.getString("rev_title"));
				dto.setContent(rs.getString("rev_content"));
				dto.setScore(rs.getFloat("rev_score"));
				dto.setViewcount(rs.getInt("rev_viewcount"));
				dto.setRgdate(rs.getTimestamp("rev_rgdate"));
				// List 한칸에 저장.
				reviewList.add(dto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return reviewList;
	}
	
	
	
	// 리뷰 작성 메서드
	public void writeReview(ReviewDTO dto) {
		int idx = 0;
		try {
			con = getCon();
			sql = "select max(rev_idx) from myshop_review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				idx = rs.getInt(1) + 1;
			}

			sql = "insert into myshop_review values(?,?,?,?,?,?,0,now(),?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, idx);
			pstmt.setInt(2, 0); 	// 우선 0으로 하고 나중에 대체
			pstmt.setString(3, dto.getAuthor());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			pstmt.setFloat(6, dto.getScore());
			pstmt.setString(7, dto.getIp());
			pstmt.setString(8, dto.getImg());

			pstmt.executeUpdate();

			System.out.println("DAO : 리뷰 업로드 완료! ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}	
	}
	
	
	
	// 리뷰 조회수
	public void updateViewCount(int num) {
		System.out.println("조회수 증가 메서드 호출");
		try {
			con = getCon();
			sql = "update myshop_review set rev_viewcount = rev_viewcount + 1 where rev_idx=?";
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
	
	
	// 리뷰 상세 정보 가져오기
	public ReviewDTO getReviewDetail(int idx) {
		ReviewDTO dto = null;
		
		try {
			con = getCon();
			sql = "select * from myshop_review where rev_idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ReviewDTO();	
				dto.setVidx(rs.getInt("rev_idx"));
				dto.setPidx(rs.getInt("rev_pidx"));
				dto.setAuthor(rs.getString("rev_author"));
				dto.setTitle(rs.getString("rev_title"));
				dto.setContent(rs.getString("rev_content"));
				dto.setScore(rs.getFloat("rev_score"));
				dto.setViewcount(rs.getInt("rev_viewcount"));
				dto.setRgdate(rs.getTimestamp("rev_rgdate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return dto;
	}
	
	
	
	// 댓글 작성 메서드
	public int writeComment(int pidx, String comment, String userid) {
		int result = 0;
		int idx = 0;
		try {
			con = getCon();
			sql = "select max(cm_idx) from review_comment";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				idx = rs.getInt(1) + 1;
			}

			sql = "insert into review_comment values(?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, idx);
			pstmt.setInt(2, pidx); 
			pstmt.setString(3, comment);
			pstmt.setString(4, userid);

			pstmt.executeUpdate();
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}	
		return result;
	}
	
	
	
	// 댓글 리스트 가져오기 메서드
	public ArrayList getRcommentList(int pidx) {
		ArrayList rCommentList = new ArrayList();
		try {
			con = getCon();

			sql = "select * from review_comment where cm_pidx=? order by cm_rgdate asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pidx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setIdx(rs.getInt("cm_idx"));
				dto.setPidx(rs.getInt("cm_pidx"));
				dto.setComment(rs.getString("cm_comment"));
				dto.setUserid(rs.getString("cm_userid"));
				dto.setRgdate(rs.getTimestamp("cm_rgdate"));
				// List 한칸에 저장.
				rCommentList.add(dto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return rCommentList;
	}
	
	

}	// 전체 함수
