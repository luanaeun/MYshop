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

public class UserpwCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		System.out.println("비밀번호 확인 로직 들어옴");
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		// 현재 로그인 정보의 id가져오기
		HttpSession se = req.getSession();		
		String id = (String) se.getAttribute("user_id");
		
		// 어떤 페이지로 이동해야할지 정보
		String whatPage = (String) req.getParameter("what");
		System.out.println("이동할 페이지 정보: " + whatPage);

		// 전달한 파라미터 저장
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setPw(req.getParameter("pw"));

		UserDAO dao = new UserDAO();
		int result = dao.userPwCheck(dto);
		System.out.println("비번 확인 결과: " + result);
		
		ActionForward forward = new ActionForward();
		res.setContentType("text/html; charset=UTF-8");
		if(result == 1) {
			if(whatPage.equals("info-update")) {
				//forward.setPath("./UpdateUserInfo.us");		// 유저 정보 변경 페이지로 이동
				PrintWriter out = res.getWriter();
				out.print("<script>");
				out.print("location.href='./UpdateUserInfo.us';");
				out.print("</script>");
				out.close();
			} else if (whatPage.equals("pw-update")) {
				// forward.setPath("./Main.ma");				// 비밀번호 변경 페이지로 이동
				PrintWriter out = res.getWriter();
				out.print("<script>");
				out.print("location.href='./Main.ma';");
				out.print("</script>");
				out.close();
			} else if(whatPage.equals("delete")) {
				// forward.setPath("./DeleteUser.us");				// 회원 탈퇴 페이지로 이동
				PrintWriter out = res.getWriter();
				out.print("<script>");
				out.print("location.href='./DeleteUser.us';");
				out.print("</script>");
				out.close();
			}
		} else if (result == 0) {
			System.out.println("비번 결과 0 안으로 들어와?");
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 틀립니다.');");
			out.print("location.href='./MyPage.us';");
			//out.print("showModal('pw-false');");
			out.print("</script>");
			out.close();
			return null;
		} else {
			PrintWriter out = res.getWriter();
			out.print("<script>");
			out.print("alert('아이디와 비밀번호 정보가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		// forward.setRedirect(true);
		
		// return forward;
		return null;
	}
	
}
