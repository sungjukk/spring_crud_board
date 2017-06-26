package com.company.jen.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.company.jen.domain.BbsVO;
import com.company.jen.domain.FileVO;
import com.company.jen.domain.PageVO;
import com.company.jen.domain.ReplyVO;

@Repository
public class BbsDAOImpl implements BbsDAO {
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	private static final String namespace = "com.company.jen.bbsMapper";
	
	@Override
	public int insertBoard(BbsVO bbs) {
		sqlSessionTemplate.insert(namespace + ".insertBoard", bbs);
		return bbs.getBbsNo();
	}
	
	@Override
	public void insertFile(FileVO file) {
		sqlSessionTemplate.insert(namespace + ".insertFile", file);
	}
	
	@Override
	public int countBoardList() {
		return sqlSessionTemplate.selectOne(namespace + ".countBoardList");
	}
	
	@Override
	public List<BbsVO> selectBoardList(PageVO page) {
		return sqlSessionTemplate.selectList(namespace + ".selectBoardList", page);
	}
	
	@Override
	public BbsVO selectBoardInfo(int bbsNo) {
		return sqlSessionTemplate.selectOne(namespace + ".selectBoardInfo", bbsNo);
	}
	
	@Override
	public FileVO selectBoardFile(int bbsNo) {
		return sqlSessionTemplate.selectOne(namespace + ".selectBoardFile", bbsNo);
	}
	
	@Override
	public void updateBoard(BbsVO bbs) {
		sqlSessionTemplate.update(namespace + ".updateBoard", bbs);
	}
	
	@Override
	public void deleteBoard(int bbsNo) {
		sqlSessionTemplate.delete(namespace + ".deleteBoard", bbsNo);
	}
	
	@Override
	public List<BbsVO> searchWord(String searchWord) {
		return sqlSessionTemplate.selectList(namespace + ".searchWord", searchWord);
	}
	
	@Override
	public void insertReply(ReplyVO reply) {
		sqlSessionTemplate.insert(namespace + ".insertReply", reply);
	}
	
	@Override
	public List<ReplyVO> selectReplyList(int bbsNo) {
		return sqlSessionTemplate.selectList(namespace + ".selectReplyList", bbsNo);
	}
	
	@Override
	public void updateReply(ReplyVO reply) {
		sqlSessionTemplate.update(namespace + ".updateReply", reply);
	}
	
	@Override
	public void deleteReply(int replyNo) {
		sqlSessionTemplate.delete(namespace + ".deleteReply", replyNo);
	}
	
}
