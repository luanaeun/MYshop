<%@page import="java.util.ArrayList"%>
<%@page import="com.myshop.product.db.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/product_css/prodDetail.css" rel="stylesheet" type="text/css">
<title>MYshop</title>
</head>

<body>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  <%
	ProductDTO dto = new ProductDTO();
  %>
  
  <div class="pdetail-container">
	<div class="pinfo-box">
		<img src="./productImgs/${dto.sumbnail }">
		<div class="table-box">
		  <table>
			<colgroup>
				<col width="30%"><col width="70%">
			</colgroup>
			<tr>
				<th>상품명</th><td>${dto.name }</td>
			</tr>
			<tr>
				<th>가격</th><td>${dto.price }원</td>
			</tr>
			<tr>
				<th>컬러</th><td>컬러가 없네..</td>
			</tr>
			<tr>
				<th>배송비</th><td>${dto.deliCharge } 원</td>
			</tr>
			<tr>
				<th>출고일</th><td>${dto.deliDays }</td>
			</tr>
			<tr>
				<th>배송조건</th><td>${dto.howDeli }</td>
			</tr>
		  </table>
		  
		  <div class="table-box-button">
		  	<input type="button" value="장바구니">
		  	<input type="button" value="찜 하기">
		  	<input type="button" value="바로 구매" style="background-color: #E4F7BA;">
		  </div>
		  
		</div>
	</div>
	
	<div class="icon-box">
		<img class="pdetail-icon" src="assets/view-icon.png"> <span>${dto.viewCount }</span>
		<img class="pdetail-icon" src="assets/wish-icon3.png"> ${dto.wishCount }
	</div>
	
	<div class="pdetail-box">
		<p>
			${dto.content }
		</p>
		<%
			ArrayList images = dto.getImages();
			System.out.println("배열에 잘 있니?" + images);
			for(int i=0; i<images.size(); i++) {
				
					%><img src="./productImgs/${images.get(i) }"><%
				
			}
		%>
		
	</div>
	
	<div style="float: right; margin-top: 30px;">
	  <div class="user-info-box">
		<img src="">
		<h3>${dto.userid }</h3><br>
		<p>판매자의 제품 보러 가기 👉</p>
	  </div>
	</div>
	
  </div>
  
  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>