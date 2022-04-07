<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.myshop.board.db.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/review.css" rel="stylesheet" type="text/css">

<title>Insert title here</title>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  	
  <%
	//int referenceCnt = (Integer)request.getAttribute("referenceCnt");
	ArrayList reviewList = (ArrayList) request.getAttribute("reviewList");
	
	String userid = (String)session.getAttribute("user_id");
	
	
  %>  
	
  <div class="review-container">

    <h1>리뷰 📸</h1>
    <div style="margin-left:15px;">⭐⭐⭐⭐⭐</div>
    <div class="add-text"><a href="./ReviewWrite.bo">리뷰 작성하기❤️</a></div>
    
    	
    <div class="list_container">
      <table class="type09">
        <colgroup>
          	<col width="10%"><col width="50%"><col width="11%"><col width="18%"><col width="11%">
        </colgroup>

		<thead>
		  <tr>
			<th scope="cols">사진</th>
			<th scope="cols">내용</th>
			<th scope="cols">별점</th>
			<th scope="cols">아이디</th>
			<th scope="cols">날짜</th>
		  </tr>
		</thead>
		
		
		<tbody>
		  <%
		  	if(reviewList != null) {
		  		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		  		
    			for(int i=1; i<=reviewList.size(); i++) { 
    				ReviewDTO dto = (ReviewDTO) reviewList.get(i-1);
    		%>	

				<tr class="review-list" onclick="location.href='./ReviewDetail.bo?num=<%=dto.getVidx() %>'">		
					<th scope="row" rowspan="2">
						<img src="./upload/review/<%=dto.getImg() %>">
					</th>
					<td style="text-align: left;">
						<p>
							<h3><%=dto.getTitle() %></h3>
							<p><%=dto.getContent() %></p>
						</p>
					</td>
					
					<td class="fileName" rowspan="2"><%=dto.getScore() %></td>
					
					<td rowspan="2"><%=dto.getAuthor() %></td>
					<td rowspan="2"><%=sd.format(dto.getRgdate()) %></td>
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