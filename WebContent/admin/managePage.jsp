<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/admin_css/managePage.css" rel="stylesheet" type="text/css">

<title>MYshop</title>
</head>
<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  
  <div class="managePage-container">
	<h3>세부 카테고리 추가</h3>
	<form action="./AddDetialCateAction.am" method="post">
	  상위 카테고리:  
    	<select name="topCategory">
        	<option value="">상위 카테고리를 선택하시오.</option>
            <option value="기획전">기획전</option>
            <option value="식품">식품</option>
            <option value="의류">의류</option>
            <option value="뷰티">뷰티</option>
            <option value="주방용품">주방용품</option>
            <option value="뷰티">가구</option>
            <option value="디지털">디지털</option>
            <option value="스포츠">스포츠</option>
            <option value="도서·음반·문구">도서·음반·문구</option>
            <option value="반려동물">반려동물</option>
            <option value="기타">기타</option>
        </select><br>
	  카테고리 이름: <input type="text" name="cateName"> <br>
	
	  <input type="submit" value="카테고리 추가">
	</form>
  </div>
  
 
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>