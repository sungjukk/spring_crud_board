package com.company.jen.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.company.jen.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	private static final String namespace = "com.company.jen.userMapper";
	
	@Override
	public void insertUser(UserVO user) {
		sqlSessionTemplate.insert(namespace + ".insertUser", user);
	}
	
	@Override
	public UserVO selectUserById(String userId) {
		return sqlSessionTemplate.selectOne(namespace + ".selectUserById", userId);
	}
	
	@Override
	public void updateUserByUserNo(UserVO user) {
		sqlSessionTemplate.update(namespace + ".updateUserByUserNo", user);
	}

}
