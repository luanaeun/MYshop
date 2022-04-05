package com.myshop.product.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;
import com.myshop.user.db.UserDAO;

public class DeleteProductAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("제품삭제 로직 들어옴");
		

		// 현재 로그인한 아이디 가져오기.
		HttpSession se = req.getSession();
		String userid = (String) se.getAttribute("user_id");
		int prodid = Integer.parseInt(req.getParameter("num"));

		
		// DAO 객체 생성
		ProductDAO dao = new ProductDAO();
		int result = dao.deleteProduct(userid, prodid);
		System.out.println("상품 삭제 함수 결과: " + result);
		
		PrintWriter out = res.getWriter();
		if(result == 1) {	
			out.print(result);
		} else {
			out.print(result);
		}

		System.out.println("M : 페이지 정보를 컨트롤러 페이지로");
		
		return null;
	}
	

}
