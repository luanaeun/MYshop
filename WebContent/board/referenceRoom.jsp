<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.myshop.board.db.ReferenceDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/referenceRoom.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  	
  <%
	int referenceCnt = (Integer)request.getAttribute("referenceCnt");
	ArrayList referenceList = (ArrayList) request.getAttribute("referenceList");
	
	String userid = (String)session.getAttribute("user_id");
  %>  
	
  <div class="notice-container">

    <h1>자료실 📃</h1>
    <div>MYshop 자료실입니다.</div>
    	<% 
    	  if(userid.equals("admin")) {
    		  %><div class="add-text"><a href="./ReferenceWrite.bo">추가</a></div><% 
    	  }
    	%>
    	
    	
    <div class="list_container">
      <table class="type09">
        <colgroup>
          	<col width="10%"><col width="50%"><col width="11%"><col width="18%"><col width="11%">
        </colgroup>

		<thead>
		  <tr>
			<th scope="cols">No.</th>
			<th scope="cols">제목</th>
			<th scope="cols">작성자</th>
			<th scope="cols">다운로드</th>
			<th scope="cols">다운횟수</th>
		  </tr>
		</thead>
		
		
		<tbody>
		  <%
		  	if(referenceList != null) {
    			for(int i=1; i<=referenceList.size(); i++) { 
    				ReferenceDTO dto = (ReferenceDTO) referenceList.get(i-1);
    		%>
				<tr class="notice-list">
					
					<th scope="row"><%=i %></th>
					<td><%=dto.getTitle() %></td>
					<td><%=dto.getAuthor() %></td>
					
					<td class="fileName">
						<a href="./FileDown.bo?file_name=<%=dto.getFile() %>">
						<%=dto.getFile() %>
						</a>
					</td>
					
					<td><%=dto.getDowncount() %></td>
					
				</tr>
		  	<%} 
    			}%>
		  </tbody>
	    </table>
		
 	  </div>
	</div>

	<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>

</html>