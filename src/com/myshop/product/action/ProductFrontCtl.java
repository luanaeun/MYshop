package com.myshop.product.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.common.*;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.action.UpdateUserInfoAction;

public class ProductFrontCtl extends HttpServlet{
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ProductFrontCtl - doProcess()호출");
		
		// 1. 가상 주소 계산 => .us로 끝나는 메핑된 주소를 가져온다. 
		String reqURI = req.getRequestURI();
		String ctxPath = req.getContextPath();
		String command = reqURI.substring(ctxPath.length());
		System.out.println("command: " + command);
		
		System.out.println("C : 1.가상주소 계산 완료" + command);
		
		
		// 2. 가상주소 매핑
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/TodayNewProduct.pd")) {			
			action = new GetTodayProductAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ProductList.pd")) {	// 카테고리에 따른 상품 리스트
			action = new GetCateProductAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if (command.equals("/ProductDetail.pd")){	// 제품 세부정보
			action = new GetProductDetailAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		  else if (command.equals("/AddProduct.pd")) {		// 상품 등록 페이지
			action = new BringCateAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/AddProductAction.pd")) {	// 상품 등록 실제 페이지
			action = new AddProductAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UpdateProduct.pd")) {		// 상품 수정 페이지로 이동
			action = new UpdateProductGetAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/UpdateProductAction.pd")) {	// 상품 수정 액션
			action = new UpdateUserInfoAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/DeleteroductAction.pd")) {	// 상품 삭제
			action = new DeleteProductAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		  else if(command.equals("/AddWishAction.pd")) {	// 위시리스트 추가
			action = new AddWishListAction();
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		  }
		
		
		// 3. 페이지 이동
		if(forward != null) {	
			if(forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
				System.out.println("C: sendRedirect방식 - " + forward.getPath() + "으로 이동");
			} else {
				RequestDispatcher dis
					= req.getRequestDispatcher(forward.getPath());
				dis.forward(req, resp);
			}
			
		}
		
		
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UserFront 컨트롤러 들어옴");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	

}
