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
 				<td>작성자</td>
				<td>
<%-- 					<input type="input" name="name" value="<%=request.getAttribute("id")%>"> --%>
					<input type="input" name="name" value="admin">
				</td>
			</tr>
 			
 			<tr>
 				<td>제목</td>
				<td>
					<input type="text" name="title" >
				</td>
			</tr>
 			
 			<tr>
 				<td>내용</td>
				<td>
					<textarea rows="10" cols="20" name="content"></textarea>
				</td>
			</tr>
			
			<tr>
 				<td>비번</td>
				<td>
					<input type="password" name="pw" >
				</td>
			</tr>

		</table>

		<div id="table_search">
			<input type="submit" value="글쓰기" class="btn">
		</div>
		<div class="clear"></div>
	</div>
</form>

<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>