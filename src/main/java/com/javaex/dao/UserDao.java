package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public String idCheck(String id) {
		System.out.println("userDao id: " + id);
		String a = sqlSession.selectOne("user.idCheck", id);
		System.out.println(a);
		return sqlSession.selectOne("user.idCheck", id);
		
		
	}
	
	
	public int join(UserVo userVo) {
		
		return sqlSession.insert("user.join", userVo);
	}
	
	
	public UserVo login(UserVo userVo) {
		
		return sqlSession.selectOne("user.login", userVo);
	}
}
