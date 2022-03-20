package com.myshop.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.ActionMapUIResource;

import com.myshop.board.db.BoardDAO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M: MYshop - NoticeListAction()호출");
		
		//BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 게시판 총 글 개수 가져오기
		int result = dao.getNoticeCount();
//		
//		//페이징 처리(한페이지에 출력할 글의 개수 - 10개로 제한)
//		int pageSize = 3;
//		
//		// 페이지 정보가 몇 페이지인지 체크: 지금 몇 패이지에 있는지 조회. 마지막은 개수가 다르니까
//		String pageNum = req.getParameter("pageNum");
//		if(pageNum == null) {	// 페이지 정보가 없으면 무조건 1페이지로 가겠다는 의미.
//			pageNum = "1";
//		}
//		
//		// 시작행 번호 계산하기	1 11 21 31 ....
//		int currentPage = Integer.parseInt(pageNum);
//		int startRow = (currentPage -1) * pageSize + 1;
//		
//		
//		// 끝행 번호 계산하기	10 20 30 ...
//		int endRow = currentPage * pageSize;
		
		// if문 안에서 생성하면 지역변수가 되니까 여기 밖에서 미리 선언. 
		ArrayList noticeList = null;
		
		if(result != 0) {	// 글이 있을 때
			// 글 목록 가져오기
			// boardList = dao.getBoardList();
			// boardList = dao.getNoticeList(startRow, pageSize);
			noticeList = dao.getNoticeList();
		
		}
		System.out.println("M: " + noticeList);
		
		
		//페이징 처리(하단)=====================
		// 전체 페이지 블럭 계산(밑에 숫자 몇개까지 나오게 할건지)
//		int pageCount = result/pageSize + (result%pageSize==0? 0:1);
//		
//		// 한번에 보여줄 페이지 블럭의 개수
//		int pageBlock = 2;
//		
//		// 시작페이지 번호(블럭): 만약 페이지가 1~10사이면 페이지 블럭=?1. 
//		// 페이지 블럭이 옆으로 안넘어간다는 말. => (15-1)/
//		int startBlock = ((currentPage-1)/pageBlock)*pageBlock+1;
//		
//		// 끝 페이지 번호(블럭)
//		int endBlock = startBlock + pageBlock-1;
//		if(endBlock > pageCount) {
//			endBlock = pageCount;
//		}
		
		// 영역객체에 글 목록 저장
		req.setAttribute("noticeList", noticeList);
		req.setAttribute("noticeCnt", result);
		
		// 페이징 처리 정보도 request영역에 저장
//		req.setAttribute("pageNum", pageNum);
//		req.setAttribute("pageCount", pageCount);
//		req.setAttribute("pageBlock", pageBlock);
//		req.setAttribute("startPage", startBlock);
//		req.setAttribute("endPage", endBlock);
		
	
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/notice.jsp");
		forward.setRedirect(false);
		return forward;
	
	}
	
}
