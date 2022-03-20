<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>로그인 / 회원가입 폼 템플릿</title>
        <link href="css/user_css/signUp.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    	<jsp:include page="../inc/header.jsp"></jsp:include>

        <div class="wrap">
            <div class="form-wrap">
            	<h1 style="text-align: center; margin-top:30px">MYshop</h1>
                
                <form id="register" action="./SignUpAction.us" class="input-group">
                	
                	<label>아이디</label>
                    <input type="text" class="input-field" name="id" placeholder="최소3자 이상" minlength=3 maxlength=8 required>
                    
                    <label>비밀번호</label>
                    <input type="password" class="input-field" name="pw" minlength=8 maxlength=16 required>
                    
                    <label>비밀번호 확인</label>
                    <input type="password" class="input-field" name="checkPW" minlength=8 maxlength=16 required><br>
                    
                    <label>이름</label>
                    <input type="text" class="input-field" name="name" required><br>
                    
                    <label>생년월일</label>
                    <input type="text" class="input-field" name="birth" placeholder="주민번호 앞 6자리" required><br>
                    
                    <label>성별</label><br><br><br>
                    <input type="radio" class="input-radio" name="gender" required>남
                    <input type="radio" class="input-radio" name="gender" required>여
                    <input type="radio" class="input-radio" name="gender" required>선택안함<br>

                    
                    <label>핸드폰 번호</label>
                    <input type="text" class="input-field" name="phone" placeholder="-없이 숫자만 입력" required><br>
                    
                    <label>이메일</label>
                    <input type="text" class="input-field" name="email" required><br>
                    
                   	<br><br>
                    <input type="checkbox" class="input-radio" name="agree" required>이용약관 동의
             
                    <button class="submit">회원가입</button>
                </form>
            </div>
        </div>
 
    </body>
</html> 