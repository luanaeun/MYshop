<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/user_css/signIn.css" rel="stylesheet" type="text/css">
<script src="./scripts/jquery-3.6.0.js"></script>
<script src="scripts/user_script/signIn.js"></script>

<%
	// 쿠키값 가져오기
	String rememberID = "";
	//String loginResult = "3";
	
	Cookie[] cookie = request.getCookies();
	
	// 원하는 쿠키값 찾기
	if(cookie !=  null) {
		if(cookie[0].getName().equals("rememberID")){
			rememberID = cookie[0].getValue();
		}
	}
	System.out.println("쿠키: " + rememberID);

	
	
%>
<div>
  <jsp:include page="../inc/header.jsp"></jsp:include>

  <div class="wrap">
	<div class="form-wrap">
	  <h1 style="text-align: center; margin-top:30px">로그인</h1>

	  <form id="login" action="./SignInAction.us" class="input-group" method="post" onsubmit="return signInCheckFunc()">
		<input type="text" name="id" class="input-field" placeholder="아이디" value=<%=rememberID %>> 
		
		<input type="password" name="pw" class="input-field" placeholder="비밀번호"> 
		<%
		
			// 로그인 로직 결과 가져오기
			int loginResult = 3;
			try {
				loginResult = Integer.parseInt(request.getParameter("r"));
			} catch(Exception e) {
				loginResult = 3;
			}
			
			System.out.println("로그인 로직 결과는? " + loginResult);
			
			if(loginResult == 0) {
				%>
					<p class="loginResult-text">아이디나 비밀번호가 틀립니다.</p><br> 
				<%		
			} else if(loginResult == -1) { 
				%>
					<p class="loginResult-text">비회원 입니다.</p><br> 
				<%
		    } else {
		    	%><p></p><%
		    }
			
		%>	
		
		<%
			if(rememberID == "") {
				%><input type="checkbox" class="checkbox" name="rememberID" value="remember"><%
			} else {
				%><input type="checkbox" class="checkbox" name="rememberID" value="remember" checked="on"><%
			}
		%>
		<span>아이디 기억하기</span>
		
		<div class="social-icons">
<!-- 		  <img src="assets/user/naverLogo.png" alt="네이버"> -->
<!-- 		  <img src="assets/user/kakaoLogo.png" alt="카카오">  -->
<!-- 		  <img src="assets/user/googleLogo.png" alt="구글"> -->
	    </div>
	  
		
		
		<button class="submit">Login</button>
	  </form>
	</div>

</div>


</body>
</html>