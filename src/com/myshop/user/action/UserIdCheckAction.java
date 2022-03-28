package com.myshop.user.action;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;



public class UserIdCheckAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("아이디 중복 체크 로직 들어옴><!!!");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");

		// 전달해준 파라미터 저장(액션태그X)
		String tempID = req.getParameter("id");

		// DAO 객체 생성
		UserDAO dao = new UserDAO();
		int result = dao.userIdCheck(tempID);
		System.out.println("아이디 중복 체크 결과: " + result);
		
		ActionForward forward = new ActionForward();
		
		if(result == 1) {
			req.setAttribute("idCheckResult", "ok");	// 세션말고, 파라미터로 넘기고싶은데....
			
			forward.setPath("/SignUp.us?check=ok");	
		} else {
			req.setAttribute("idCheckResult", "no");
			forward.setPath("/SignUp.us");
		} 
		forward.setRedirect(false);
				
		System.out.println("M : 아이디 중복 확인했으니까 다시 가입 페이지로!");
		
		return forward;

	}

}
