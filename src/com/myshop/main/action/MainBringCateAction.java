package com.myshop.main.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;

public class MainBringCateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		System.out.println("M: MYshop - MainBringCateAction()호출");
		
		
		//BoardDAO 객체 생성
		ProductDAO dao = new ProductDAO();
		HashMap cateList = dao.bringCategory();
		System.out.println("M: 카테고리 조회 완료" + cateList);
		
		
		// 영역객체에 글 목록 저장
		req.setAttribute("cateList", cateList);

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./main/main.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
