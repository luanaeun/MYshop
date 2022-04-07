<%@page import="com.myshop.board.db.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/board_css/noticeDetail.css" rel="stylesheet" type="text/css">
<link href="./css/index.css" rel="stylesheet" type="text/css">
<title>MYshop</title>
</head>
<body>
<%
	NoticeDTO dto = (NoticeDTO)request.getAttribute("dto");
%>
  			<h1>글 목록</h1>
			<table id="notice">
				<td>
					<th class="tno" colspan="4"></th>
				</td>
				<tr>
					<td>글 번호</td>
					<td><%=dto.getNum()%></td>
					<td>조회수</td>
					<td><%=dto.getViewcount()%></td>

				</tr>

				<tr>
					<td>글쓴이</td>
					<td><%=dto.getName() %></td>
					<td>작성일</td>
					<td><%=dto.getRgdate() %></td>
				</tr>

				<tr>
					<td>글제목</td>
					<td colspan="3"><%=dto.getTitle()%></td>
				</tr>

				<tr>
					<td>글내용</td>
					<td colspan="3"><%=dto.getContent()%></td>
				</tr>


			</table>
			<input type="button" value="수정하기" onclick="location.href='./NoticeUpdate.bo?num=<%=dto.getNum() %>'">
  

</body>
</html>