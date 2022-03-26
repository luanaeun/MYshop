<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/user_css/signIn.css" rel="stylesheet" type="text/css">
<script src="./scripts/jquery-3.6.0.js"></script>
<script src="scripts/user_script/signIn.js"></script>

<div>
  <jsp:include page="../inc/header.jsp"></jsp:include>

  <div class="wrap">
	<div class="form-wrap">
	  <h1 style="text-align: center; margin-top:30px">로그인</h1>

	  <form id="login" action="./SignInAction.us" class="input-group" method="post" onsubmit="return signInCheckFunc()">
		<input type="text" name="id" class="input-field" placeholder="아이디"> 
		<div id="id" class="check-result"></div>
		
		<input type="password" name="pw" class="input-field" placeholder="비밀번호"> 
		<div id="pw" class="check-result"></div>
		
		<input type="checkbox" class="checkbox" > 
		<span>아이디 기억하기</span>
		
		<div class="social-icons">
		  <img src="assets/user/naverLogo.png" alt="네이버">
		  <img src="assets/user/kakaoLogo.png" alt="카카오"> 
		  <img src="assets/user/googleLogo.png" alt="구글">
	    </div>
	  
		
		
		<button class="submit">Login</button>
	  </form>
	</div>


	<script>
// 		var x = document.getElementById("login");
// 		var y = document.getElementById("register");
// 		var z = document.getElementById("btn");

// 		function login() {
// 			x.style.left = "50px";
// 			y.style.left = "450px";
// 			z.style.left = "0";
// 		}

// 		function register() {
// 			x.style.left = "-400px";
// 			y.style.left = "50px";
// 			z.style.left = "110px";
// 		}
	</script>

</div>


</body>
</html>