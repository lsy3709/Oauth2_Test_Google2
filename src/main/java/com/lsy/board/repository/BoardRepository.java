package com.lsy.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lsy.board.model.Board;
import com.lsy.board.model.FileVO;

public interface BoardRepository extends JpaRepository<Board, Long> {


	Page<Board> findByTitleContaining(String word, Pageable pageable);
	Page<Board> findByWriterContaining(String word, Pageable pageable);
	Page<Board> findByContentContaining(String word, Pageable pageable);
	Page<Board> findByTitleContainingOrWriterContainingOrContentContaining(String title, String writer,String content,Pageable pageable);
//	Page<Board> findByTitleOrWriterOrContentContaining(String title, String writer,String content,Pageable pageable);
	
	@Query(value = "SELECT max(bno) FROM board ",nativeQuery = true)
	public Long getMaxBoardId();
	
	@Query(value = "SELECT totalrow FROM board where bno=1 ",nativeQuery = true)
	public Long getTotalrowBoard();
	 

}
