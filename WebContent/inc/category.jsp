<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<link href="css/inc_css/category.css" rel="stylesheet" type="text/css">

<%
	LinkedHashMap cateList = (LinkedHashMap) request.getAttribute("cateList");
%>

<div class="header_bottom">

	<ul class="cate-list">
		<%
		if(cateList != null) {
			for (Object i: cateList.keySet()) {
				ArrayList detailList = (ArrayList) cateList.get(i);
				if(i.equals("도서·음반·문구")) {
					%><li class="dropdown" style="width: 110px;"><a href=""><%=i %></a><%
				} else {
					%><li class="dropdown"><a href=""><%=i %></a><%
				}
				%>
					<div class="dropdown-box">
				<%
				if (detailList.size() > 0) {
					for(Object j : detailList) {
						%>
							<a href="ProductList.pd"><%=j %></a>
						<% 			
					}
				}
				%>
					</div>
				</li>
				<div style="float:left";>|</div>
			<% 
			}
			%><%
		}
		%>
		
<!-- 		<li class="dropdown"><a href="">식품</a> -->
<!-- 			<div class="dropdown-box"> -->
<!-- 				<a href="ProductList.pd">유제품</a> -->
<!-- 				<a href="ProductList.pd">과일</a> -->
<!-- 			</div>		 -->
<!-- 		</li> -->
		
		
	</ul>
	
</div>



</html>