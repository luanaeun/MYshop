// user/signUp.jsp페이지의 자바스크립트 파일

// 우편번호 가져오는 함수.
function searchPostNum() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', '
						+ data.buildingName : data.buildingName);
			}
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('postnum').value = data.zonecode;
			document.getElementById("roadAddr").value = roadAddr;
			document.getElementById("jibunAdd").value = data.jibunAddress;
		}
	}).open();
}	


// 아이디 중복 체크 버튼 함수
function idCheckFunc() {
	if($("[name=id]").val() == ""){
        $("#id").text("*아이디를 입력해주세요");
        $("[name=id]").focus();
        return false;
	} else {
		var url = "./SignUpIdCheck.us?id="+$("[name=id]").val();
		// history.pushState(null, null, "./SignUpIdCheck.us?id="+$("[name=id]").val());
		window.location.href="./SignUpIdCheck.us?id="+$("[name=id]").val();
		// location.href="./SignUpIdCheck.us?id="+$("[name=id]").val();
	}
}
																																																											// buf
					

// 회원가입 유효성 검사 함수.

function signUpCheckFunc() {
	var useridCheck = RegExp(/^[가-힣a-zA-Z0-9]{3,10}$/);
	var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);																																																											// //주민등록번호
	var nameCheck = RegExp(/^[가-힣a-zA-Z]{2,15}$/);
	var birthCheck = RegExp(/^[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|[3][01])/);
	var phonNumberCheck = RegExp(/^01[0179][0-9]{7,8}$/);
	var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	
	// 아이디
	if($("[name=id]").val() == ""){
        $("#id").text("*아이디를 입력해주세요");
        $("[name=id]").focus();
        return false;
    }
	if(!useridCheck.test($("[name=id]").val())){
		$("#id").text("*아이디는 한글,영문, 숫자만 가능합니다.");
        // $("[name=id]").val("");
        $("[name=id]").focus();
        return false;
    }
	
	// 아이디 중복 체크 여부
	if($("[name=idCheckHidden]").val() != "ok") {
		$("#id").text("*아이디 중복체크를 해주세요.");
		$("[name=id]").focus();
		return false;
	}
	
	
	//비밀번호
	if (!passwdCheck.test($("[name=pw]").val())) {
		$("#pw").text("*대소문자,특수문자 포함 8~16자로 입력.");
		$("[name=pw]").val("");
		$("[name=pw]").focus();
		return false;
	}
	
	// 비번 확인
	if ($("[name=pw]").val() !== $("[name=pwCheck]").val()) {
		$("#pwCheck").text("*비밀번호가 다릅니다.");
		$("[name=pwCheck]").val("");
		$("[name=pwCheck]").focus();
		return false;
	}
	
	// 이름
	if(!nameCheck.test($("[name=name]").val())){
		$("#name").text("*이름은 한글,영문만 가능합니다.");
        $("[name=name]").focus();
        return false;
    }
	
	// 생년월일
	if(!birthCheck.test($("[name=birth]").val())){
		$("#birth").text("*올바른 주민번호 앞자리 입력.");
        $("[name=birth]").focus();
        return false;
    }
	
	
	// 성별
	if($("input:radio[name=gender]:checked").length == 0){
        $("#gender").text("*성별을 선택해주세요.");
        $("[name=gender]").focus();
        return false;
    }
	
	// 핸드폰 번호
	if(!phonNumberCheck.test($("[name=phone]").val())){
		$("#phone").text("*정확하게 입력되었는지 확인해주세요.");
        $("[name=phone]").focus();
        return false;
    }
	
	// 이메일
	if(!emailCheck.test($("[name=email]").val())){
		$("#email").text("*이메일 형식이 다릅니다.");
        $("[name=email]").focus();
        return false;
    }
	
	// 주소
	if($("[name=postnum]").val() == "" || $("[name=roadAddr]").val() == ""){
        $("#addr").text("*주소를 입력해주세요.");
        $("#bringAddr").focus();
        return false;
    }
	
	// 개인정보 이용약관
	if($("[name=infoAgree]").is(":checked") == false){
        $("#infoAgree").text("*이용약관에 동의해주세요.");
        $("[name=infoAgree]").focus();
        return false;
    }
	
	return true;
																																																											// ==
}																																																										// ""){
																																																											// alert("아이디
																																																											// 입력바람");
																																																											// $("#id").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //아이디
																																																											// 유효성검사
																																																											// if(!getCheck.test($("#id").val())){
																																																											// alert("형식에
																																																											// 맞게
																																																											// 입력해주세요");
																																																											// $("#id").val("");
																																																											// $("#id").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //비밀번호
																																																											// 공백
																																																											// 확인
																																																											// if($("#password").val()
																																																											// ==
																																																											// ""){
																																																											// alert("패스워드
																																																											// 입력바람");
																																																											// $("#password").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //아이디
																																																											// 비밀번호
																																																											// 같음
																																																											// 확인
																																																											// if($("#id").val()
																																																											// ==
																																																											// $("#password").val()){
																																																											// alert("아이디와
																																																											// 비밀번호가
																																																											// 같습니다");
																																																											// $("#password").val("");
																																																											// $("#password").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //비밀번호
																																																											// 유효성검사
																																																											// if(!getCheck.test($("#password").val())){
																																																											// alert("형식에
																																																											// 맞게
																																																											// 입력해주세요");
																																																											// $("#password").val("");
																																																											// $("#password").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //비밀번호
																																																											// 확인란
																																																											// 공백
																																																											// 확인
																																																											// if($("#password_check").val()
																																																											// ==
																																																											// ""){
																																																											// alert("패스워드
																																																											// 확인란을
																																																											// 입력해주세요");
																																																											// $("#password_check").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //비밀번호
																																																											// 서로확인
																																																											// if($("#password").val()
																																																											// !=
																																																											// $("#password_check").val()){
																																																											// alert("비밀번호가
																																																											// 상이합니다");
																																																											// $("#password").val("");
																																																											// $("#password_check").val("");
																																																											// $("#password").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //이메일
																																																											// 공백
																																																											// 확인
																																																											// if($("#mail").val()
																																																											// ==
																																																											// ""){
																																																											// alert("이메일을
																																																											// 입력해주세요");
																																																											// $("#mail").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //이메일
																																																											// 유효성
																																																											// 검사
																																																											// if(!getMail.test($("#mail").val())){
																																																											// alert("이메일형식에
																																																											// 맞게
																																																											// 입력해주세요")
																																																											// $("#mail").val("");
																																																											// $("#mail").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //이름
																																																											// 공백
																																																											// 검사
																																																											// if($("#name").val()
																																																											// ==
																																																											// ""){
																																																											// alert("이름을
																																																											// 입력해주세요");
																																																											// $("#name").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //이름
																																																											// 유효성
																																																											// 검사
																																																											// if(!getCheck.test($("#name").val())){
																																																											// alert("이름형식에
																																																											// 맞게
																																																											// 입력해주세요")
																																																											// $("#name").val("");
																																																											// $("#name").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// if(($("#id_num").val()
																																																											// ==
																																																											// "")
																																																											// ||
																																																											// ($("#id_num_back").val()
																																																											// ==
																																																											// "")){
																																																											// alert("주민등록번호를
																																																											// 입력해주세요");
																																																											// $("#id_num").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// if(check_jumin()
																																																											// ==false){
																																																											// return
																																																											// false;
																																																											// }
																																																											// //취미
																																																											// 유효성
																																																											// 검사
																																																											// for(var
																																																											// i=0;i<$('[name="hobby[]"]').length;i++){
																																																											// if($('input:checkbox[name="hobby[]"]').eq(i).is(":checked")
																																																											// ==
																																																											// true)
																																																											// {
																																																											// hobbyCheck
																																																											// =
																																																											// true;
																																																											// break;
																																																											// } }
																																																											// if(!hobbyCheck){
																																																											// alert("하나이상
																																																											// 관심분야를
																																																											// 체크해
																																																											// 주세요");
																																																											// return
																																																											// false;
																																																											// }
																																																											// //자기소개란
																																																											// 공백
																																																											// 검사
																																																											// if($("#intro").val()
																																																											// ==
																																																											// ""){
																																																											// alert("자기소개를
																																																											// 입력해주세요")
																																																											// $("#intro").val("");
																																																											// $("#intro").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// return
																																																											// true;
																																																											// }
																																																											// function
																																																											// check_jumin(){
																																																											// var
																																																											// jumins3
																																																											// =
																																																											// $("#id_num").val()
																																																											// +
																																																											// $("#id_num_back").val();
																																																											// //주민등록번호
																																																											// 생년월일
																																																											// 전달
																																																											// var
																																																											// fmt
																																																											// =
																																																											// RegExp(/^\d{6}[1234]\d{6}$/)
																																																											// //포멧
																																																											// 설정
																																																											// var
																																																											// buf
																																																											// =
																																																											// new
																																																											// Array(13);
																																																											// //주민번호
																																																											// 유효성
																																																											// 검사
																																																											// if
																																																											// (!fmt.test(jumins3))
																																																											// {
																																																											// alert("주민등록번호
																																																											// 형식에
																																																											// 맞게
																																																											// 입력해주세요");
																																																											// $("#id_num").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// //주민번호
																																																											// 존재
																																																											// 검사
																																																											// for
																																																											// (var
																																																											// i =
																																																											// 0; i
																																																											// <
																																																											// buf.length;
																																																											// i++){
																																																											// buf[i]
																																																											// =
																																																											// parseInt(jumins3.charAt(i));
																																																											// }
																																																											// var
																																																											// multipliers
																																																											// =
																																																											// [2,3,4,5,6,7,8,9,2,3,4,5];//
																																																											// 밑에
																																																											// 더해주는
																																																											// 12자리
																																																											// 숫자들
																																																											// var
																																																											// sum
																																																											// = 0;
																																																											// for
																																																											// (var
																																																											// i =
																																																											// 0; i
																																																											// <
																																																											// 12;
																																																											// i++){
																																																											// sum
																																																											// +=
																																																											// (buf[i]
																																																											// *=
																																																											// multipliers[i]);//
																																																											// 배열끼리12번
																																																											// 돌면서
																																																											// } if
																																																											// ((11
																																																											// -
																																																											// (sum
																																																											// %
																																																											// 11))
																																																											// % 10
																																																											// !=
																																																											// buf[12])
																																																											// {
																																																											// alert("잘못된
																																																											// 주민등록번호
																																																											// 입니다.");
																																																											// $("#id_num").focus();
																																																											// return
																																																											// false;
																																																											// }
																																																											// var
																																																											// birthYear
																																																											// =
																																																											// (jumins3.charAt(6)
																																																											// <=
																																																											// "2")
																																																											// ?
																																																											// "19"
																																																											// :
																																																											// "20";
																																																											// birthYear
																																																											// +=
																																																											// $("#id_num").val().substr(0,
																																																											// 2);
																																																											// var
																																																											// birthMonth
																																																											// =
																																																											// $("#id_num").val().substr(2,
																																																											// 2);
																																																											// var
																																																											// birthDate
																																																											// =
																																																											// $("#id_num").val().substr(4,
																																																											// 2);
																																																											// var
																																																											// birth
																																																											// =
																																																											// new
																																																											// Date(birthYear,
																																																											// birthMonth,
																																																											// birthDate);
																																																											// $("#year").val(birthYear);
																																																											// $("#month").val(birthMonth);
																																																											// $("#date").val(birthDate);
																																																											// }
																																																											// </script>

