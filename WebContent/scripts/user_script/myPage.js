/* Modal 기능 */

let whatabout = "";

function showModal(what) {
	if(what == 'info-update') {
		$("[name=modal-text]").text("비밀번호 입력");
		$("[name=what]").val("info-update");
	} else if (what == 'pw-update') {
		$("[name=modal-text]").text("비밀번호 입력");
		$("[name=what]").val("pw-update");
	} else if (what == 'delete') {
		$('[name=warnning-text]').text("탈퇴 후 1년동안 재가입이 불가능합니다.");
		$('[name=modal-text]').text("비밀번호 입력");
		$("[name=what]").val("delete");
	} else if (what == 'pw-false'){
		$('[name=warnning-text]').text("비밀번호가 틀립니다.");
	}
	
	$('#myModal').show();
	
}

//팝업 Close 기능
function close_pop(flag) {
    $('#myModal').hide();
    $('[name=warnning-text]').text("");
};


// 비번 적었는지 확인
function pwWriteCheck() {
	if($("[name=pw]").val() == "") {
		$('[name=warnning-text]').text("비밀번호를 입력하세요.");
		$('[name=warnning-text]').focus();
		return false;
	}
}


        


