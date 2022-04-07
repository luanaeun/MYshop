package com.myshop.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.board.db.ReviewDAO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class RcommentWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : ReviewceWriteAction_execute()호출");

		// 한글처리
		req.setCharacterEncoding("UTF-8");
		HttpSession se = req.getSession();
		
		// 받은 정보
		int pidx = Integer.parseInt(req.getParameter("pnum"));
		String comment = req.getParameter("rComment");
		String userid = (String)se.getAttribute("user_id");
		
		
		// DAO객체 생성
		ReviewDAO dao = new ReviewDAO();
		int result = dao.writeComment(pidx, comment, userid);
		
		
//		PrintWriter out = res.getWriter();
//		out.print(result);
		ActionForward forward = new ActionForward();
		forward.setPath("./board/reviewDetail.jsp");
		forward.setRedirect(false);
		return forward;
		
		
		
	}
}
