// user/signIn.jsp페이지의 자바스크립트 파일


// 회원가입 유효성 검사 함수.
function signInCheckFunc() {

	// 아이디
	if($("[name=id]").val() == ""){
        $("#id").text("*아이디를 입력하시오");
        $("[name=id]").focus();
        return false;
    }
	
	//비밀번호
	if($("[name=pw]").val() == ""){
        $("#pw").text("*비밀번호를 입력하시오");
        $("[name=pw]").focus();
        return false;
    }
	return true;
}


function findIDandPW(what){
	if(what == "id") {
		location.href="./FindIDPW.us?what=id";
	} else {
		location.href="./FindIDPW.us?what=pw";
	}
}