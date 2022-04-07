package com.myshop.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.iterators.ArrayListIterator;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.CommentDTO;
import com.myshop.board.db.ReviewDAO;
import com.myshop.board.db.ReviewDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("ReviewDetailAction들어옴");
		
		ReviewDAO dao = new ReviewDAO();
		ArrayList rCommentList = null;
		int idx = Integer.parseInt(req.getParameter("num"));
		
		dao.updateViewCount(idx);
		ReviewDTO dto = dao.getReviewDetail(idx);		// 글 목록 가져오기
		req.setAttribute("dto", dto);
		
		rCommentList = dao.getRcommentList(idx);
		req.setAttribute("rCommentList", rCommentList);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/reviewDetail.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
