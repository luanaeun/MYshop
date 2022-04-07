<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<link href="${pageContext.request.contextPath}/css/product_css/mainpList.css" rel="stylesheet">

<%
	request.setCharacterEncoding("UTF-8");

	String sectionName = request.getParameter("sectionName");
	String pageLink = request.getParameter("pageLink");
%>


<div class="productList-container">
	<div class="prodList-title">
		<h3><%=sectionName %></h3>
		<span><a href="<%=pageLink %>">+ 더보기</a></span>
	</div>

	<div class="prod-total">
		
		<div class="prod-box" onclick="location.href='prodDetail.jsp'">
		  <a href="./ProductDetail.pd">
			<img class="prod-view" alt="" src="assets/testImage/productTest.png">
			<h4>하늘색 기본 티</h4>
			<p>30,000원</p>
		  </a>
		</div>
		

		<div class="prod-box" onclick="location.href='prodDetail.jsp'">
		  <a href="./ProductDetail.pd">
			<img class="prod-view" alt="" src="assets/testImage/productTest.png">
			<h3>하늘색 기본 티</h3>
			<p>30,000원</p>
		  </a>
		</div>

		
		<div class="prod-box">
		  <a href="./ProductDetail.pd">
			<img class="prod-view" alt="" src="assets/product/productTest.png">
			<h3>하늘색 기본 티</h3>
			<p>30,000원</p>
		  </a>
		</div>
		

		<div class="prod-box">
			<img class="prod-view" alt="" src="assets/product/productTest.png">
			<h3>하늘색 기본 티</h3>
			<p>30,000원</p>
		</div>

		<div class="prod-box">
			<img class="prod-view" alt="" src="assets/product/productTest.png">
			<h3>하늘색 기본 티</h3>
			<p>30,000원</p>
		</div>

	</div>



</div>

</html>
