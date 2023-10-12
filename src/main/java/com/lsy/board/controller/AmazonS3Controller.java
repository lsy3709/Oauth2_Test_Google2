package com.lsy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lsy.board.service.AwsS3Service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

	@Autowired
	 private AwsS3Service awsS3Service;

	    /**
	     * Amazon S3에 파일 업로드
	     * @return 성공 시 200 Success와 함께 업로드 된 파일의 파일명 리스트 반환
	     */
	    @ApiOperation(value = "Amazon S3에 파일 업로드", notes = "Amazon S3에 파일 업로드 ")
	    @PostMapping("/file")
	    public ResponseEntity<List<String>> uploadFile(@ApiParam(value="파일들(여러 파일 업로드 가능)", required = true) @RequestPart List<MultipartFile> multipartFile) {
//	        return ApiResponse.success(awsS3Service.uploadFile(multipartFile));
	    	return new ResponseEntity<List<String>>(awsS3Service.uploadFile(multipartFile),HttpStatus.OK);
	    }

	    /**
	     * Amazon S3에 업로드 된 파일을 삭제
	     * @return 성공 시 200 Success
	     */
	    @ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제", notes = "Amazon S3에 업로드된 파일 삭제")
	    @DeleteMapping("/file")
	    public ResponseEntity<Void> deleteFile(@ApiParam(value="파일 하나 삭제", required = true) @RequestParam String fileName) {
	        awsS3Service.deleteFile(fileName);
//	        return ApiResponse.success(null);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	}
