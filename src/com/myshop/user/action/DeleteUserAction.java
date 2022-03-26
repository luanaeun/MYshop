package com.myshop.user.action;


import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;



public class DeleteUserAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("유저 삭제 로직 들어옴");
		
		
		Timestamp dropDay = new Timestamp(System.currentTimeMillis());	// 탈퇴 날짜
		
		// 현재 로그인한 아이디 가져오기.
		HttpSession se = req.getSession();
		String id = (String) se.getAttribute("user_id");
		
		// 입력한 비밀번호 가져오기.
		String pw = req.getParameter("pw");
		

		// DAO 객체 생성
		UserDAO dao = new UserDAO();
		int result = dao.userDelete(dropDay, id, pw);
		System.out.println("회원탈퇴 함수 결과: " + result);
		
		ActionForward forward = new ActionForward();
		
		res.setContentType("text/html; charset=UTF-8");
		if(result == 1) {
			se.invalidate();
			forward.setPath("./Main.ma");
		} else if(result == 0) {
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		} 
		
		
		forward.setRedirect(true);
		
		
		System.out.println("M : 페이지 정보를 컨트롤러 페이지로");
		
		return forward;

	}

}
