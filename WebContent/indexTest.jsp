<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/inc_top.css" rel="stylesheet" type="text/css">

  <style>
  	*{
  		margin: 0px;
  		padding: 0px;
  	}
  </style>
</head>

<body>
	<header>
		<jsp:include page="./inc/top.jsp" />
		<jsp:include page="./inc/topMiddle.jsp" />
		<jsp:include page="./inc/topBottom.jsp" />
	</header>
	
	<section id="slider"><!--slider-->
		<jsp:include page="./main/section01.jsp" />
	</section><!--/slider-->
	
	
	<jsp:include page="./product/productList.jsp"></jsp:include>
	
	
	
	
	<jsp:include page="./inc/footer.jsp" />

</body>
</html>