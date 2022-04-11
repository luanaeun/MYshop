<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/user_css/findIDPW.css" rel="stylesheet" type="text/css">

<script src="./scripts/jquery-3.6.0.js"></script>
<script src="scripts/user_script/findIDPW.js"></script>

<title>MYshop</title>
</head>

<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  
  
  <div class="findIDPW-container">
  	<form action="./FindIDPWAction.us" method="post" onsubmit="return isWrite()">
  	  <label>이름</label><input type="text" name="name"><br>
  	  <label>이메일</label><input type="text" name="email"><br>
  	  <p>* 가입시 적었던 이메일주소와 같아야 아이디를 조회할 수 있습니다.</p>
  	  
  	  <input type="submit" value="보내기" class="submit">
  	</form>
  
  
  </div>
  
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>