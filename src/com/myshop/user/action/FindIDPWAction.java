package com.myshop.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;

public class FindIDPWAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String what = req.getParameter("what");
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		UserDAO dao = new UserDAO();
		
		if(what.equals("id")){
		  int result = dao.findIDPW(name, email);
		}

		
		

		// 정보를 전달하기 위해 request영역 정보 저장
		

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./user/signIn.jsp");
		forward.setRedirect(false);

		return forward;
	}
  
}
