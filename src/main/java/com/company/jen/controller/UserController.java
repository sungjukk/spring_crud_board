package com.company.jen.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.jen.domain.PageVO;
import com.company.jen.domain.UserVO;
import com.company.jen.service.BbsService;
import com.company.jen.service.UserService;
import com.company.jen.util.Encrypt;
import com.company.jen.util.PagingUtils;

@Controller
public class UserController {
	
	@Inject
	private UserService userService;
	@Inject
	private BbsService bbsService;
	
	private PageVO pv = new PageVO();
	
	@RequestMapping(value = "/signin.do")
	public String signin() {
		
		return "user/signin";
	}
	
	@RequestMapping(value = "/signup.do")
	public String signup() {
		
		return "user/signup";
	}
	
	@RequestMapping(value = "/signup_mod.do")
	public String signupMod() {
		return "user/signup_mod";
	}
	
	@RequestMapping(value = "/a.do")
	public String a(Model model, int pageNo) {
		PagingUtils pc = new PagingUtils();
		pv = pc.initPage(pageNo, 10, bbsService.countBoardList());
		model.addAttribute("pv", pv);
		model.addAttribute("boardList", bbsService.selectAllBoard(pv));
		return "user/a";
	}
	
	/**
	 * 회원가입
	 * @param user
	 * @return 
	 */
	@RequestMapping(value="/join.do")
	public String insertUser(@ModelAttribute UserVO user) {
		userService.insertUser(user);
		return "redirect:/signin.do";
	}
	
	/**
	 * 로그인
	 * @param session
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/login.do")
	public String login(Model model, HttpServletRequest request, @ModelAttribute UserVO user) {
		boolean isLogin = userService.login(request, user);
		if (isLogin == true) {
			return "redirect:/a.do?pageNo=1";
		}
		model.addAttribute("msg", "로그인 실패");
		return "user/signin";
	}
	
	/**
	 * 회원정보 수정
	 * @param session
	 * @param userPw
	 * @return
	 */
	@RequestMapping(value="/modifyprofile.do")
	public String updateUser(HttpSession session, String userPw) {
		UserVO userInfo = (UserVO) session.getAttribute("userInfo");
		UserVO user = new UserVO(userInfo.getUserNo(), Encrypt.encryptMD5(userPw));
		userService.updateUser(user);
		return "redirect:/a.do?pageNo=1";
	}
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/signin.do";
	}
	
}
