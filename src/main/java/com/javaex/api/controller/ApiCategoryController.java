package com.javaex.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class ApiCategoryController {

	@Autowired
	private CategoryService cateService;
	
	
	@RequestMapping(value = "/{id}/admin/category", method = RequestMethod.GET)
	public String getCateList(@PathVariable("id") String id, Model model) {
		
		List<CategoryVo> cateList = cateService.getCateList(id);
		model.addAttribute("cateList", cateList);
		return "blog/admin/blog-admin-cate";
	}
	
	@RequestMapping(value = "/addCate", method = RequestMethod.POST)
	public String addCate(@ModelAttribute CategoryVo cateVo) {
		
		
		cateService.addCate(cateVo);
		return "";
	}
	
}
