<%@page import="com.myshop.user.db.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
    	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    	<script src="./scripts/jquery-3.6.0.js"></script>
    	<script src="scripts/user_script/signUp.js"></script>

    	<title>로그인 / 회원가입 폼 템플릿</title>
        <link href="css/user_css/signUp.css" rel="stylesheet" type="text/css">
    </head>
    
    
    <body>
    	<%
    		//String idCheckResult = (String)request.getAttribute("idCheckResult");
    	%>
    	<jsp:include page="../inc/header.jsp"></jsp:include>

        <div class="wrap">
            <div class="form-wrap">
            	<h1>회원가입</h1>
                
                <form id="register" action="./SignUpAction.us" onsubmit="return signUpCheckFunc()" class="input-group">
                	
                	<label>아이디</label><span id="id"></span><br>
                    <input type="text" class="input-field" name="id" oninput="changeIDCheck()" placeholder="3~10자" minlength=3 maxlength=10 >   
                    <input type="button" class="check-button" name="idCheckBtn" value="중복 체크" onclick="idCheckFunc()" style="background-color: #FFCBCB"><br>
                    
                    
                    <label>비밀번호</label><span id="pw"></span><br>
                    <input type="password" class="input-field" name="pw" placeholder="대문자+소문자+숫자+특수문자" minlength=8 maxlength=16><br>
                    
                    <label>비밀번호 확인</label><span id="pwCheck"></span><br>
                    <input type="password" class="input-field" name="pwCheck" minlength=8 maxlength=16><br>
                    
                    <label>이름</label><span id="name"></span><br>
                    <input type="text" class="input-field" name="name" minlength=1 maxlength=15><br>
                    
                    <label>생년월일</label><span id="birth"></span><br>
                    <input type="text" class="input-field" name="birth" placeholder="주민번호 앞 6자리"><br>
                    
                    <label>성별</label><span id="gender"></span><br>
                    <input type="radio" class="input-radio" name="gender" value="남">남
                    <input type="radio" class="input-radio" name="gender" value="여">여
                    <input type="radio" class="input-radio" name="gender" value="비공개">선택안함<br>

                    
                    <label>핸드폰 번호</label><span id="phone"></span><br>
                    <input type="text" class="input-field" name="phone" placeholder="-없이 숫자만 입력"><br>
<!--                     <input type="button" class="check-button" name="phoneCheck" value="인증번호받기" class="check-button"><br> -->
                    
                    <label>이메일</label><span id="email"></span><br>
                    <input type="text" class="input-field" name="email"><br>
                    
                    <label>주소</label><span id="addr"></span><br>
                    <input type="text" class="input-addr" id="postnum" name="post" placeholder="우편번호" style="width:150px;">
					<input type="button" onclick="searchPostNum()" value="주소 가져오기" class="check-button" id="bringAddr"><br>
					
					<input type="text" id="roadAddr" placeholder="도로명주소" name="roadAddr" class="input-addr"><br>
					<input type="text" id="detailAddr" placeholder="상세주소" name="detailAddr" class="input-addr"><br>
					<input type="hidden" id="jibunAdd" placeholder="지번주소"><br>
                    
                   	<br><br>
                    <input type="checkbox" class="input-radio" name="infoAgree" value="true"> 이용약관 동의(필수)
                    <span id="infoAgree"></span><br>
             		<input type="checkbox" class="input-radio" name="emailAgree" value="true"> 이메일 수신 동의(선택)<br>
             		
                    <button class="submitbutton">회원가입</button>
                </form>
            </div>
        </div>
 
    </body>
</html> 