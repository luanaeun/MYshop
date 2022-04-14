package com.myshop.user.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.common.MyAuthentication;
import com.myshop.user.db.UserDAO;
import com.myshop.user.db.UserDTO;

public class FindIDPWAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("메일 보내는 Action들어옴");
		req.setCharacterEncoding("UTF-8");
		
		String what = req.getParameter("what");
		
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		System.out.println("다 들어오는지 확인" + what + name + id + email);
		
		UserDAO dao = new UserDAO();
		PrintWriter out = res.getWriter();

		System.out.println("try문들어옴");
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");	// 로그인시 TLS를 사용할 것인지 설정
		prop.put("mail.smtp.host", "smtp.naver.com"); 	// 이메일 발송을 처리해줄 SMTP서버
		prop.put("mail.smtp.auth", "true");				// SMTP 서버의 인증을 사용한다는 의미
		prop.put("mail.smtp.port", "587");				// TLS의 포트번호는 587이며 SSL의 포트번호는 465이다.
		
		// soket문제와 protocol문제 해결
		prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
		prop.put("mail.smtp.socketFactory.fallback", "false");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		Authenticator auth = new MyAuthentication();
		Session se = Session.getDefaultInstance(prop, auth);
		
		// 아이디를 찾을때
		if(what.equals("i")){	
		  int findResult = 0;
		  String result = dao.findID(name, email);
		  if (result != "") {
			try {  
			  System.out.println("이메일 보내기 들어옴");
			  Message msg = new MimeMessage(se);
			  InternetAddress sender_address = new InternetAddress("cieloeun@naver.com");	// 보내는 사람 표시
			  InternetAddress receiver_address = new InternetAddress(email);			// 받을 이메일 주소
				
			  msg.setHeader("content-type", "text/html;charset=UTF-8");
			  msg.setSentDate(new java.util.Date());							// 보내는 날짜
			  msg.setFrom(sender_address);									// 송산자
			  msg.addRecipient(Message.RecipientType.TO, receiver_address);	// 수신자
			  msg.setSubject("MYshop 아이디 찾기");
			  
			  String mailText = name + "님의 아이디는 " + result + " 입니다.";
			  //msg.setText(mailText);
			  msg.setText(mailText);
				
			  Transport.send(msg);
			  System.out.println("메일 보내기 완료");
			  
			  findResult = 1;
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("실패");
				findResult = 0;
			}
		  } else {
			  findResult = -1;
			  System.out.println("메일 보내기 실패");
		  }
		  out.print(findResult);
		}  
		  
		  
		if(what.equals("p")){
			System.out.println("얘는 안들어오지?");
			int resultPW = dao.findPW(id, name);
			if(resultPW == 1) {
				try {			
					Message msg = new MimeMessage(se);
					InternetAddress sender_address = new InternetAddress("cieloeun@naver.com");	// 보내는 사람 표시
					InternetAddress receiver_address = new InternetAddress(email);			// 받을 이메일 주소
					
					msg.setHeader("content-type", "text/html;charset=UTF-8");
					msg.setSentDate(new java.util.Date());							// 보내는 날짜
					msg.setFrom(sender_address);									// 송산자
					msg.addRecipient(Message.RecipientType.TO, receiver_address);	// 수신자
					msg.setSubject("MYshop 아이디 찾기");
					msg.setText("");
					
					Transport.send(msg);
					System.out.println("메일 보내기 완료");
					out.print("이메일로 확인번호를 보냈습니다.");
				} catch(Exception e) {
					System.out.println("메일 보내기 실패");
					e.printStackTrace();
				}
			} else {
				out.print("정보가 없습니다. 다시 입력해주세요.");
			}
		}



		// 페이지 이동
//		ActionForward forward = new ActionForward();
//		forward.setPath("./user/signIn.jsp");
//		forward.setRedirect(false);
//
//		return forward;
		return null;
	}
}
