<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MYshop</title>
<link href="css/board_css/qanda.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  
    <div class="qa-container">

    <h1>질문 게시판</h1>
    <div>질문하는 공간입니다 :)</div>

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
//     		for(int i=0; i<noticeList.size(); i++) { 
//     		NoticeDTO dto = (NoticeDTO) noticeList.get(i);
    		%>
    		
				<tr class="notice-list">
					
					<th scope="row">하하</th>
					<td>하하</td>
					<td>하하</td>
					<td>하하</td>
					<td>하하</td>
<%-- 					<td><%=dto.getName() %></td> --%>
<%-- 					<td><%=dto.getRgdate() %></td> --%>
<%-- 					<td><%=dto.getViewcount() %></td> --%>
					
				</tr>
			
		  	<% //} 
		  	%>
		</tbody>
	  </table>


	</div>

  </div> 


  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>