package com.myshop.product.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myshop.board.db.BoardDAO;
import com.myshop.board.db.NoticeDTO;
import com.myshop.common.Action;
import com.myshop.common.ActionForward;
import com.myshop.product.db.ProductDAO;
import com.myshop.product.db.ProductDTO;
import com.oreilly.servlet.MultipartFilter;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AddProductAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("M : AddProductAction_execute()호출");
		
		
		// 이미지가 들어갈 저장 경로를 설정한다.
		String realPath = req.getRealPath("/productImgs");
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(
				req, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
		);
		
		// 한글처리
		req.setCharacterEncoding("UTF-8");
		
		//전달된 제품정보를 저장
		ProductDTO dto = new ProductDTO();
		HttpSession se = req.getSession();
		dto.setUserid((String)se.getAttribute("user_id"));
		
		dto.setName(multi.getParameter("name"));
		dto.setPrice(Integer.parseInt(multi.getParameter("price")));
		dto.setCategory(multi.getParameter("category"));
		dto.setStock(Integer.parseInt(multi.getParameter("stock")));
		dto.setContent(multi.getParameter("content"));
		dto.setDeliCharge(Integer.parseInt(multi.getParameter("deliveryCharge")));
		dto.setDeliDays(multi.getParameter("deliDays"));
		dto.setHowDeli(multi.getParameter("howDeli"));
		dto.setIp(req.getRemoteAddr());
		
		dto.setSumbnail(multi.getParameter("img01"));

		
		ArrayList fileNames = new ArrayList<>();
		fileNames.add(multi.getParameter("img02"));
		fileNames.add(multi.getParameter("img03"));
		fileNames.add(multi.getParameter("img04"));
		fileNames.add(multi.getParameter("img05"));
		dto.setImages(fileNames);
		
		System.out.println("저장할 것: " + dto.toString());
		
		// 3번째 시도
//		System.out.println("대체 파일이 어떻게 되있길래" + multi.getParameterValues("fileNames")[0]);
//		String[] fileArray = multi.getParameterValues("fileNames");
//		System.out.println("대체 파일이 어떻게 되있길래..?" + Arrays.toString(fileArray));
//		ArrayList<String> fileNames = new ArrayList<>(Arrays.asList(fileArray));
//		dto.setSumbnail(fileNames.get(0));
//		
//		fileNames.remove(0);
//		dto.setImages(fileNames);
//		System.out.println("저장할 것: " + dto.toString());
		
		// 2번째 시도
//		System.out.println(multi.getParameter("fileNames"));
//		String[] fileArray = multi.getParameter("fileNames").split("|");
//		System.out.println("대체 파일이 어떻게 되있길래" + Arrays.toString(fileArray));
//		ArrayList<String> fileNames = new ArrayList<>(Arrays.asList(fileArray));
//		System.out.println("리스트로 바꾼 이후는?" + fileNames);
//		dto.setSumbnail(fileNames.get(0));
//		
//		fileNames.remove(0);
//		dto.setImages(fileNames);
//		System.out.println("최종 저장된 이미지들: " + fileNames);
//		System.out.println("이 결과는?" + multi.getFile("addImg"));
//		System.out.println("이 결과는2?" + multi.getFileNames());
		
//		Enumeration<String> en = multi.getFileNames();
//		
//		System.out.println("en: " + en);
//		Map map = new HashMap();
//		Collections enu = (Collections) multi.getFileNames();
		
		// 첫 번째 시도
		// 음... 파일 여러개 가져오는거 어케하지...?
		// Enumeration en = multi.getFileNames();
		// System.out.println("이넘" + en);
		//
		// ArrayList tempList = new ArrayList<>();
		//
//		 while(en.hasMoreElements()) {
//		 System.out.println("에이"+ (String)en.nextElement());
//		 String a = (String) en.nextElement();
//		 System.out.println(multi.getFilesystemName("addImg"));
//		// System.out.println("제발.." + b);
//		// tempList.add(b);
//		 }


		// 제품 등록 메서드 호출
		ProductDAO dao = new ProductDAO();
		dao.addProduct(dto);

		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./TodayNewProduct.pd");
		forward.setRedirect(true);
		
		System.out.println("M: 글쓰기 완료후 페이지 정보를 Ctl로 리턴");
		
		return forward;

	
	}
	
	

}
