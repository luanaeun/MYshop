package com.myshop.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;
import com.myshop.product.db.ProductDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 이미지가 들어갈 저장 경로를 설정한다.
		String realPath = req.getRealPath("/productImgs");
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(
				req, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
		);
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		//전달된 제품정보를 저장
		ProductDTO dto = new ProductDTO();
		HttpSession se = req.getSession();
		dto.setUserid((String)se.getAttribute("user_id"));
		
		dto.setName(multi.getParameter("name"));
		dto.setPrice(Integer.parseInt(multi.getParameter("price")));
		dto.setCategory(multi.getParameter("category"));
		dto.setStock(Integer.parseInt(multi.getParameter("stock")));
		dto.setContent(multi.getParameter("content"));
		dto.setDeliCharge(Integer.parseInt(multi.getParameter("deliveryCharge")));
		dto.setDeliDays(multi.getParameter("deliDays"));
		dto.setHowDeli(multi.getParameter("howDeli"));
		dto.setIp(req.getRemoteAddr());
		
		dto.setSumbnail(multi.getParameter("img01"));

		
		ArrayList fileNames = new ArrayList<>();
		fileNames.add(multi.getParameter("img02"));
		fileNames.add(multi.getParameter("img03"));
		fileNames.add(multi.getParameter("img04"));
		fileNames.add(multi.getParameter("img05"));
		dto.setImages(fileNames);
		
		System.out.println("저장할 것: " + dto.toString());


		// 제품 수정 메서드 호출
		ProductDAO dao = new ProductDAO();
		dao.updateProduct(dto);

		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./TodayNewProduct.pd");
		forward.setRedirect(true);
		
		System.out.println("M: 글쓰기 완료후 페이지 정보를 Ctl로 리턴");
		
		return forward;
	}
	
}
