<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>MYshop</title>
  <link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
		LinkedHashMap cateList = (LinkedHashMap) request.getAttribute("cateList");
	
	%>
	<jsp:include page="../inc/header.jsp"></jsp:include>
	<jsp:include page="../inc/category.jsp">
		<jsp:param name="cateList" value="<%=cateList%>" />
	</jsp:include>
	
	<jsp:include page="../main/section01.jsp" />
	
		
	<jsp:include page="../product/mainpList.jsp">
		<jsp:param name="sectionName" value="오늘 신상" />
		<jsp:param name="pageLink" value="./TodayNewProduct.pd" />
	</jsp:include>
	
	
	<jsp:include page="../product/mainpList.jsp">
		<jsp:param name="sectionName" value="조회수 높은 상품" />
		<jsp:param name="pageLink" value="./TodayNewProduct.pd" />
	</jsp:include>
	

	
	<jsp:include page="../inc/footer.jsp"></jsp:include>
</body>
</html>