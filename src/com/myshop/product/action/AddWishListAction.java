package com.myshop.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDTO;

public class AddWishListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : AddWishListAction_execute()호출");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");

		// 전달된 제품정보를 저장
//		ProductDTO dto = new ProductDTO();
//		HttpSession se = req.getSession();
//		dto.setUserid((String) se.getAttribute("user_id"));
		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./TodayNewProduct.pd");
		forward.setRedirect(true);

		System.out.println("M: 글쓰기 완료후 페이지 정보를 Ctl로 리턴");

		return forward;
	}
	

}
