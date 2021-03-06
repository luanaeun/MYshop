package com.myshop.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class NoticeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : BoardWriteAction_execute()호출");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		int idx = Integer.parseInt(req.getParameter("num"));
		
		//전달된 글정보를 저장
		NoticeDTO dto = new NoticeDTO();
		dto.setNum(idx);
		dto.setName(req.getParameter("name"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setPw(req.getParameter("pw"));
		dto.setIp(req.getRemoteAddr());
		System.out.println("M : " + dto);
		
		
		// 공지 업데이트 메서드 호출
		BoardDAO dao = new BoardDAO();
		int result = dao.updateNotice(dto);
		
		
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
		out.print("alert('글 수정 완료!');");
		out.print("location.href='./NoticeDetailAction.bo?num="+idx+"';");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
