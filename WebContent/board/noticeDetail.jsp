<%@page import="com.myshop.board.db.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/noticeDetail.css" rel="stylesheet" type="text/css">

<script src="./scripts/jquery-3.6.0.js"></script>
<script src="scripts/board_script/noticeDetail.js"></script>

<title>MYshop</title>
</head>
<body>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("dto");
	String userid = (String)session.getAttribute("user_id");
%>
<jsp:include page="../inc/header.jsp"></jsp:include>


	<div class="nWrite-container">
		<table id="notice">
			<colgroup>
          		<col width="20%"><col width="80%">
        	</colgroup>

			<tr>
				<th colspan="2"><%=dto.getTitle() %></th>
			</tr>
			
			<tr>
 				<td>글 번호</td>
				<td>
	 				<%=dto.getNum()%>
				</td>
			</tr>
			<tr>
 				<td>글쓴이</td>
				<td><%=dto.getName() %></td>
			</tr>
 			
 			<tr>
 				<td>조회수</td>
				<td><%=dto.getViewcount()%></td>
			</tr>
 			
 			<tr>
 				<td>내용</td>
				<td>
					<textarea disabled overflow="auto"> ${dto.content }</textarea>
				</td>
			</tr>
		</table>

	<% 
      	if(userid != null && userid.equals("admin")) {
    		%>
    		<div>
				<input type="button" value="수정하기" class="detail-btn" onclick="location.href='./NoticeUpdate.bo?num=<%=dto.getNum() %>'">
				<input type="button" value="삭제하기" class="detail-btn" onclick="showModal()">
			</div>
    		<% 
      	}
    %>
	</div>
	
	<div id="myModal" class="modal">
      <div class="modal-content">
      	<form action="./NoticeDeleteAction.bo?num=<%=dto.getNum()%>" method="post" onsubmit="return pwWriteCheck()">	
         	<p class="x-box" onClick="close_pop();"><span>X</span></p>

			<p style="color: red; font-size: 11pt;" name="warnning-text"></p>
         	<p class="pw-text" name="modal-text">비밀번호 입력</p>
         	<input type="password" class="modal-pw" name="pw"><br>

         	<input type="submit" class="modal-submit" id="modal-submit" value="확인">
      	</form>
      </div>
   </div>


<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>