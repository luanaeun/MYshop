function writeComment(num) {
	// 확인 클릭시 이동
	$('#rSubmit').click(function(){
		$.ajax({
			url:'./RcommentWriteAction.bo',
			data:{
				'pnum': num,
				'rComment': $("[name=rComment]").val()
			},
			success:function(result){
				if(result == 1) {
					console.log("작성완료");
					location.href="./ReviewDetail.bo?num="+num;
				} else {
					alert("작성 실패");
				}
			}
		});
	});		
}