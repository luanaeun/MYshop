<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script src="scripts/user_script/signUp.js"></script>

 
        <title>로그인 / 회원가입 폼 템플릿</title>
        <link href="css/user_css/signUp.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<jsp:include page="../inc/header.jsp"></jsp:include>

        <div class="wrap">
            <div class="form-wrap">
            	<h1 style="text-align: center; margin-top:30px">MYshop</h1>
                
                <form id="register" action="./SignUpAction.us" class="input-group">
                	
                	<label>아이디</label><br>
                    <input type="text" class="input-field" name="id" placeholder="3~10자" minlength=3 maxlength=7 id="id" required>   
                    <input type="button" class="check-button" name="idCheck" value="중복 체크"><br>
                    
                    <label>비밀번호</label><br>
                    <input type="password" class="input-field" name="pw" placeholder="대문자+소문자+숫자+특수문자" minlength=8 maxlength=16 required><br>
                    
                    <label>비밀번호 확인</label><br>
                    <input type="password" class="input-field" name="pwCheck" minlength=8 maxlength=16 required><br>
                    
                    <label>이름</label><br>
                    <input type="text" class="input-field" name="name" required><br>
                    
                    <label>생년월일</label><br>
                    <input type="text" class="input-field" name="birth" placeholder="주민번호 앞 6자리" required><br>
                    
                    <label>성별</label><br>
                    <input type="radio" class="input-radio" name="gender" value="남" required>남
                    <input type="radio" class="input-radio" name="gender" value="여" required>여
                    <input type="radio" class="input-radio" name="gender" value="비공개" required>선택안함<br>

                    
                    <label>핸드폰 번호</label><br>
                    <input type="text" class="input-field" name="phone" placeholder="-없이 숫자만 입력" required>
                    <input type="button" class="phone-check" name="phoneCheck" value="인증번호받기" class="check-button"><br>
                    
                    <label>이메일</label><br>
                    <input type="text" class="input-field" name="email" required><br>
                    
                    <label>주소</label><br>
                    <input type="text" class="input-addr" id="postnum" name="post" placeholder="우편번호" style="width:150px;" required>
					<input type="button" onclick="searchPostNum()" value="주소 가져오기" class="check-button"><br>
					
					<input type="text" id="roadAddr" placeholder="도로명주소" name="roadAddr" class="input-addr"><br>
					<input type="text" id="detailAddr" placeholder="상세주소" name="detailAddr" class="input-addr"><br>
					<input type="hidden" id="jibunAdd" placeholder="지번주소"><br>
                    
                   	<br><br>
                    <input type="checkbox" class="input-radio" name="agree" value="true" required> 이용약관 동의(필수)<br>
             		<input type="checkbox" class="input-radio" name="emailAgree" value="true"> 이메일 수신 동의(선택)<br>
             		
                    <button class="submitbutton">회원가입</button>
                </form>
            </div>
        </div>
 
    </body>
</html> 