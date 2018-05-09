package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getId(String id) {
		
		return sqlSession.selectOne("blog.getId", id);
	}
	
	public void makeBlog(BlogVo blogVo) {
		
		sqlSession.insert("blog.makeBlog", id);
	}
	
}
