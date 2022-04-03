package com.myshop.user.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.common.*;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class UserFrontCtl extends HttpServlet{
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UserFrontCtl - doProcess()호출");
		
		// 1. 가상 주소 계산 => .us로 끝나는 메핑된 주소를 가져온다. 
		String reqURI = req.getRequestURI();
		String ctxPath = req.getContextPath();
		String command = reqURI.substring(ctxPath.length());
		System.out.println("command: " + command);
		
		System.out.println("C : 1.가상주소 계산 완료");
		
		
		// 2. 가상주소 매핑
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MngPage.us")) {			// 관리자 페이지
			forward = new ActionForward();
			forward.setPath("./admin/managePage.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/UserpwCheck.us")) {		// 비밀번호 체크 페이지
			action = new UserpwCheckAction();
			try{
				forward = action.execute(req, resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/SignIn.us")) {		// 로그인 페이지
			forward = new ActionForward();
			forward.setPath("./user/signIn.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/SignInAction.us")) {	// 로그인 DB동작 
			action = new UserSignInAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/SignOut.us")) {	// 로그아웃 동작
			action = new UserSignOutAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/SignUp.us")) {	// 회원가입
			forward = new ActionForward();
			forward.setPath("./user/signUp.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/SignUpIdCheck.us")) {	//아이디 중복 확인
			action = new UserIdCheckAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/SignUpAction.us")) {	// 회원가입DB동작 
			action = new UserSignUpAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/MyPage.us")) {	// 마이페이지
			action = new GetUserInfoAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/UpdateUserInfo.us")) { // 유저 정보 변경 페이지
			action = new UpdateGetUserAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
			forward = new ActionForward();
			forward.setPath("./user/updateUserInfo.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/UpdateUserInfoAction.us")) {	// 유저 정보 변경 액션
			action = new UpdateUserInfoAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/DeleteUser.us")) {		// 회원 탈퇴 동작
			action = new DeleteUserAction();	
			try {
				forward = action.execute(req, resp);	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		// 3. 페이지 이동
		if(forward != null) {	
			if(forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
				System.out.println("C: sendRedirect방식 - " + forward.getPath() + "으로 이동");
			} else {
				RequestDispatcher dis
					= req.getRequestDispatcher(forward.getPath());
				dis.forward(req, resp);
			}
			
		}
		
		
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UserFront 컨트롤러 들어옴");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	

}
