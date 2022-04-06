package com.myshop.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.board.action.NoticeListAction;

import com.myshop.common.*;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;

public class BoardFrontCtl extends HttpServlet {
	
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
			action = new NoticeListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeDetail.bo")) {
			action = new NoticeDetailAction();
			
			try {
				forward = action.execute(req, resp);
			} catch (Exception e){
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeWrite.bo")) {		// 공지 쓰기
			forward = new ActionForward();
			forward.setPath("./board/noticeWrite.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/NoticeWriteAction.bo")) {
			action = new NoticeWriteAction();
			
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/QandA.bo")) {
//			action = new QandAListAction();
//			try {
//				forward = action.execute(req, resp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			forward = new ActionForward();
			forward.setPath("./board/qanda.jsp");
			forward.setRedirect(false);
		}
		
		 else if (command.equals("/ReferenceRoom.bo")) {		// 자료실
			action = new ReferenceGetAction();	
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 } else if (command.equals("/ReferenceWrite.bo")) {		// 자료실 추가 페이지
			forward = new ActionForward();
			forward.setPath("./board/referenceWrite.jsp");	
			forward.setRedirect(false);
		 } else if (command.equals("/ReferenceWriteAction.bo")) {	// 자료실 업로드 동작
			action = new ReferenceWriteAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		 } else if (command.equals("/FileDown.bo")) {
			forward = new ActionForward();
			forward.setPath("./board/fileDown.jsp");	
			forward.setRedirect(false);
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
