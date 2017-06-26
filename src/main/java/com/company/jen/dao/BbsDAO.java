package com.company.jen.dao;

import java.util.List;

import com.company.jen.domain.BbsVO;
import com.company.jen.domain.FileVO;
import com.company.jen.domain.PageVO;
import com.company.jen.domain.ReplyVO;

public interface BbsDAO {
	
	/** 글 등록 */
	public int insertBoard(BbsVO bbs);
	
	/** 파일 등록 */
	public void insertFile(FileVO file);
	
	/** 전체 글 갯수 */
	public int countBoardList();
	
	/** 전체 글 조회 */
	public List<BbsVO> selectBoardList(PageVO page);
	
	/** 글 상세 조회 */
	public BbsVO selectBoardInfo(int bbsNo);
	
	/** 글 파일 조회 */
	public FileVO selectBoardFile(int bbsNo);
	
	/** 글 수정 */
	public void updateBoard(BbsVO bbs);
	
	/** 글 삭제 */
	public void deleteBoard(int bbsNo);
	
	/** 글 검색 */
	public List<BbsVO> searchWord(String searchWord);
	
	/** 댓글 등록 */
	public void insertReply(ReplyVO reply);
	
	/** 댓글 조회 */
	public List<ReplyVO> selectReplyList(int bbsNo);
	
	/** 댓글 수정 */
	public void updateReply(ReplyVO reply);
	
	/** 댓글 삭제 */
	public void deleteReply(int replyNo);
	
}
