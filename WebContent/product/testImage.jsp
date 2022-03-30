<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<style>
html {
    height: 100%;
}

body {
    font-family: sans-serif;
    height: 100%;
    margin: 0;
}

.container {
    display: flex;
    height: 100%;
    flex-direction: column;
}

.image-upload {
    flex: 1;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.button {
    display: flex;
    justify-content: center;
}

label {
    cursor: pointer;
    font-size: 1em;
}

#addImg-button {
    visibility: hidden;
}

.fileContainer {
    display: flex;
    justify-content: center;
    align-items: center;
}

.fileInput {
    display: flex;
    align-items: center;
    border-bottom: solid 2px black;
    width: 60%;
    height: 30px;
}

#fileName {
    margin-left: 5px;
}

.buttonContainer {
    width: 15%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 10px;
    background-color: black;
    color: white;
    border-radius: 30px;
    padding: 10px;
    font-size: 0.8em;

    cursor: pointer;
}

.image-show {
    z-index: -1;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    width: 100px;
    height: 100px;
}

.img {
    position: absolute;
}
</style>

<script>
function showImage() {
	alert("이미지 보여준다~~");
    var newImage = document.getElementById('image-show').lastElementChild;
    
    // 이미지가 화면에 나타난다.
    newImage.style.visibility = "visible";
    
    // document.getElementById('image-upload').style.visibility = 'hidden';

    //document.getElementById('fileName').textContent = null;     //기존 파일 이름 지우기
}


function loadFile(images) {
    var images = images.files[0];
  
    var name = document.getElementById('fileName');
    name.textContent = images.name;

    var newImage = document.createElement("img");
    newImage.setAttribute("class", 'img');

    newImage.src = URL.createObjectURL(images);   

    newImage.style.width = "70%";
    newImage.style.height = "70%";
    //newImage.style.visibility = "hidden";   //버튼을 누르기 전까지는 이미지 숨기기
    newImage.style.objectFit = "contain";

    var container = document.getElementById('image-show');
    container.appendChild(newImage);
    
    //var newImage = document.getElementById('image-show').lastElementChild;
};
</script>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Upload Example</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <div class="image-upload" id="image-upload">

            <form method="post" enctype="multipart/form-data">
                <div class="button">
                    <label for="addImg-button">
                       	 👉 CLICK HERE! 👈
                    </label>
                </div>
                <input type="file" id="addImg-button" name="chooseFile" accept="image/*" onchange="loadFile(this)">
            </form>

            <div class="fileContainer">
                <div class="fileInput">
                    <p>FILE NAME: </p>
                    <p id="fileName"></p>
                </div>
                <div class="buttonContainer">
                    <div class="submitButton" id="submitButton" onclick="showImage()">SUBMIT</div>
                </div>
            </div>
        </div>
        
        <div class="image-show" id="image-show"></div>
    </div>

    <script src="index.js"></script>
</body>
</html>
