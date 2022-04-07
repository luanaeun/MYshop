package com.myshop.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.ReviewDAO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class RcommentGetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ReviewDAO dao = new ReviewDAO();
		ArrayList rCommentList = null;
		
		
		int pidx = Integer.parseInt(req.getParameter("num"));
		rCommentList = dao.getRcommentList(pidx);		// 글 목록 가져오기
		
		
		req.setAttribute("rCommentList", rCommentList);
		//req.setAttribute("referenceCnt", result);
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/review.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
