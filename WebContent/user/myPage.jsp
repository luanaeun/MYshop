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
  		<div class="statistics-box">
  			<div>
  				<p class="my-pcount-text">판매중</p>
  				<p class="my-pcount" style="color: blue;">10</p>	
  			</div>
  			<div>
  				<p class="my-pcount-text">배송 준비중</p>
  				<p class="my-pcount" style="color: orange;">3</p>	
  			</div>
  			<div>
  				<p class="my-pcount-text">판매 완료</p>
  				<p class="my-pcount" style="color: #59DA50;">5</p>	
  			</div>
  		</div>
  	</div>
  	
  	<br><hr><br>
  	
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
  			
  			<tr>
  				<th>주소</th><td>어쩌고</td>
  			</tr>	
  	</table>
  	
  	<div class="myBtn-box">
  		<input type="button" class="my-button" value="정보 수정하기" onclick="location.href='./UpdateUserInfo.us'">
  		<input type="button" class="my-button" value="비밀번호 변경" onclick="location.href='./DeleteUser.us'">
  		<input type="button" class="my-button" value="회원 탈퇴" onclick="location.href='./DeleteUser.us'">
  	</div>
  	
  	
  	
  </div>

  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>