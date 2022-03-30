<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/product_css/addProduct.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">

<script src="./scripts/jquery-3.6.0.js"></script>
<script src="scripts/prod_script/addProduct.js"></script>

<title>MYshop</title>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>

<form action="./AddProductAction.pd" method="post" enctype="multipart/form-data">
  	<div class="addProd-container">
		<h1>제품 등록</h1>
		
		<h4>이미지 추가</h4>
		<div class="images-box" id="images-box">
			<p id="img-warning" class="img-warning"></p>
			<label for="addImg-input"><div class="addImg-plus">+</div></label>
        	<input type="file" multiple id="addImg-input" name="addImg" accept="image/*" onchange="loadImage(this)">

    	</div>

	
		<h4>상세 정보</h4>
		<p><textarea placeholder="상세 정보 입력"></textarea></p>
	
	
		<table id="notice">
			<colgroup>
          		<col width="20%"><col width="30%"><col width="20%"><col width="30%">
        	</colgroup>
			<tr>
				<th class="twrite">가격</th>
				<td><input type="text" name="" value=""></td>
				<th class="twrite">배송비</th>
				
				<td><input type="text" name="" value=""></td>
			</tr>
				<th class="twrite">수량</th>
				<td><input type="text" name="" value=""></td>
				<th class="twrite">배송조건</th>
				
				<td><input type="text" name="" value=""></td>
		</table>

		<div id="table_search">
			<input type="submit" value="완료" class="btn">
		</div>
		<div class="clear"></div>
</form>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>