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
  <%
  	String what = request.getParameter("what");
  //./FindIDPWAction.us
  %>
  
  <div class="findIDPW-container">
  	<form method="post" onsubmit="return isWrite('<%=what%>')">
  	  <p id="warn-text" style="color:red;"></p>
  	  <%
  	  	if(what.equals("i")) {
  	  		%><label>이름</label><input type="text" name="name"><br><% 
  	  	} else {
  	  		%><label>아이디</label><input type="text" name="id"><br><%
  	  	}
  	  %>
  	  <label>이메일</label><input type="text" name="email"><br>
  	  <p name="email-text">* 가입시 적었던 이메일주소와 같아야 아이디를 조회할 수 있습니다.</p>
  	  
  	  <p id="result-text" style="color:red;"></p>
  	  <input type="submit" value="보내기" class="submit" id="submit">
  	</form>

  </div>
  
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>