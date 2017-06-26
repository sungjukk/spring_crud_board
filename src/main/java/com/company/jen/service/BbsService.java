package com.company.jen.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.jen.dao.BbsDAO;
import com.company.jen.domain.BbsVO;
import com.company.jen.domain.FileVO;
import com.company.jen.domain.PageVO;
import com.company.jen.domain.ReplyVO;

@Service
public class BbsService {

	@Inject
	private BbsDAO bbsDao;
	
	/**
	 * 게시글 등록
	 * @param bbs
	 */
	public void insertBoard(BbsVO bbs, FileVO file) {
		int bbsNo = bbsDao.insertBoard(bbs);
		if (file != null) {
			file.setBbsNo(bbsNo);
			bbsDao.insertFile(file);
		}
	}
	
	/**
	 * 전체 글 갯수
	 * @return
	 */
	public int countBoardList() {
		return bbsDao.countBoardList();
	}
	
	/**
	 * 전체 글 조회
	 * @return
	 */
	public List<BbsVO> selectAllBoard(PageVO page) {
		return bbsDao.selectBoardList(page);
	}
	
	/**
	 * 글 하나 조회
	 * @param boardNo
	 * @return
	 */
	public BbsVO selectOneBoard(int bbsNo) {
		return bbsDao.selectBoardInfo(bbsNo);
	}
	
	/**
	 * 글 파일 조회
	 * @param bbsNo
	 * @return
	 */
	public FileVO selectBoardFile(int bbsNo) {
		return bbsDao.selectBoardFile(bbsNo); 
	}
	
	/**
	 * 글 수정
	 * @param bbs
	 */
	public void updateBoard(BbsVO bbs) {
		bbsDao.updateBoard(bbs);
	}
	
	/**
	 * 글 삭제
	 * @param bbsNo
	 */
	public void deleteBoard(int bbsNo) {
		bbsDao.deleteBoard(bbsNo);
	}
	
	/**
	 * 글 검색
	 * @param searchWord
	 * @return
	 */
	public List<BbsVO> searchWord(String searchWord) {
		return bbsDao.searchWord(searchWord);
	}
	
	/**
	 * 댓글 등록
	 * @param reply
	 */
	public void insertReply(ReplyVO reply) {
		bbsDao.insertReply(reply);
	}
	
	/**
	 * 댓글 조회
	 * @param bbsNo
	 * @return
	 */
	public List<ReplyVO> selectReplyList(int bbsNo) {
		return bbsDao.selectReplyList(bbsNo);
	}
	
	/**
	 * 댓글 수정
	 * @param reply
	 */
	public void updateReply(ReplyVO reply) {
		bbsDao.updateReply(reply);
	}
	
	/**
	 * 댓글 삭제
	 * @param replyNo
	 */
	public void deleteReply(int replyNo) {
		bbsDao.deleteReply(replyNo);
	}
	
}
