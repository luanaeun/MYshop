<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
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

<%
	LinkedHashMap cateList = (LinkedHashMap) request.getAttribute("cateList");
%>

<form action="./AddProductAction.pd" method="post" enctype="multipart/form-data" onsubmit="return addProdCheck()">
  	<div class="addProd-container">
		<h1>제품 등록</h1>
		
		<h4>이미지 추가</h4>
		<p>제일 처음 이미지는 썸네일로 활용됩니다.</p>
		<p id="img-warning" class="warning-text"></p>
		<div class="images-box" id="images-box">
			<div class="detail-imgbox" id="detail-imgbox1">
				<label for="img-input1"><div class="addImg-plus">+</div></label>
				<input type="file" id="img-input1" class="img-input" name="addImg1" accept="image/*" onchange="loadImage(this, 1)">
			</div>
			
			
			<div class="detail-imgbox" id="detail-imgbox2">
				<label for="img-input2"><div class="addImg-plus">+</div></label>
				<input type="file" id="img-input2" class="img-input" name="addImg2" accept="image/*" onchange="loadImage(this, 2)">
				
			</div>
			
			<div class="detail-imgbox" id="detail-imgbox3">
				<label for="img-input3"><div class="addImg-plus">+</div></label>
				<input type="file" id="img-input3" class="img-input" name="addImg3" accept="image/*" onchange="loadImage(this,3)">
			</div>
			
			<div class="detail-imgbox" id="detail-imgbox4">
				<label for="img-input4"><div class="addImg-plus">+</div></label>
				<input type="file" id="img-input4" class="img-input" name="addImg4" accept="image/*" onchange="loadImage(this,4)">
			</div>
			
			<div class="detail-imgbox" id="detail-imgbox5">
				<label for="img-input5"><div class="addImg-plus">+</div></label>
				<input type="file" id="img-input5" class="img-input" name="addImg5" accept="image/*" onchange="loadImage(this,5)">
			</div>
		
    	</div>
    	

		<p id="info-warning" class="warning-text"></p>
		<table>
			<colgroup>
          		<col width="20%"><col width="30%"><col width="20%"><col width="30%">
        	</colgroup>
        	<tr>
        		<th>*제품명</th>
				<td><input type="text" name="name" minlength="2" maxlength='15' placeholder="2~15글자"></td>
        	</tr>
        	
        	<tr>
				<th>*가격</th>
				<td><input type="text" name="price" placeholder="숫자만 입력"></td>
				<th>*수량</th>
				<td><input type="text" name="stock" ></td>
			</tr>
       	
        	<tr>
				<th>색상</th>
				<td>
					<select name="color">
					  <option value="">--선택하기--</option>
					  <option value="빨강" style="background-color: red;">빨강</option>
					  <option value="주황" style="background-color: orange;">주황</option>
					  <option value="노랑" style="background-color: yellow;">노랑</option>
					  <option value="초록" style="background-color: green;">초록</option>
					  <option value="파랑" style="background-color: blue;">파랑</option>
					  <option value="남색" style="background-color: navy;">남색</option>
					  <option value="보라" style="background-color: purple;">보라</option>
					  <option value="하양" style="background-color: white;">하양</option>
					  <option value="검정" style="background-color: black;">검정</option>
				</td>
				<th>*카테고리</th>
				<td>
					<select name="category">
						<option value="">--선택하기--</option>
						<%
							if(cateList != null) {
								for (Object i: cateList.keySet()) {
									
									ArrayList detailList = (ArrayList) cateList.get(i);
			    					if (detailList.size() > 0) {
			    						//ArrayList detailList = (ArrayList) cateList.get(i);
			    						%><option disabled class="select-option"><%=i %></option><% 
										for(Object j : detailList) {
											%><option name="<%=j%>"><%=j %></option><% 
										}
			    					} else {
			    						%><option class="select-option" name="<%=i%>"><%=i %></option><% 
			    					}	
			    				}
							}
						%>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>*배송비</th>
				<td><input type="text" name="deliveryCharge" value=""></td>
				<th>출고기간</th>
				<td><input type="text" name="deliDays" maxlength='20' placeholder="ex) 3~5일 뒤"></td>
			</tr>
			<tr>
				<th class="twrite">배송조건</th>
				<td colspan="3">
					<input type="text" name="howDeli" maxlength="20" placeholder="ex) 20,000원 이상 무료배송">
				</td>
				
			</tr>
		</table>
		
		
		<h4>상세 정보</h4>
		<p><textarea placeholder="상세 정보 입력" name="content"></textarea></p>


		<div>
			<input type="submit" value="완료" class="submit-btn">
		</div>
		<div class="clear"></div>
</form>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>