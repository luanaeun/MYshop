<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/user_css/myPage.css" rel="stylesheet" type="text/css">
<title>MyPage</title>
</head>
<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>

  <div class="mypage-container">
  	<div class="default-info-box">
  		<img alt="" src="assets/user/defaultPerson.png" class="user-img">
  		<table class="info-table01">
  			<colgroup>
          		<col width="20%"><col width="30%"><col width="20%"><col width="30%">
        	</colgroup>
  			<tr>
  				<th>아이디</th><td>어쩌고</td>
  				<th>생년월일</th><td>어쩌고</td>
  			</tr>
  			
  			<tr>
  				<th>이름</th><td>어쩌고</td>
  				<th>이메일</th><td>어쩌고</td>
  			</tr>
  			
  			<tr>
  				<th>핸드폰 번호</th><td>어쩌고</td>
  				<th>성별</th><td>어쩌고</td>
  			</tr>
			
  		</table>
  	</div>
  	
  	<div class="other-info-box">
  		<table class="inf0-table02">
  			<th>이름</th><th>이름</th>

  			
  			<tr>
  				<th>이름</th><td>어쩌고</td>
  				<th>이름</th><td>어쩌고</td>
  			</tr>
  			
  			<tr>
  				<th>이름</th><td>어쩌고</td>
  				<th>이름</th><td>어쩌고</td>
  			</tr>
			
  		</table>
  	</div>
  	
  	<hr>
  	
  	<div class="statistics-box">
  		<div>하나</div>
  		<div>둘</div>
  		<div>셋</div>
  	</div>
  </div>

  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>