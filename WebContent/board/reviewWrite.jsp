<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/reviewWrite.css" rel="stylesheet" type="text/css">

<script src="./scripts/jquery-3.6.0.js"></script>
<script src="scripts/board_script/reviewWrite.js"></script>
<title>MYshop</title>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>

<%
	String userid = (String)session.getAttribute("user_id");

%>

<form action="./ReviewWriteAction.bo" method="post" enctype="multipart/form-data" onsubmit="return addReviewCheck()">
	<div class="vWrite-container">
	<h1>리뷰쓰기❤️</h1>
		<table id="notice">
			<colgroup>
          		<col width="30%"><col width="70%">
        	</colgroup>
			
			<tr>
				<td class="add-th">
					<p>사진</p>
<!-- 					<label for="addImg-input"><div class="addImg-plus">+</div></label> -->
					
				</td>
				<td>
					<input type="file" id="addImg-input" name="addImg" accept="image/*" onchange="loadImage(this)">					
					
				</td>
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
					<input type="text" name="title" maxlength="15">
				</td>
			</tr>
 			
 			<tr>
 				<td>내용</td>
				<td>
					<textarea rows="10" cols="20" name="content"></textarea>
				</td>
			</tr>
			
			<tr>
 				<td>별점</td>
				<td>
					<input type="number" name="score" min="1" max="5" step="0.5">
				</td>
			</tr>

		</table>

		<div id="table_search">
			<input type="submit" value="리뷰 올리기" class="submit">
		</div>
		<div class="clear"></div>
	</div>
</form>

<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>