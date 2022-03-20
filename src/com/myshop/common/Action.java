package com.myshop.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myshop.common.ActionForward;

public interface Action {
	  // 객체를 생성할 수 없음, 추상메서드 포함.
	  // 인터페이스의 추상메서드는 상속관계에서는 반드시 구현해야한다.(강제성)
		
	  //MemberFrontCtl.java에서 만든 ActioinForward를 반환해주는 추상 메서드.
  public abstract ActionForward execute(
	HttpServletRequest req, HttpServletResponse res) throws Exception;
	  
	  	
	  
}
