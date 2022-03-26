package com.myshop.user.action;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;



public class UserSignOutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("로그아웃 로직 들어옴");
		
		// 현재 로그인 정보가 담긴 세션 삭제
		HttpSession se = req.getSession();
		se.invalidate();

		
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.ma");
		forward.setRedirect(true);
		
		
		System.out.println("M : 로그아웃 완료~ 메인페이지로");
		
		return forward;

	}

}
