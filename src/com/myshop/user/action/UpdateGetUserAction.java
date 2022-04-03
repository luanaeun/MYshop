package com.myshop.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class UpdateGetUserAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 현재 로그인한 세션 아이디 가져오기
		HttpSession se = req.getSession();
		String current_user = (String) se.getAttribute("user_id");
		

		// UserDAO객체 생성및 함수 호출
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.getUserInfo(current_user);
			
		// 정보를 전달하기 위해 request영역 정보 저장
		req.setAttribute("dto", dto);
		int idx = dto.getIdx();
		
		
		
		//페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./user/updateUserInfo.jsp?idx="+idx);
		forward.setRedirect(false);
		

		
		return forward;
	}

	
}
