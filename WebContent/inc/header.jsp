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
<%
	String user_id = (String) session.getAttribute("user_id");
%>

<div class="header_top">

	<div class="top_left">
		<ul class="top_li_nav">
			<li><a href="">페이스북</a></li>
			<li><a href="">인스타</a></li>
		</ul>
	</div>

	<div class="top_right">
		<ul class="top_li_nav">

			
			<%
				if(user_id == null) {
					%><li><a href="${pageContext.request.contextPath}/SignIn.us">로그인</a></li>
					  <li><a href="${pageContext.request.contextPath}/SignUp.us">회원가입</a></li><% 
				} else {
					%><li>${user_id} 님</li>
					  <li><a href="${pageContext.request.contextPath}/MyPage.us">마이페이지</a></li>
					  <li><a href="${pageContext.request.contextPath}/SignOut.us">로그아웃</a></li><% 
				}
			%>
			
			<li><a href="${pageContext.request.contextPath}/Preparing.pr">주문조회</a></li>
			<li><a href="${pageContext.request.contextPath}/Preparing.pr">배송조회</a></li>
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
			<li><a href="${pageContext.request.contextPath}/Notice.bo">Notice</a></li>
			<li><a href="${pageContext.request.contextPath}/QandA.bo">Q & A</a></li>
			<li><a href="${pageContext.request.contextPath}/Preparing.pr">Review</a></li>
		</ul>
		
	</div>
	
	<div class="nav-search-box">
		<input class="" type="text" placeholder="Search" />
	</div>

</div>

</body>
</html>