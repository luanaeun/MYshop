package com.myshop.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;
import com.myshop.product.db.ProductDTO;

public class GetProductDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 전달한 제품 번호와 페이지 번호를 저장
		int num = Integer.parseInt(req.getParameter("num")); // 글번호
		// String pageNum = req.getParameter("pageNum"); // 현재 페이지 정보
		System.out.println("GetProductDetailAction.java에 들어옴: " + num);

		ProductDAO dao = new ProductDAO();

		// 제품 조회수 증가 => 메서드로 동작(num을 가져가서 해당 글에대한 조회수 증가)
		dao.updateProductView(num);

		// 특정 글의 내용을 확인
		ProductDTO dto = dao.getDetailProduct(num);
		System.out.println("상품 세부 정보 가져왔어?" + dto);

		// 정보를 전달하기 위해 request영역 정보 저장
		req.setAttribute("dto", dto);
		// req.setAttribute("pageNum", pageNum);
		// req.setAttribute("dto", dao.getBoard(num));

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./product/prodDetail.jsp");
		forward.setRedirect(false);

		return forward;

	}
	
	

}
