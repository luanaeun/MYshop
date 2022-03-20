<%@page import="com.myshop.board.db.NoticeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/notice.css" rel="stylesheet" type="text/css">
<title>MYshop</title>
</head>

<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  	
  <%
	int noticeCnt = (Integer)request.getAttribute("noticeCnt");
	ArrayList noticeList = (ArrayList) request.getAttribute("noticeList");
	
 	String pageNum = (String) request.getAttribute("pageNum");
// 		int pageCount = (int) request.getAttribute("pageCount");
// 		int pageBlock = (int) request.getAttribute("pageBlock");
// 		int startPage = (int) request.getAttribute("startPage");
// 		int endPage = (int) request.getAttribute("endPage");
  %>
	
  <div class="notice-container">

    <h1>공지 📢</h1>
    <div>MYshop 공지입니다.</div>

    <div class="list_container">
      
      <table class="type09">
        <colgroup>
          	<col width="10%"><col width="55%"><col width="11%"><col width="13%"><col width="11%">
        </colgroup>

		<thead>
		  <tr>
			<th scope="cols">No.</th>
			<th scope="cols">제목</th>
			<th scope="cols">작성자</th>
			<th scope="cols">작성일</th>
			<th scope="cols">조회수</th>
		  </tr>
		</thead>
				
		<tbody>
		  <%
    		for(int i=0; i<noticeList.size(); i++) { 
    		NoticeDTO dto = (NoticeDTO) noticeList.get(i);
    		%>
    		
				<tr class="notice-list">
					
					<th scope="row"><%=dto.getNum() %></th>
					<td><a href="./NoticeDetail.bo?num=<%=dto.getNum()%>"><%=dto.getTitle() %></a></td>
					<td><%=dto.getName() %></td>
					<td><%=dto.getRgdate() %></td>
					<td><%=dto.getViewcount() %></td>
					
				</tr>
			
		  	<%} %>
		</tbody>
	  </table>
    
    </div>

  </div> 
	<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
