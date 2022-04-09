package com.myshop.user.action;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;



public class UserSignUpAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");

		// 전달해준 파라미터 저장 
		UserDTO dto = new UserDTO();
		
		dto.setId(req.getParameter("id"));
		dto.setPw(req.getParameter("pw"));
		dto.setName(req.getParameter("name"));
		dto.setBirth(req.getParameter("birth"));
		dto.setPhone(req.getParameter("phone"));
		dto.setEmail(req.getParameter("email"));
		dto.setGender(req.getParameter("gender"));
		System.out.println("post");
		dto.setPost(Integer.parseInt(req.getParameter("post")));
		dto.setRoadAddr(req.getParameter("roadAddr"));
		dto.setDetailAddr(req.getParameter("detailAddr"));
		
		dto.setStatus(1);
		System.out.println("infoagree");
		dto.setInfoAgree(req.getParameterValues("infoAgree")[0] == null ? 0 : 1);
		System.out.println("emailagree");
		dto.setEmailAgree(req.getParameter("emailAgree") == null ? 0 : 1);
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		dto.setUserimg("defaultUserImg.png");

		
		// 관리자인지 확인
		//if(req.getParameter("id").toLowerCase().contains("admin") == true) {
		if(req.getParameter("id").equals("admin")) {
			dto.setIsAdmin(1);
		} else {
			dto.setIsAdmin(0);
		}
		
		
		// 회원가입 메서드 호출
		UserDAO dao = new UserDAO();
		int result = dao.userSignUp(dto);
		
		ActionForward forward = new ActionForward();
		if(result == 1) {
			forward.setPath("./SignIn.us");
			forward.setRedirect(true); 
		} else {
			forward.setPath("./SignUp.us");
			forward.setRedirect(true);
		}
		
		System.out.println("M : 페이지 정보를 컨트롤러 페이지로");
		
		return forward;

	}

}
