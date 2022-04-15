<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.myshop.board.db.CommentDTO"%>
<%@page import="com.myshop.board.db.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.myshop.product.db.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/board_css/reviewDetail.css" rel="stylesheet" type="text/css">

<script src="./scripts/jquery-3.6.0.js"></script>
<script src="scripts/prod_script/prodDetail.js"></script>
<script src="scripts/board_script/rCommentWrite.js"></script>

<title>MYshop</title>
</head>

<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  <%
	ReviewDTO dto = (ReviewDTO)request.getAttribute("dto");
  	String current_user = (String)session.getAttribute("user_id");
  	int review_num = Integer.parseInt(request.getParameter("num"));
  	//request.setAttribute("dto", dto);
  	
  	ArrayList rCommentList = (ArrayList)request.getAttribute("rCommentList");
  	
  %>
  
  <div class="pdetail-container">
	<div class="pinfo-box">
		<img src="../upload/review/${dto.img }">
		<div class="table-box">
		  <table>
			<colgroup>
				<col width="30%"><col width="70%">
			</colgroup>
			<tr>
				<th>아이디</th><td>${dto.author }</td>
			</tr>
			<tr>
				<th>평점</th><td>${dto.score }</td>
			</tr>
			<tr>
				<th>제목</th><td>${dto.title }</td>
			</tr>
			<tr>
				<th>내용</th><td>${dto.content }</td>
			</tr>
		  </table>
		  
		  <div class="table-box-button">
		  <%
		  	if(dto.getAuthor().equals(current_user)) {
		  		%>
	  			  <input type="button" value="수정하기">
	  			  <input type="button" value="삭제" style="background-color: #EDC6C6;">
	  			<%
		  		
		  	} else {
		  		%>
		  		  <input type="button" value="신고하기">
		  		<%
		  	}
		  %>
		  </div>
		  	
		  
		  
		</div>
	</div>
	
	<div class="icon-box">
		<img class="pdetail-icon" src="assets/view-icon.png"> <span>${dto.viewcount }</span>
	</div>
	
	<div class="comment-box">
	  <div class="comment-write-box">
	  	<textarea name="rComment"></textarea>
		<input type="button" value="작성" id="rSubmit" onclick="writeComment(${dto.vidx})">
	  </div>
		
	  <%
	  	if(rCommentList != null) {
			for(int i=0; i<rCommentList.size(); i++) {
				CommentDTO cdto = (CommentDTO) rCommentList.get(i);
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				%>
				  <div class="comment-div">
				  	<img src="assets/comment.png">
				  	<h3><%= cdto.getUserid() %></h3>
				  	<p style="width: 400px;"><%= cdto.getComment() %></p>
				  	<p><%= sd.format(cdto.getRgdate()) %></p>
				  </div>
				<%
				
			}
		}
	  %>
		
	</div>	

	
  </div>
  
  <div id="myModal" class="modal">
      <div class="modal-content">
         <p class="x-box" onClick="close_pop();"><span>X</span></p>

         <p class="pw-text" name="modal-text"></p>

         <div class="modal-submit" id="modal-submit" onclick="deleteScript(${dto.vidx})">확인</div>
      </div>
   </div>
  
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>