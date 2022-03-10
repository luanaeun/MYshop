<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="../css/user_css/login.css" rel="stylesheet" type="text/css">

<div>
  <jsp:include page="../inc/header.jsp"></jsp:include>

  <div class="wrap">
	<div class="form-wrap">
	  <h1 style="text-align: center; margin-top:30px">로그인</h1>

	  <form id="login" action="" class="input-group">
		<input type="text" class="input-field" placeholder="User name or Email" required> 
		<input type="password" class="input-field" placeholder="Enter Password" required> 
		<input type="checkbox" class="checkbox">
		<span>Remember ID</span>
		
		<div class="social-icons">
		  <img src="img/fb.png" alt="facebook"> 
		  <img src="img/tw.png" alt="twitter"> 
		  <img src="img/gl.png" alt="google">
	    </div>
	  
		
		
		<button class="submit">Login</button>
	  </form>
	</div>


	<script>
		var x = document.getElementById("login");
		var y = document.getElementById("register");
		var z = document.getElementById("btn");

		function login() {
			x.style.left = "50px";
			y.style.left = "450px";
			z.style.left = "0";
		}

		function register() {
			x.style.left = "-400px";
			y.style.left = "50px";
			z.style.left = "110px";
		}
	</script>

</div>


</body>
</html>