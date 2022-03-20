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
		System.out.println("여기까지는 들어와?");
		
		// 한글처리: 자바코드는 원래 reqest가 없는데 execute메서드가 실행될때 Ctl에서 가져오게 한거야.
		req.setCharacterEncoding("UTF-8");

		// 전달해준 파라미터 저장(액션태그X)
		UserDTO dto = new UserDTO();

		dto.setId(req.getParameter("id"));
		dto.setPw(req.getParameter("pw"));
		dto.setName(req.getParameter("name"));
		dto.setBirth(Integer.parseInt(req.getParameter("birth")));
		dto.setPhone(Integer.parseInt(req.getParameter("phone")));
		dto.setEmail(req.getParameter("email"));
		dto.setGender(req.getParameter("gender"));
		dto.setState("default");
		dto.setAgree(req.getParameter("agree"));

		
		// 날짜 정보 저장
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));

		
		System.out.println("저장한것: " + dto.toString());

		// DAO 객체 생성
		UserDAO dao = new UserDAO();
		
		
		// 회원가입 메서드 호출
		dao.userSignUp(dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./SignIn.us");
		forward.setRedirect(true); //이동방법 설정.주소가 login으로 바껴야하니까 true를 한다. 
		
		System.out.println("M : 페이지 정보를 컨트롤러 페이지로");
		
		return forward;

	}

}
