package com.company.jen.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.company.jen.dao.UserDAO;
import com.company.jen.domain.UserVO;
import com.company.jen.util.Encrypt;

@Service
public class UserService {

	@Inject
	private UserDAO userDao;
	
	/** 회원가입 */
	public void insertUser(UserVO user) {
		user.setUserPw(Encrypt.encryptMD5(user.getUserPw()));
		userDao.insertUser(user);
	};
	
	/** 로그인 */
	public boolean login(HttpServletRequest request, UserVO user) {
		UserVO userInfo = userDao.selectUserById(user.getUserId());
		if(userInfo.getUserPw().equals(Encrypt.encryptMD5(user.getUserPw()))) {
			request.getSession().setAttribute("userInfo", userInfo);
			return true;
		}
		return false;
	}
	
	/** 회원정보 수정 */
	public void updateUser(UserVO user) {
		userDao.updateUserByUserNo(user);
	}
	
}
