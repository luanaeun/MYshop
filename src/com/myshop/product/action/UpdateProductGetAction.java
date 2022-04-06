package com.myshop.product.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;
import com.myshop.product.db.ProductDTO;

public class UpdateProductGetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M: MYshop - UpdateProductGetAction()호출");
		
		
		//BoardDAO 객체 생성
		ProductDAO dao = new ProductDAO();
		HashMap cateList = dao.bringCategory();
		System.out.println("M: 카테고리 조회 완료" + cateList);
		
		
		// 영역객체에  카테고리 저장
		req.setAttribute("cateList", cateList);
		
		
		
		// 제품 세부 정보 가져오기
		int num = Integer.parseInt(req.getParameter("num"));
		System.out.println("넘은 가져와? 세부정보 가져왔어?" + num);
		ProductDTO dto = dao.getDetailProduct(num);
		System.out.println("세부정보 가져왔어?" + dto);
		req.setAttribute("dto", dto);
		
			
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./product/updateProduct.jsp");
		forward.setRedirect(false);
		return forward;
	}
	

}
