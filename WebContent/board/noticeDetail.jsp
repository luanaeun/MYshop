<%@page import="com.myshop.board.db.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/noticeWrite.css" rel="stylesheet" type="text/css">
<title>MYshop</title>
</head>
<body>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("dto");
%>
<jsp:include page="../inc/header.jsp"></jsp:include>

<form action="./NoticeWriteAction.bo" method="post">
	<div class="nWrite-container">
	<h1>게시판 글쓰기</h1>
		<table id="notice">
			<colgroup>
          		<col width="20%"><col width="80%">
        	</colgroup>

			<tr>
				<th class="twrite">입력</th>
				<th class="ttitle">게시판</th>
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
					${dto.content }
				</td>
			</tr>
			
			

		</table>

		<div id="table_search">
			<input type="button" value="수정하기" onclick="location.href='./NoticeUpdate.bo?num=<%=dto.getNum() %>'">
  
		</div>
		<div class="clear"></div>
	</div>
	
</form>

<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>