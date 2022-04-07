package com.myshop.product.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;

public class GetCateProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M: MYshop - GetCateProductAction()호출");
		
		ArrayList productList = null;
		//SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		ProductDAO dao = new ProductDAO();
		
		String cate = req.getParameter("cate");
		
		// 페이징 처리를 위한 준비.
		int pageSize = 10;	//페이징 처리(한페이지에 출력할 글의 개수 )
				
		// 페이지 정보가 몇 페이지인지 체크: 지금 몇 패이지에 있는지 조회. 마지막은 개수가 다르니까
		String pageNum = req.getParameter("pageNum");
		if(pageNum == null) {	// 페이지 정보가 없으면 무조건 1페이지로 가겠다는 의미.
			pageNum = "1";
		}
		// 시작행 번호 계산하기	1 11 21 31 ....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage -1) * pageSize + 1;	
		// 끝행 번호 계산하기	10 20 30 ...
		int endRow = currentPage * pageSize;
		
	
		// 제품 총 개수 알아오고 리스트 가져오기
		int productCount = dao.getCateProductCount(cate);
		if(productCount != 0) {	// 글이 있을 때
			productList = dao.getCateProductList(startRow, pageSize, cate);
		}
		System.out.println("M: 제품 조회 완료" + productList);
		
		
		int pageCount = productCount/pageSize + (productCount%pageSize==0? 0:1);
		// 한번에 보여줄 페이지 블럭의 개수
		int pageBlock = 2;
		// 시작페이지 번호(블럭): 만약 페이지가 1~10사이면 페이지 블럭=?1. 
		// 페이지 블럭이 옆으로 안넘어간다는 말. => (15-1)/
		int startBlock = ((currentPage-1)/pageBlock)*pageBlock+1;
		// 끝 페이지 번호(블럭)
		int endBlock = startBlock + pageBlock-1;
		if(endBlock > pageCount) {
			endBlock = pageCount;
		}
		
		
		// 영역객체에 글 목록 저장
		req.setAttribute("productList", productList);
		req.setAttribute("productCount", productCount);
				
		// 페이징 처리 정보도 request영역에 저장
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageBlock", pageBlock);
		req.setAttribute("startPage", startBlock);
		req.setAttribute("endPage", endBlock);
		

			
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./product/productList.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
