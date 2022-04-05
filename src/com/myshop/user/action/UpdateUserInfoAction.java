package com.myshop.user.action;


import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;



public class UpdateUserInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("유저 정보 업데이트 액션!");
		
		// 한글처리: 자바코드는 원래 reqest가 없는데 execute메서드가 실행될때 Ctl에서 가져오게 한거야.
		req.setCharacterEncoding("UTF-8");

		// 전달해준 파라미터 저장(액션태그X)
		UserDTO dto = new UserDTO();
		System.out.println("id");
		
		HttpSession se = req.getSession();
		String original_id = (String)se.getAttribute("user_id");
		
	
		dto.setId(req.getParameter("id"));
		dto.setName(req.getParameter("name"));
		dto.setBirth(req.getParameter("birth"));
		dto.setPhone(req.getParameter("phone"));
		dto.setEmail(req.getParameter("email"));
		dto.setGender(req.getParameter("gender"));
		System.out.println("post");
		dto.setPost(Integer.parseInt(req.getParameter("post")));
		dto.setRoadAddr(req.getParameter("roadAddr"));
		dto.setDetailAddr(req.getParameter("detailAddr"));
		
		System.out.println("emailagree: " + req.getParameter("emailAgree"));
		dto.setEmailAgree(req.getParameter("emailAgree") == null ? 0 : 1);


		System.out.println("저장한것: " + dto.toString());
		
		

		// DAO 객체 생성. 실제 메서드 호출!!
		UserDAO dao = new UserDAO();
		int result = dao.updateUserInfo(dto, original_id);
		
		ActionForward forward = new ActionForward();
		if(result == 1) {
			se.setAttribute("user_id", req.getParameter("id"));
			
			// 쿠키값 가져오기
			String rememberID = "";
			Cookie[] cookies = req.getCookies();
			if(cookies !=  null) {
				for(int i=0; i<cookies.length; i++) {	// 쿠키값 해제 동작
					if(cookies[i].getName().equals("rememberID")) {
						Cookie cookie = new Cookie("rememberID", req.getParameter("id"));
						res.addCookie(cookie);
					}
				}
			}
			System.out.println("쿠키: " + rememberID);
			
			forward.setPath("./MyPage.us");
			forward.setRedirect(true); //이동방법 설정.주소가 login으로 바껴야하니까 true를 한다. 
		} else {
			forward.setPath("./UpdateUserInfo.us");		// 정보변경이 잘 되지 않았을 때.
			forward.setRedirect(true);
		}
		
		
		
		System.out.println("M : 페이지 정보를 컨트롤러 페이지로");
		
		return forward;

	}

}
