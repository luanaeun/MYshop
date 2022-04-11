
function isWrite() {
	if ($("[name=name]").val() == "") {
		$("#id").text("*이름을 입력하세요");
		$("[name=name]").focus();
		return false;
	}
	
	if ($("[name=email]").val() == "") {
		$("#id").text("*이메일을 입력하세요");
		$("[name=email]").focus();
		return false;
	}
}