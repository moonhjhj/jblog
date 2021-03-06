package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void makeCate(CategoryVo cateVo) {
		
		sqlSession.insert("cate.makeCate", cateVo);
		
	}
	public List<CategoryVo> getCateList(String id){
		
		return sqlSession.selectList("cate.getCateList", id);
		
	}
	public CategoryVo selectCategory(int cateNo) {
		return sqlSession.selectOne("cate.selectCategory", cateNo);
		
	}
	
	public int addCate(CategoryVo cateVo) {
		System.out.println("[CategoryDao] >> addCate IN");
		System.out.println(cateVo.toString());
		sqlSession.insert("cate.addCate", cateVo);
		return cateVo.getCateNo();
	}
}
