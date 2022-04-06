<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/referenceWrite.css" rel="stylesheet" type="text/css">
<title>MYshop</title>
</head>
<body>
	<jsp:include page="../inc/header.jsp"></jsp:include>
	
	<%
		String userid = (String)session.getAttribute("user_id");
	%>
	<form action="./ReferenceWriteAction.bo" method="post" enctype="multipart/form-data">
		<div class="nWrite-container">
			<h1>자료실 글쓰기</h1>
			<table id="notice">
				<colgroup><col width="20%"><col width="80%"></colgroup>

				<tr>
					<th>입력</th>
					<th>게시판</th>
				</tr>
			
				<tr>
 					<td>작성자</td>
					<td>
						<input type="input" name="author" value="<%=userid %>">
					</td>
				</tr>
 			
 				<tr>
 					<td>제목</td>
					<td>
						<input type="text" name="title" >
					</td>
				</tr>
				
				<tr>
 					<td>파일</td>
					<td>
						<input type="file" name="file" >
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="등록" class="submit"></td>
				<tr>
			</table>

		
		</div>
	</form>
	
	
	<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>