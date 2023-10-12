package com.lsy.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lsy.board.model.Board;
import com.lsy.board.model.FileVO;

public interface FileVORepository extends JpaRepository<FileVO, Long> {

	//샘플
	//@Query("select r from Reply r join fetch r.board where board_id=?1")
//	@Query(value = "SELECT f FROM FileVO f where bno=?1 ")
	public List<FileVO> findByBno(Long bno);
//	public FileVO findByBno(Long bno);
	public FileVO findByFno(Long bno);

}
