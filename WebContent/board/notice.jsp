<%@page import="java.text.SimpleDateFormat"%>
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
	String userid = (String)session.getAttribute("user_id");
	
	String pageNum = (String) request.getAttribute("pageNum");
	int pageCount = (int) request.getAttribute("pageCount");
	int pageBlock = (int) request.getAttribute("pageBlock");
	int startPage = (int) request.getAttribute("startPage");
	int endPage = (int) request.getAttribute("endPage");
  %>  
	
  <div class="notice-container">

    <h1>κ³΅μ§ π’</h1>
    <div>MYshop κ³΅μ§μλλ€.</div>
    <% 
      if(userid != null && userid.equals("admin")) {
    	%><div class="add-text"><a href="./NoticeWrite.bo">μΆκ°</a></div><% 
      }
    %>

    <div class="list_container">
      
      <table class="type09">
        <colgroup>
          	<col width="10%"><col width="55%"><col width="11%"><col width="13%"><col width="11%">
        </colgroup>

		<thead>
		  <tr>
			<th scope="cols">No.</th>
			<th scope="cols">μ λͺ©</th>
			<th scope="cols">μμ±μ</th>
			<th scope="cols">μμ±μΌ</th>
			<th scope="cols">μ‘°νμ</th>
		  </tr>
		</thead>
				
		<tbody>
		  <%
		  	if(noticeList != null) {
    			for(int i=0; i<noticeList.size(); i++) { 
    				NoticeDTO dto = (NoticeDTO) noticeList.get(i);
    			
    				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    		%>
    		
				<tr class="notice-list">
					
					<th scope="row"><%=i+1 %></th>
					<td><a href="./NoticeDetail.bo?num=<%=dto.getNum()%>&pnum=<%=pageNum%>"><%=dto.getTitle() %></a></td>
					<td><%=dto.getName() %></td>
					<td><%=sd.format(dto.getRgdate()) %></td>
					<td><%=dto.getViewcount() %></td>
					
				</tr>
			
		  	<%} 
    			}%>
		</tbody>
	  </table>


	  <div class="page_control">
		<%
			// μ΄μ μ λλ₯΄λ©΄ μ΄μ  λΈλ­μ μ μΌ μ²« νμ΄μ§λ‘ μ΄λ!
			if(startPage > pageBlock) {
			%><a href="./Notice.bo?pageNum=<%=startPage-pageBlock %>">[μ΄μ ]</a> <%
			}
		%>

		<%
			for(int i=startPage; i<=endPage; i++) {
			%><a href="./Notice.bo?pageNum=<%=i %>">[<%=i %>]</a> <%		
			}
		%>

		<%
			if(endPage < pageCount) {
				%><a href="./Notice.bo?pageNum=<%=startPage+pageBlock %>">[Next]</a> <%
			}
		%>

	  </div>

	</div>

  </div> 
	<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
