//package com.lsy.board.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import lombok.extern.java.Log;
//
//@Log
//@Controller
//@RequestMapping("/files/*")
//public class FileUploadController {
//
//	@GetMapping("uploadForm")
//	public void fileUploadForm() {
//
//	}
//
//	//변수명 uploads는 input(file)에 적은대로
//	@PostMapping("uploadFormAction")
//	public String uploadFormAction(MultipartFile[] uploads, HttpSession session, Model model) {
//
//		String uploadFolder=session.getServletContext().getRealPath("/resources/upload");
//		String names="";
//		String today=new SimpleDateFormat("yyMMdd").format(new Date());
//		// 파일명: /resources/upload + / + 오늘날짜
//		String saveFolder=uploadFolder+File.separator+today;
//		File folder = new File(saveFolder);
//		if(!folder.exists()) {
//			folder.mkdirs();
//		}
////		File destDir = new File(saveFolder);
//		List<String> mfile=new ArrayList<String>();
//		log.info(saveFolder);
//
//		for(MultipartFile multipartFile:uploads) {
//			String orifile = multipartFile.getOriginalFilename();
//			//파일이름 중복 피하기 : 랜덤 16진수 숫자 더해주기
//			UUID uuid= UUID.randomUUID();
//			String uploadfileName=uuid.toString()+"_"+orifile;
//			log.info("===============================");
//			log.info("origin name: "+multipartFile.getOriginalFilename());
//			log.info("uploadfile name: "+uploadfileName);
//			//파일객체생성(경로, 파일)
//
//			File saveFile = new File(saveFolder,uploadfileName);
//
//
//			try {
//				//업로드한 파일을 saveFolder경로에 uploadfileName이름으로 전송
//				multipartFile.transferTo(saveFile);
//				mfile.add(orifile);
//			}catch(IllegalStateException |IOException e){
//				e.printStackTrace();
//			}
//		}
//		model.addAttribute("uploadFiles", mfile);
//
//		return "files/fileResult";
//	}
//}
