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
			<label for="addImg-input"><div class="addImg-plus">+</div></label>
        	<input type="file" multiple id="addImg-input" name="addImg" accept="image/*" onchange="loadImage(this)">
        	<input type="hidden" name="fileNames" value1="" value2="" value3="" value4="" value5="">
    	</div>

		<p id="info-warning" class="warning-text"></p>
		<table>
			<colgroup>
          		<col width="20%"><col width="30%"><col width="20%"><col width="30%">
        	</colgroup>
        	<tr>
				<th>*제품명</th>
				<td><input type="text" name="name" minlength="2" maxlength='10' placeholder="2~10글자"></td>
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
				<th>*가격</th>
				<td><input type="text" name="price" placeholder="숫자만 입력"></td>
				<th>*수량</th>
				<td><input type="text" name="stock" ></td>
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
		<p><textarea placeholder="상세 정보 입력" name="detailInfo"></textarea></p>


		<div>
			<input type="submit" value="완료" class="submit-btn">
		</div>
		<div class="clear"></div>
</form>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>