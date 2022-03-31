function loadImage(input) {
	console.log("선택한 파일들", input.files);
	let fileNames = [];
	let files = input.files;
	
	if(files.length > 5){
		$("#img-warning").text("* 이미지는 5개까지만 선택할 수 있습니다.");
	} else {
		for(let i=0; i<files.length; i++) {
			var temp = input.files[i];
			console.log("파일이름:", temp.name)
			
			//새로운 이미지 div 추가
		    var newImage = document.createElement("img");
		    newImage.setAttribute("class", 'image-show');
		    console.log("뭐가 셋팅된거지?", newImage);

		    //이미지 source 가져오기
		    newImage.src = URL.createObjectURL(temp);   
		    
		    newImage.style.width = "160px";
		    newImage.style.height = "160px";
		    newImage.style.float = "left";
		    newImage.style.border = "1px solid #BDBDBD";
		    newImage.style.borderRadius = "10px";
		    newImage.style.margin = "0px 5px";
		    newImage.style.objectFit = "contain";

		    //이미지를 image-show div에 추가
		    var container = document.getElementById('images-box');
		    console.log("최종 추가: ", container);
		    container.appendChild(newImage);
		    
		    fileNames.push(temp.name);
		    console.log(fileNames);
		}
	}
//	console.log("최종 파일 이름들:", fileNames);
	$('input[name=fileNames]').val(fileNames);
	console.log("인풋값: ", $('input[name=fileNames]').val());
};



function addProdCheck() {
	let isNmberCheck = /\d/;
	
	if($("[name=addImg]").val() == ""){
		$("#img-warning").text("* 이미지가 최소1 개 이상 있어야 합니다.");
        $("#img-warning").focus();
        return false;
    }
	
	if($("[name=price]").val() == ""){
        $("#info-warning").text(" * 가격을 입력해주세요.");
        $("[name=price]").focus();
        return false;
    }
	if(!isNmberCheck.test($("[name=price]").val())){
		$("#info-warning").text(" * 가격은 숫자만 입력가능합니다.");
        $("[name=price]").focus();
        return false;
	}
	
	if($("[name=stock]").val() == ""){
        $("#info-warning").text(" * 수량을 입력해주세요.");
        $("[name=stock]").focus();
        return false;
    }
	if(!isNmberCheck.test($("[name=stock]").val())){
		$("#info-warning").text(" * 수량은 숫자만 입력가능합니다.");
        $("[name=stock]").focus();
        return false;
	}
	
	if($("[name=deliveryCharge]").val() == ""){
        $("#info-warning").text(" * 배송비를 입력해주세요.");
        $("[name=deliveryCharge]").focus();
        return false;
    }
	if(!isNmberCheck.test($("[name=deliveryCharge]").val())){
		 $("#info-warning").text(" * 배송비는  숫자만 입력가능합니다.");
	        $("[name=deliveryCharge]").focus();
	        return false;
	}
	
	if($("select[name='category']").val() == ""){
        $("#info-warning").text(" * 카테고리를 선택해주세요.");
        $("[name='category']").focus();
        return false;
    }
		
	if($("[name=detailInfo]").val() == ""){
        $("#info-warning").text(" * 상세 정보를 입력해주세요.");
        $("[name=detailInfo]").focus();
        return false;
    }
}
