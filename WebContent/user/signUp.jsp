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
                    <p onclick="infoAgreeCheck()" class="infoAgree-p">
                    	<input type="checkbox" class="input-radio" name="infoAgree" value="true"> 이용약관 동의(필수)
                    </p>
                    <span id="infoAgree"></span><br>
             		<input type="checkbox" class="input-radio" name="emailAgree" value="true"> 이메일 수신 동의(선택)<br>
             		
                    <button class="submitbutton">회원가입</button>
                </form>
            </div>
        </div>
        
        
        <div id="myModal" class="modal">
      	  <div class="modal-content">
         		<p class="x-box" onClick="close_pop();"><span>X</span></p>
				<h4>이용 약관</h4>
		 		<p style="font-size: 11pt;" name="warnning-text">
		 			MYshop서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다.
		 			서비스를 이용하시거나 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 
		 			잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.<br><br>
				  <p style="text-align: left; font-size: 11pt;">
		 			이용자에게 개인정보 보관기간에 대해 회원가입 시 동의를 얻은 경우는 아래와 같습니다.<br>
					- 부정 가입 및 이용 방지<br>
					부정 이용자의 가입인증 휴대전화번호 또는 DI (만14세 미만의 경우 법정대리인DI) : 탈퇴일로부터 6개월 보관<br>
					탈퇴한 이용자의 휴대전화번호(복호화가 불가능한 일방향 암호화(해시처리)) : 탈퇴일로부터 6개월 보관<br><br>

					휴대전화번호:등록/수정/삭제 요청 시로부터 최대1년<br>
					- 전자상거래 등에서 소비자 보호에 관한 법률<br>
					계약 또는 청약철회 등에 관한 기록: 5년 보관<br>
					대금결제 및 재화 등의 공급에 관한 기록: 5년 보관<br>
					소비자의 불만 또는 분쟁처리에 관한 기록: 3년 보관<br>
					- 전자문서 및 전자거래 기본법: 공인전자주소를 통한 전자문서 유통에 관한 기록 : 10년 보관<br>
					- 인증서와 인증 업무에 관한 기록: 인증서 효력 상실일로부터 10년 보관<br>
					- 통신비밀보호법: 로그인 기록: 3개월<br>

					MYshop ‘개인정보 유효기간제’에 따라 1년간 서비스를 이용하지 않은 회원의 개인정보를 별도로 분리 보관하여 관리하고 있습니다.<br>

		 		</p><br>
		 		
		 		<h4>개인정보 이용 동의</h4>
		 		<p style="font-size: 11pt;" name="warnning-text">
		 			개인정보보호법에 따라 네이버에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 
		 			개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.<br>
		 			  
		 		</p><br>
         		
            
         		<input type="submit" class="modal-submit" name="modal-submit" onclick="infoAgreeYes()" value="확인">
         	
      	  </div>
    	</div>
 
    </body>
</html> 