<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/board_css/notice.css" rel="stylesheet" type="text/css">


<body>
	<jsp:include page="../inc/header.jsp"></jsp:include>
  	<jsp:include page="../inc/category.jsp"></jsp:include>
	
	<div class="notice-container">

    	<h1>공지 📢</h1>
        <div>MYshop 공지입니다.</div>

      <div class="list_container">
      
        <table class="type09">
        	<colgroup>
          		<col width="10%"><col width="60%"><col width="10%"><col width="10%"><col width="10%">
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
				<tr class="notice-list">
						<th scope="row">번호</th>
						<td>내용이 들어갑니다.</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
				</tr>

			</tbody>
		</table>
        
       </div>

    
    </div>
	<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
