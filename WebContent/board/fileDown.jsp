<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 다운로드</title>
</head>
<body>
<%
	System.out.println("파일 다운으로 들어왔어");

	// 전달된 파일의 이름 저장
	String fileName = request.getParameter("file_name");
	System.out.println("파일이름:" + fileName);
	// 서버에 업로드된 폴더
	String savePath = "upload/reference/";
	System.out.println("세이브 패스:" + savePath);
	
	// 서버에 업롣된 파일의 위치. 
	// 인터페이스라서 직접  new로 생성못하고 메서드를 통해 객체 가져오기.
	ServletContext ctx = getServletContext();
	String downloadPath = ctx.getRealPath(savePath);
	System.out.println("downloadPath: " +downloadPath);
	
	// 다운로드할 파일의 전체 경로
	// \이거는 이스케이프 문자라서 실제로 인식하게 하려면 두개를 써야해. 
	String filePath = downloadPath + "\\" + fileName;
	System.out.println("filePath: " + filePath);
	
	// 파일을 저장하는 배열을 만들자. (버퍼)
	byte[] b = new byte[4096];
	FileInputStream fis = new FileInputStream(filePath);
	
	// MIME타입꺼내오기
	String MimeType = getServletContext().getMimeType(filePath);
	
	// MIME타입의 정보가 없는 경우
	if(MimeType == null) {
		MimeType = "application/octec-stream";
		// 실제로 잘 알려지지 않은 이진 파일 => 브라우저가 자동실행하지 않고, 실행 할건지 말건지 불어본다. 
	}
	
	// 응답 정보를 설정(전달받은 MIME파입으로)
	response.setContentType(MimeType);	
	
	/////////////////////3단계
	// 브라우저 종류에 따라 다운로드형태가 다르기때문에 브라우저를 체크해야한다. 
	String agent = request.getHeader("User-Agent");
	
	//explor인지, 그 외의 브라우저인지 체크하기.
	boolean ieBrowser = (agent.indexOf("MSIE") > -1);	// 있으면 인덱스가  있으니까 무조건 -1보다 크게된다. 
	if(ieBrowser){
		// ie는 한글 다운로드시 깨져서 인코딩했고, 공백문자가 +로 표시되서, %20으로 변경하는것. 
		// +로 공백이 되있으면 한글 인코딩이 깨질수 있어 다운로드가 되지 않기 때문이다. 
		fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	} else {
		// 브라우저 다운로드시 한글깨짐 처리.
		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
	}
	
	// 브라우저에서 해석되는 파일도 다운로드 형태로 처리. 
	response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	
	// java.lang.IllegalStateException: 이 응답을 위해 getOutputStream()이 이미 호출되었습니다.
	// JSP -> Servlet으로 변혼시 내장객체인 out이 자동생성되는데, 
	// 우리가 outputStream객체를 새로 생성하니까 출구가 2개가 되버린거야. 그래서 발생하는 예외처리. 
	// 무시하고 써도 되지만, 글씨 보기 싫으면 기존의 out객체를 제거해준다. 
	out.clear();
	out = pageContext.pushBody();
	
	// 다운로드할 파일을 출력. 파일 인풋스트림에 있던 거를 뭐...?
	ServletOutputStream out2 = response.getOutputStream();
	int data = 0;
	while((data = fis.read(b,0,b.length)) != -1){	//파일을 하나씩 불러와서 -1은 이제 더이상 파일이 없을 때. 
		// 결과를 콘솔에 out2를 통해 출력.
		out2.write(b, 0, data);
	}
	// 버퍼의 특징: 버퍼의 박스가 꽉 차야지만 전달한다. 
	// 그럼 꽉 안차면? 안보내서 데이터가 잘리게돼 
			//-> flush는 빈 공간을 공백으로 채워준다. => 버퍼(배열)의 빈 공간을 공백으로 채워서 전달. 
	out2.flush();
	out2.close();

%>
</body>
</html>