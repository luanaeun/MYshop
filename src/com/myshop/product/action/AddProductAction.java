package com.myshop.product.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;
import com.myshop.product.db.ProductDTO;
import com.oreilly.servlet.MultipartFilter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AddProductAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : AddProductAction_execute()호출");
		
		
		// 이미지가 들어갈 저장 경로를 설정한다.
		String realPath = req.getRealPath("/upload/productImg");
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(
				req, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
		);
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		//전달된 제품정보를 저장
		ProductDTO dto = new ProductDTO();
		HttpSession se = req.getSession();
		dto.setUseridx((int) se.getAttribute("user_idx"));
		
		dto.setName(multi.getParameter("name"));
		dto.setPrice(Integer.parseInt(multi.getParameter("price")));
		dto.setCategory(multi.getParameter("category"));
		dto.setStock(Integer.parseInt(multi.getParameter("stock")));
		dto.setColor(multi.getParameter("color"));
		dto.setContent(multi.getParameter("content"));
		dto.setDeliCharge(Integer.parseInt(multi.getParameter("deliveryCharge")));
		dto.setDeliDays(multi.getParameter("deliDays"));
		dto.setHowDeli(multi.getParameter("howDeli"));
		dto.setIp(req.getRemoteAddr());
		
		dto.setSumbnail(multi.getFilesystemName("addImg1"));

		
		ArrayList fileNames = new ArrayList();
		for(int i=2; i<=5; i++) {
			String temp_file = multi.getFilesystemName("addImg"+i);
			if(temp_file != null) {
				fileNames.add(temp_file);
			} else {
				fileNames.add(null);
			}
		}
		dto.setImages(fileNames);
		
		//System.out.println("저장할 것: " + dto.toString());


		// 제품 등록 메서드 호출
		ProductDAO dao = new ProductDAO();
		dao.addProduct(dto);

		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./TodayNewProduct.pd");
		forward.setRedirect(true);
		
		System.out.println("M: 글쓰기 완료후 페이지 정보를 Ctl로 리턴");
		
		return forward;
	}
	
	

}
