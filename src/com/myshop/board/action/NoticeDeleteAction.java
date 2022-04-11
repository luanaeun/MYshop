package com.myshop.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : BoardDeleteAction_execute()호출");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		int idx = Integer.parseInt(req.getParameter("num"));
		String pw = req.getParameter("pw");
		
		
		// 공지 업데이트 메서드 호출
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteNotice(idx, pw);
		
		
		// 페이지 이동
		res.setContentType("text/html; charset=UTF-8");
		if(result == 0) {
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		} 
		if(result == -1) {
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('해당 글 없음');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		} 
		
		PrintWriter out = res.getWriter();
		out.print("<script>");
		out.print("alert('글 삭제 완료!');");
		out.print("location.href='./Notice.bo'");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
