<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.myshop.product.db.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/product_css/productList.css" rel="stylesheet" type="text/css">

<title>MYshop</title>
</head>
<body>
<%
	ArrayList productList = (ArrayList) request.getAttribute("prodList");
%>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  

  <div class="productList-container">
  <div class="right-bar">
  
        <h2 class="right-title">오른쪽 제목</h2>

		<%
		if(productList != null) {
			for(int i=0; i<productList.size(); i++) { 
				ProductDTO dto = (ProductDTO) productList.get(i);
			
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		%>
		  <a href = "./ProductDetail.pd?num=<%=dto.getNum()%>">
			<div class="clothes-box">
            	<img src="./productImgs/<%=dto.getSumbnail() %>">
            	<p><%=dto.getName()%></p>
            	
				<div class="box-text">
					<p><%=dto.getPrice()%></p>
					
					<img class="wish-icon" src="assets/wish-icon.png" onclick="location.href='./AddWishAction.pd'">
					
				</div>

			</div>
		  </a>
			<%
				}
				}
			%>

        <div class="clothes-box">
            <div class="image">
                이미지
            </div>
            <div class="box-text">
                <span>하늘색 니트티</span>
                <p>15,000</p>
            </div>
            <div style="float:left"><p class="like-icon">♡</p></div>
        </div>

        <div class="clothes-box">
            <div class="image">이미지</div>
            <div class="box-text">
                <span>하늘색 니트티</span>
                <p>15,000</p>
            </div>
            <div style="float:left"><p class="like-icon">♡</p></div>
        </div>
        
        <div class="clothes-box">
            <div class="image">이미지</div>
            <div class="box-text">
                <span>하늘색 니트티</span>
                <p>15,000</p>
            </div>
            <div style="float:left"><p class="like-icon">♡</p></div>
        </div>
        
        <div class="clothes-box">
            <div class="image">이미지</div>
            <div class="box-text">
                <span>하늘색 니트티</span>
                <p>15,000</p>
            </div>
            <div style="float:left"><p class="like-icon">♡</p></div>
        </div>

    </div>
  </div>
	


  
   

  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>