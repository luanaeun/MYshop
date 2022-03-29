package com.myshop.manage.action;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.manage.db.CategoryDAO;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;

public class AddDetailCateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
System.out.println("로그인 로직 들어옴");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");

		// 전달해준 파라미터 저장(액션태그X)
		String topName = req.getParameter("topCategory");
		String cateName = req.getParameter("cateName");
		System.out.println("셀렉트 한거 나와..?" +topName);
		

		// DAO 객체 생성
		CategoryDAO dao = new CategoryDAO();
		int result = dao.addDetailCate(topName, cateName);
		System.out.println("카테고리 추가 함수 결과: " + result);
		
		ActionForward forward = new ActionForward();
		if(result == 1) {
			forward.setPath("./MngPage.am");	
		} else {
			forward.setPath("./MngPage.ma");	
		}
		forward.setRedirect(true);
		
		
		System.out.println("M : 페이지 정보를 컨트롤러 페이지로");
		
		return forward;
	}

	
}
