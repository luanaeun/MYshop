function showModal() {
	$('#myModal').show();
	
}

//팝업 Close 기능
function close_pop(flag) {
    $('#myModal').hide();
};


//비번 적었는지 확인
function pwWriteCheck() {
	if($("[name=pw]").val() == "") {
		$('[name=warnning-text]').text("비밀번호를 입력하세요.");
		$('[name=warnning-text]').focus();
		return false;
	}
}


