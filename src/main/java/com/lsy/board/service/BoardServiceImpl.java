package com.lsy.board.service;


import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.lsy.board.model.Board;
import com.lsy.board.model.FileVO;
import com.lsy.board.model.Reply;
import com.lsy.board.model.User;
import com.lsy.board.repository.BoardRepository;
import com.lsy.board.repository.FileVORepository;
import com.lsy.board.repository.ReplyRepository;

import lombok.extern.java.Log;

@Log
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private FileVORepository fileVORepository;
	
	//AWS s3 작업중
		@Autowired
		 private AmazonS3Client amazonS3Client;
		
		  @Value("${cloud.aws.s3.bucket}")
		    private String bucket;
		  
		  @Autowired
		    private AmazonS3 amazonS3;
	
	@PersistenceContext
	EntityManager em;
	
	//filevo 여러개  삭제 
	@Transactional
	@Override
	public void deleteFilevos(Long bno)   {
		List<FileVO> fileVoList = fileList(bno);
	    for(FileVO fileVO : fileVoList) {
	    	fileVORepository.deleteById(fileVO.getFno());
//	        Optional<FileVO> selected_fileVO = fileVORepository.findById(fileVO.getFno());
//	        if(selected_fileVO.isPresent()) {
//	        	fileVORepository.delete(selected_fileVO.get());
//	        }
	    }
	}
	
	//filevo 한개  삭제 
	@Transactional
	@Override
	public void deleteFilevo(Long fno)   {
		FileVO fileImage = fileOne(fno);
	    
	    	fileVORepository.deleteById(fileImage.getFno());

	}
	
////AWS s3 작업중, 한개 삭제 하기	
	@Transactional
	@Override
	public void deleteS3File(Long fno) {
		FileVO fileImage = fileOne(fno);
		
			String fileName = fileImage.getSavefilename();
			amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));	
		}
	
////AWS s3 작업중, 여러개 삭제 하기	
	@Transactional
	@Override
	public void deleteS3Files(Long bno) {
		List<FileVO> filelist = fileList(bno);
		for(FileVO fileVO : filelist) {
			String fileName = fileVO.getSavefilename();
			amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));	
		}
        
    }
	

	@Override
	public void insertImagExtra(Board board) {
//		Long bno = board.getId();
		for(FileVO fileVO:board.getFiles()) {
//			fileVO.setBno(bno);
			fileVORepository.save(fileVO);
		}
		
	}

    


	@Transactional
	@Override
	public void insert(Board board, User user) {
		// TODO Auto-generated method stub
		Board board2 = findBytotalrow(1L);
		Long bno = board2.getTotalrow();
		
		for(FileVO fileVO:board.getFiles()) {
			fileVO.setBno(bno);
			fileVORepository.save(fileVO);
		}
		boardRepository.save(board);
	}
	
	
	@Transactional
	//aws 작업중 해당 URL 가져오기.
	@Override
//	public String getPreSignedURL(File uploadFile, String dirName){
	public String getPreSignedURL(String uploadFile){
		
        String preSignedURL = "";
//        String fileName = dirName + "/" + uploadFile.getName();
//        String fileName = uploadFile.getName();
        String fileName = uploadFile;

        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60*24;
        expiration.setTime(expTimeMillis);
        
        log.info(expiration.toString());
        
        try {

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucket, fileName)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
            preSignedURL = url.toString();
            log.info("Pre-Signed URL : " + url.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return preSignedURL;
    }

	@Transactional
	@Override
	public List<Board> boardList() {
		// TODO Auto-generated method stub
		return boardRepository.findAll();
	}
	
	@Transactional
	@Override
	public List<Reply> replyList(Long bno) {
		// TODO Auto-generated method stub
		
		return replyRepository.replyList(bno);
	}
	
	@Transactional
//상세화면 파일 보여주기 작업중
	@Override
	public List<FileVO> fileList(Long bno) {
		// TODO Auto-generated method stub
		return fileVORepository.findByBno(bno);
	}
	
	@Transactional
	//파일 삭제시 한개 FileVO 로 받기
		@Override
		public FileVO fileOne(Long fno) {
			// TODO Auto-generated method stub
			return fileVORepository.findByFno(fno);
		}
	
	@Transactional
//다운로드 작업중
	@Override
	public FileVO getFile(Long fno) {
		// TODO Auto-generated method stub
		return fileVORepository.findByFno(fno);
	}

	@Transactional
	@Override
	public Page<Board> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return boardRepository.findAll(pageable);
	}
	
	//검색 :제목 
	@Transactional
	@Override
	public Page<Board> search( String word, Pageable pageable) {
		Page<Board>  boardEntities   = boardRepository.findByTitleContaining(word, pageable);
//			if(boardEntities.isEmpty()) {
////				return boardRepository.findAllByUseYn(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "boardIdx")), "Y");
//				
//				
//			}
		return boardEntities;
	}
	
	//검색 :작성자
		@Transactional
		@Override
		public Page<Board> searchWriter( String word, Pageable pageable) {
		
			return boardRepository.findByWriterContaining(word, pageable);
		}
		
		//검색 :내용
				@Transactional
				@Override
				public Page<Board> searchContent( String word, Pageable pageable) {
				
					return boardRepository.findByContentContaining(word, pageable);
				}
		
		 //3. 제목 or 작성자 or 내용으로 검색
		@Transactional
		@Override
		public Page<Board> searchListTitleWriterContent( String word, Pageable pageable) {
		String title = word;
		String writer = word;
		String content = word;
			return boardRepository.findByTitleContainingOrWriterContainingOrContentContaining(title, writer, content, pageable);
//			return boardRepository.findByTitleOrWriterOrContent(title, writer, content, pageable);
		}

	@Transactional
	@Override
	public Board findById(Long id) {
		// TODO Auto-generated method stub
		Board board=boardRepository.findById(id).get();
		board.setHitcount(board.getHitcount()+1);
		return board;
	}
	
	//보드 1번 게시물의 totalrow를 글쓰기 작성 할 때마다, 1씩 증가시켜서 -> filevo의 bno로 사용해볼 예정. 
	//조회수 벤치 마킹 해보기. 
	@Transactional
	@Override
	public Board findBytotalrow(Long id) {
		// TODO Auto-generated method stub
			
		Board board=boardRepository.findById(id).get();
		board.setTotalrow(board.getTotalrow()+1);
		return board;
	}
	
	

	@Transactional
	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		//boardRepository.save(board);
		// 더티 체킹
		Board b=boardRepository.findById(board.getId()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
	}

	@Transactional
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		boardRepository.deleteById(id);
	}

	@Transactional
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return boardRepository.count();
	}

	@Transactional
	@Override
	public void insetReply(Reply reply) {
		replyRepository.save(reply);
		// TODO Auto-generated method stub
		//replyRepository.replyInsert(
		//		reply.getWriter(), 
		//		reply.getContent(), 
		//		reply.getBoard().getId(), 
		//		reply.getUser().getId());
	}

	@Transactional
	@Override
	public void insertFileVO(FileVO fileVO) {
		// TODO Auto-generated method stub
		fileVORepository.save(fileVO);
	}

	@Transactional
	@Override
	public void replyDelete(Long id) {
		// TODO Auto-generated method stub
		replyRepository.deleteById(id);
	}


	



	
}
