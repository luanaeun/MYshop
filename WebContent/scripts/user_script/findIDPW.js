
function isWrite(what) {
	console.log("왓이 전달되니?", what);
	if ($("[name=name]").val() == "") {
		$("#warn-text").text("*이름을 입력하세요");
		$("[name=name]").focus();
		return false;
	}
	if ($("[name=id]").val() == "") {
		$("#warn-text").text("*아이디를 입력하세요");
		$("[name=name]").focus();
		return false;
	}
	
	if ($("[name=email]").val() == "") {
		$("#warn-text").text("*이메일을 입력하세요");
		$("[name=email]").focus();
		return false;
	}
	
	$.ajax({
		async: true,
		type : 'POST',
		data: {
			'what' : what,
			'name' : $("[name=name]").val(),
			'id' : $("[name=id]").val(),
			'email' : $("[name=email]").val()
		},
		url : "./FindIDPWAction.us",
        success : function(findResult) {
        	console.log("아이디 조회 결과: ", findResult);
            if (findResult == 1) {
            	$("#warn-text").text("*이메일을 입력하세요");
//            	$("[name=name]").detach();
//            	$("[name=id]").detach();
//            	$("[name=email]").detach();
//            	$("[name=email-text]").detach();
//            	$(".findIDPW-container").append("<h2>아이디를 이메일로 보냈습니다.</h2s>");
//            	$("#submit").val("확인");
//            	$("#submit").click(function(){
//            		location.href="./SignIn.us";
//            	});
            	  
            } else if(findResult == 0){
            	$("#result-text").text("*메일을 보낼 수 없습니다. 잠시후에 다시 시도.");
            } else {
            	$("#result-text").text("비회원이거나 잘못입력되었습니다. ");
            }
        },
        error : function(error) {
            alert("error : " + error);
        }
		
	});
	
}