package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlog(String id) {
		
		return sqlSession.selectOne("blog.getBlog", id);
	}
	
	public void makeBlog(Map<String, String> blogMap) {
		
		sqlSession.insert("blog.makeBlog", blogMap);
	}
	
	public int blogMgt(BlogVo blogVo) {
		System.out.println("[Blog Dao]");
		
		System.out.println("[Blog Dao] update query >>" + blogVo.toString());
		
		return sqlSession.update("blog.blogMgt", blogVo);
	}
}
