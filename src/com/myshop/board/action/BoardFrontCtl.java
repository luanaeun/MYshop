package com.myshop.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.action.BoardWriteAction;
import com.myshop.common.*;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class BoardFrontCtl extends HttpServlet{
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardFrontCtl - doProcess()호출");
		
		// 1. 가상 주소 계산 => .us로 끝나는 메핑된 주소를 가져온다. 
		String reqURI = req.getRequestURI();
		String ctxPath = req.getContextPath();
		String command = reqURI.substring(ctxPath.length());
		System.out.println("command: " + command);
		
		System.out.println("C : 1.가상주소 계산 완료");
		
		
		// 2. 가상주소 매핑
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Notice.bo")) {			
			forward = new ActionForward();
			forward.setPath("./board/notice.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/BoardWrite.bo")) {	
			forward = new ActionForward();
			forward.setPath("./board/noticeWrite.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/BoardWriteAction.bo")) {
			action = new BoardWriteAction();
			
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
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
