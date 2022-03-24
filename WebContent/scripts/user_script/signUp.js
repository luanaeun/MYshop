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
}																																// var
																																																											// buf
					

// 회원가입 유효성 검사 함수.

// 아이디: 영문 대.소문자, 숫자 _,-만 입력 가능하고 5에서 20자리를 입력했는지 체크
// var useridCheck = RegExp(/^[A-Za-z0-9_\-]{3,10}$/);
var useridCheck = RegExp(/^[가-힣a-zA-Z0-9]{3,10}$/);	//영어, 한글, 숫자만 사용.

// 이름: 2~15글자의 한글만 입력하였는지 검사
var nameCheck = RegExp(/^[가-힣]{2,15}$/);

//패스워드: 영문 대문자와 소문자, 숫자, 특수문자를 하나 이상 포함하여 8~16자가 되는지
var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);																																																											// //주민등록번호
																																																											// 배열
// 이메일
var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);

// 핸드폰
var phonNumberCheck = RegExp(/^01[0179][0-9]{7,8}$/);

$('#id').blur(function(){
    console.log($('#id'),val());

    if($('#id').val().length < 3){
        $('#helper1').text("3자 이상 입력해주세요.");

    } if($('#username').val().length > 11) {
        $('#helper1').text("10자 이하로 입력해주세요.");

    }

});																																																											// if($("#id").val()
																																																											// ==
																																																											// ""){
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

