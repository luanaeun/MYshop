package com.myshop.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class ReferenceGetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		BoardDAO dao = new BoardDAO();
		
		// 자료실 개수
		int result = dao.getReferenceCount();
		
		ArrayList referenceList = null;
		
		if(result != 0) {	// 글이 있을 때
			referenceList = dao.getReferenceList();		// 글 목록 가져오기
		}
		System.out.println("M: " + referenceList);
		
		
		req.setAttribute("referenceList", referenceList);
		req.setAttribute("referenceCnt", result);
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/referenceRoom.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
