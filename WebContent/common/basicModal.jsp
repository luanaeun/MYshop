<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common_css/basicModal.css" rel="stylesheet" type="text/css">
<title>MYshop</title>
</head>
<body>
	<%
		String modalText = request.getParameter("text");
	%>
  	<div id="myModal" class="modal">
      <div class="modal-content">
      	 <form action="./UserpwCheck.us" method="post" onsubmit="return pwWriteCheck();">
         	<p class="x-box" onClick="close_pop();"><span>X</span></p>
         	<input type="hidden" name="what">

		 	<p style="color: red; font-size: 11pt;" name="warnning-text"></p>
         	<p class="pw-text" name="modal-text">${modalText }</p>
         
         	<input type="password" class="modal-pw" name="pw"><br>
            
         	<input type="submit" class="modal-submit" value="확인">
<!--          	<div class="modal-submit">확인</div> -->
         </form>  
      </div>
    </div>

</body>
</html>