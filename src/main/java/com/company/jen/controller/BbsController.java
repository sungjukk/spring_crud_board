package com.company.jen.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.company.jen.domain.BbsVO;
import com.company.jen.domain.FileVO;
import com.company.jen.domain.ReplyVO;
import com.company.jen.domain.UserVO;
import com.company.jen.service.BbsService;
import com.company.jen.util.FileUtils;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	
	@Inject
	private BbsService bbsService;
	
	/**
	 * 글 작성 폼
	 * @return
	 */
	@RequestMapping(value = "/writeForm.do")
	public String writeForm() {
		return "bbs/writeForm";
	}
	
	/**
	 * 글 등록
	 * @param bbs
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/write.do")
	public String insertBoard(HttpServletRequest request, MultipartHttpServletRequest mRequest) throws Exception {
		BbsVO bbs = new BbsVO();
		bbs.setUserNo(((UserVO) request.getSession().getAttribute("userInfo")).getUserNo());
		bbs.setBbsTitle(mRequest.getParameter("bbsTitle"));
		bbs.setBbsContent(mRequest.getParameter("bbsContent").replaceAll("\r\n", "<br>"));
		
		FileUtils fu = new FileUtils();
		FileVO file = fu.fileUpload(request, mRequest);
		
		bbsService.insertBoard(bbs, file);
		return "redirect:/a.do?pageNo=1";
	}
	
	/**
	 * 글 상세 조회
	 * @param bbsNo
	 * @return
	 */
	@RequestMapping(value = "/detail.do")
	public String selectOneBoard(Model model, int bbsNo) {
		model.addAttribute("board", bbsService.selectOneBoard(bbsNo));
		model.addAttribute("file", bbsService.selectBoardFile(bbsNo));
		return "bbs/detail";
	}
	
	/**
	 * 글 수정 폼
	 * @return
	 */
	@RequestMapping(value = "/updateForm.do")
	public String updateForm(Model model, int bbsNo) {
		BbsVO bbs = bbsService.selectOneBoard(bbsNo);
		bbs.setBbsContent(bbs.getBbsContent().replaceAll("<br>", "\r\n"));
		model.addAttribute("board", bbs);
		return "bbs/updateForm";
	}
	
	/**
	 * 글 수정
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String updateBoard(@ModelAttribute BbsVO bbs) {
		bbs.setBbsContent(bbs.getBbsContent().replaceAll("\r\n", "<br>"));
		bbsService.updateBoard(bbs);
		return "redirect:/bbs/detail.do?bbsNo=" + bbs.getBbsNo();
	}
	
	/**
	 * 글 삭제
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String deleteBoard(@RequestParam List<Integer> bbsNoList) {
		for (int bbsNo : bbsNoList) {
			bbsService.deleteBoard(bbsNo);
		}
		return "redirect:/a.do?pageNo=1";
	}
	
	/**
	 * 글 검색
	 * @param searchWord
	 * @return
	 */
	@RequestMapping(value = "/searchWord.json")
	public @ResponseBody List<BbsVO> searchWord(String searchWord) {
		return bbsService.searchWord(searchWord);
	}
	
	/**
	 * 댓글 리스트
	 * @param bbsNo
	 * @return
	 */
	@RequestMapping(value = "/replyList.json")
	public @ResponseBody List<ReplyVO> selectReplyList(int bbsNo) {
		return bbsService.selectReplyList(bbsNo);
	}
	
	/**
	 * 댓글 등록
	 * @param reply
	 */
	@RequestMapping(value = "/insertReply.json")
	public @ResponseBody List<ReplyVO> insertReply(@ModelAttribute ReplyVO reply) {
		bbsService.insertReply(reply);
		return bbsService.selectReplyList(reply.getBbsNo());
	}
	
	/**
	 * 댓글 수정
	 * @param reply
	 * @return
	 */
	@RequestMapping(value = "/updateReply.json")
	public @ResponseBody List<ReplyVO> updateReply(@ModelAttribute ReplyVO reply) {
		bbsService.updateReply(reply);
		return bbsService.selectReplyList(reply.getBbsNo());
	}
	
	/**
	 * 댓글 삭제
	 * @param reply
	 * @return
	 */
	@RequestMapping(value = "/deleteReply.json")
	public @ResponseBody List<ReplyVO> deleteReply(@ModelAttribute ReplyVO reply) {
		bbsService.deleteReply(reply.getReplyNo());
		return bbsService.selectReplyList(reply.getBbsNo());
	}
	
}
