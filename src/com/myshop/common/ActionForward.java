package com.myshop.common;

public class ActionForward {
  // 사용자 정의 클래스
  // 페이지 이동 시 필요한 정보를 저장하는 클래스
	
  // sendRedirect 이동 방식: 주소랑 화면 둘다 바뀜.
  // forward 이동방식: 주소변경없이 화면만 바뀜. 
	
  private String path;	//이동경로
  private boolean isRedirect;	//이동 방식
  
  public String getPath() {
	return path;
  }
  
  public void setPath(String path) {
	this.path = path;
  }
  
  public boolean isRedirect() {
	return isRedirect;
  }
  
  public void setRedirect(boolean isRedirect) {
	this.isRedirect = isRedirect;
  }
  
  

}
