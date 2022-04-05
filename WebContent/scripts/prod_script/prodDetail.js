function showModal(what) {
	if(what == 'delete') {
		$("[name=modal-text]").text("정말 삭제하시겠습니까?");
	} else {
		$("[name=modal-text]").text("삭제 완료");
	}
		
	$('#myModal').show();
}

//팝업 Close 기능
function close_pop(flag) {
    $('#myModal').hide();
    $('[name=warnning-text]').text("");
};


function deleteScript(num) {
	// 확인 클릭시 이동
	$('#modal-submit').click(function(){
		if($("[name=modal-text]").val() == "삭제 완료") {
			location.href="./TodayNewProduct.pd";
		} else {
			$.ajax({
				url:'./DeleteroductAction.pd?num='+num,
				data:{
					'id': num
				},
				success:function(result){
					console.log("삭제 결과", result);
					if(result == 1) {
						$("[name=modal-text]").css("color", "blue");
						$("[name=modal-text]").text("삭제 완료");
						$('#modal-submit').click(function(){
							location.href="./TodayNewProduct.pd";
						});
					} else {
						$("[name=modal-text]").text("삭제 실패. 조금 후에 다시 해주세요.");
					}
					$('#myModal').show();
				}
			});
		}
			
	});
}
