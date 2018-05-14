package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao cateDao;

	public List<CategoryVo> getCateList(String id){
		
		return cateDao.getCateList(id);
	}
	
	public CategoryVo addCate(CategoryVo cateVo) {
		System.out.println("[CategoryService] >> addCate IN");

		int cateNo = cateDao.addCate(cateVo);
		
		System.out.println("[CategoryController] >> addCate OUT");

		return cateDao.selectCategory(cateNo); 
	}
}
