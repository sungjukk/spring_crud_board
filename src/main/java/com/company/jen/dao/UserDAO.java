package com.company.jen.dao;

import com.company.jen.domain.UserVO;

public interface UserDAO {

	/** 회원 등록 */
	public void insertUser(UserVO user);
	
	/** 아이디로 유저 정보 찾기 */
	public UserVO selectUserById(String userId);
	
	/** 유저번호로 비밀번호 바꾸기 */
	public void updateUserByUserNo(UserVO user);
	
}
