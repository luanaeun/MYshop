package com.myshop.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.board.db.ReferenceDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReferenceWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : ReferenceWriteAction_execute()호출");
		
		
		String realPath = req.getRealPath("/upload/reference");
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(
				req, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
		);
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		//전달된 글정보를 저장
		ReferenceDTO dto = new ReferenceDTO();
		dto.setAuthor(multi.getParameter("author"));
		dto.setTitle(multi.getParameter("title"));
		dto.setFile(multi.getFilesystemName("file"));
		
		
		// DAO객체 생성
		BoardDAO dao = new BoardDAO();
		dao.writeReference(dto);
		
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./ReferenceRoom.bo");
		forward.setRedirect(true);
		
		System.out.println("M: 글쓰기 완료후 페이지 정보를 Ctl로 리턴");
		
		return forward;
	}
	
}
