package com.lsy.board.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.lsy.board.model.Board;
import com.lsy.board.model.FileVO;
import com.lsy.board.model.Reply;
import com.lsy.board.model.User;

public interface BoardService {
	
	public void insert(Board board,User user);
	public void insertImagExtra(Board board);
	
	public void insertFileVO(FileVO fileVO);
	
	//AWS s3 작업중 , URL 가져오기 메서드
//	public String getPreSignedURL(String uploadFile, String dirName);
	public String getPreSignedURL(String uploadFile);
	
	//AWS s3 작업중, 여러개  삭제 하기
	public void deleteS3Files(Long bno);
	
	//AWS s3 작업중, 한개 삭제 하기
		public void deleteS3File(Long fno);
		
		public FileVO fileOne(Long fno);
	
		//filevo 한개  삭제 
		public void deleteFilevo(Long fno);
		
	//filevo 여러개  삭제 
	public void deleteFilevos(Long bno);
	
	public List<Board> boardList();
	public List<FileVO> fileList(Long bno);
	public FileVO getFile(Long fno);
	
	public Board findById(Long id);
	
	public Board findBytotalrow(Long id);
	
	public void update(Board board);
	public void delete(Long id);

	public Long count();
	
	public void insetReply(Reply reply);
	
	public List<Reply> replyList(Long bno);
	
	public void replyDelete(Long id);

	public Page<Board> findAll(Pageable pageable);
	
	//검색 제목
	public Page<Board> search(String word,Pageable pageable );
	
	//검색 작성자
		public Page<Board> searchWriter(String word,Pageable pageable );
		
		//검색 내용
		public Page<Board> searchContent(String word,Pageable pageable );
		
		 //4. 제목 or 작성자 or 내용으로 검색
		public Page<Board> searchListTitleWriterContent(  String word, Pageable pageable );

}
