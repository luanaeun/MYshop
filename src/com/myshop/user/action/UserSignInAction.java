package com.myshop.user.action;


import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;



public class UserSignInAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("로그인 로직 들어옴");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");

		// 전달해준 파라미터 저장(액션태그X)
		UserDTO dto = new UserDTO();

		dto.setId(req.getParameter("id"));
		dto.setPw(req.getParameter("pw"));
		dto.setLastLogin(new Timestamp(System.currentTimeMillis()));	// 마지막 로그인 날짜
		
		String rememberID = "";
		try {
			rememberID = req.getParameterValues("rememberID")[0];
		} catch(Exception e) {
		
		}

		System.out.println("저장한것: " + dto.toString());
		System.out.println("아이디 저장 체크 여부: " + rememberID);

		// DAO 객체 생성
		UserDAO dao = new UserDAO();
		int result = dao.userSignIn(dto);
		System.out.println("로그인 함수 결과: " + result);
		
		ActionForward forward = new ActionForward();
		if(result == 1) {
			HttpSession se = req.getSession();		// 세션에 아이디 저장.
			se.setAttribute("user_id", dto.getId());
			
			if(rememberID.length() > 0) {	//...? 이거 왜 안돼...???
				System.out.println("쿠키값 설정하러~: " + rememberID);
				Cookie cookie = new Cookie("rememberID", req.getParameter("id"));
				res.addCookie(cookie); 		// 응답페더에 쿠키 추가
				System.out.println("쿠키 저장 완료! => " + cookie.getValue());
			} else {
				Cookie[] cookies = req.getCookies();
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("rememberID")) {
						cookies[i].setMaxAge(0);
						res.addCookie(cookies[0]);
					}
				}	
			}
			forward.setPath("./Main.ma");
			
		} else if(result == 0) {
			forward.setPath("./SignIn.us");
		} else {
			forward.setPath("./SignIn.us");
		}
		forward.setRedirect(true);
		
		
		System.out.println("M : 페이지 정보를 컨트롤러 페이지로");
		
		return forward;

	}

}
