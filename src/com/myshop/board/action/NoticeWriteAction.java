package com.myshop.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class NoticeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : BoardWriteAction_execute()호출");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		//전달된 글정보를 저장
		NoticeDTO dto = new NoticeDTO();
		dto.setName(req.getParameter("name"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setPw(req.getParameter("pw"));
		
		
		// 사용자 ip주소 추가
		dto.setIp(req.getRemoteAddr());
		System.out.println("M : " + dto);
		
		// DAO객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 글쓰기 메서드 호출
		dao.writeNotice(dto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Notice.bo");
		forward.setRedirect(true);
		
		System.out.println("M: 글쓰기 완료후 페이지 정보를 Ctl로 리턴");
		
		return forward;
	}

}
