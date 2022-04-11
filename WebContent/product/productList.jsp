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
	ArrayList productList = (ArrayList) request.getAttribute("productList");

	String cate = request.getParameter("cate");
	int productCount = (int) request.getAttribute("productCount");
	
	String pageNum = (String) request.getAttribute("pageNum");
	int pageCount = (int) request.getAttribute("pageCount");
	int pageBlock = (int) request.getAttribute("pageBlock");
	int startPage = (int) request.getAttribute("startPage");
	int endPage = (int) request.getAttribute("endPage");
%>
  <jsp:include page="../inc/header.jsp"></jsp:include>
  <jsp:include page="../inc/category.jsp"></jsp:include>
  

  <div class="productList-container">

  
        <h1><%=cate %></h1>
        <div class="count-text">총 <%=productCount %>개</div>

		<%
		if(productList != null) {
			for(int i=0; i<productList.size(); i++) { 
				ProductDTO dto = (ProductDTO) productList.get(i);
			
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		%>
		  <a href = "./ProductDetail.pd?num=<%=dto.getNum()%>">
			<div class="clothes-box">
            	<img src="./upload/productImg/<%=dto.getSumbnail() %>">
            	<p><%=dto.getName()%></p>
            	
				<div class="box-text">
					<p><%=dto.getPrice()%></p>
					
					<img class="wish-icon" src="assets/wish-icon.png" onclick="location.href='./AddWishAction.pd'">
					
				</div>

			</div>
		  </a>
		<%
			}
		} else {
			%><h1 style="color: #6799FF;">상품이 없습니다.</h1><%
		}
		%>

        
    <div class="page_control">
			<%
					// 이전을 누르면 이전 블럭의 제일 첫 페이지로 이동!
				if (startPage > pageBlock) {
			%><a href="./ProductList.pd?pageNum=<%=startPage - pageBlock%>">[이전]</a>
			<%
				}
			%>

			<%
				for (int i = startPage; i <= endPage; i++) {
			%><a href="./ProductList.pd?pageNum=<%=i%>" ><%=i%></a>
			<%
				}
			%>

			<%
				if (endPage < pageCount) {
			%><a href="./ProductList.pd?pageNum=<%=startPage + pageBlock%>">[Next]</a>
			<%
			}
			%>	

		</div>
  </div>
	

  <jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>