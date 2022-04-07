package com.myshop.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.board.db.ReferenceDTO;
import com.myshop.board.db.ReviewDAO;
import com.myshop.board.db.ReviewDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : ReviewceWriteAction_execute()호출");
		
		
		String realPath = req.getRealPath("/upload/review");
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(
				req, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
		);
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		//전달된 글정보를 저장
		ReviewDTO dto = new ReviewDTO();
		dto.setAuthor(multi.getParameter("author"));
		dto.setTitle(multi.getParameter("title"));
		dto.setContent(multi.getParameter("content"));
		dto.setScore(Float.parseFloat(multi.getParameter("score")));
		dto.setImg(multi.getFilesystemName("addImg"));
		dto.setIp(req.getRemoteAddr());
		
		
		// DAO객체 생성
		ReviewDAO dao = new ReviewDAO();
		dao.writeReview(dto);
		
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./Review.bo");
		forward.setRedirect(true);
		
		System.out.println("M: 글쓰기 완료후 페이지 정보를 Ctl로 리턴");
		
		return forward;
	}
	
}
