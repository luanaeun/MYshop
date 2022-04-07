package com.myshop.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.ReviewDAO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class ReviewGetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ReviewDAO dao = new ReviewDAO();
		
		// 자료실 개수
		int result = dao.getReviewCount();
		
		ArrayList reviewList = null;
		
		if(result != 0) {	// 글이 있을 때
			reviewList = dao.getReviewList();		// 글 목록 가져오기
		}
		System.out.println("M: " + reviewList);
		
		
		req.setAttribute("reviewList", reviewList);
		//req.setAttribute("referenceCnt", result);
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/review.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
