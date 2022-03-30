function loadImage(input) {
	console.log("선택한 파일들", input.files);
	let files = input.files;
	
	if(files.length >5){
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
		}
	}
	
};



function addProdCheck() {
	
}
