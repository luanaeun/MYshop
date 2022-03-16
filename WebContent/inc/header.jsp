<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link href="css/inc_css/header.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="header_top">

	<div class="top_left">
		<ul class="top_li_nav">
			<li><a href="">페이스북</a></li>
			<li><a href="">인스타</a></li>
		</ul>
	</div>

	<div class="top_right">
		<ul class="top_li_nav">
			<li><a href="${pageContext.request.contextPath}/SignIn.us">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/SignUp.us">회원가입</a></li>
			<li><a href="">주문조회</a></li>
			<li><a href="">배송조회</a></li>
		</ul>
	</div>
</div>


<div class="header-middle">

	<div class="topMiddle_left">
		<a href="${pageContext.request.contextPath}/Main.ma">
			<img src="${pageContext.request.contextPath}/assets/inc/logo.png" alt="" />
		</a>
	</div>


	<div class="topMiddle_right">
	
		<ul>
			<li><a href="${pageContext.request.contextPath}/board/notice.jsp">Notice</a></li>
			<li><a href="#">Q & A</a></li>
			<li><a href="checkout.html">Review</a></li>
		</ul>
		
	</div>
	
	<div class="nav-search-box">
		<input class="" type="text" placeholder="Search" />
	</div>

</div>

</body>
</html>