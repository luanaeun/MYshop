package com.myshop.product.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;

public class GetTodayProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M: MYshop - GetTodayProductAction()호출");
		ArrayList productList = null;
		
		//BoardDAO 객체 생성
		ProductDAO dao = new ProductDAO();
		productList = dao.getProductList();
		System.out.println("M: 제품 조회 완료" + productList);
		
		
		// 영역객체에 글 목록 저장
		req.setAttribute("prodList", productList);
		// req.setAttribute("noticeCnt", result);
		

			
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./product/productList.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
