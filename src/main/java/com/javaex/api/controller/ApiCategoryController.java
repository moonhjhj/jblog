package com.javaex.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
public class ApiCategoryController {

	@Autowired
	private CategoryService cateService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/{id}/admin/category", method = RequestMethod.GET)
	public String getCateList(@PathVariable("id") String id, Model model) {
		
		List<CategoryVo> cateList = cateService.getCateList(id);
		model.addAttribute("cateList", cateList);
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-cate";
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/addCate", method = RequestMethod.GET)
	public CategoryVo addCate(@PathVariable("id") String id, @ModelAttribute CategoryVo cateVo) {
		System.out.println("[CategoryController] >> addCate IN");
		
		CategoryVo catVo = cateService.addCate(cateVo, id);
		
		System.out.println("[CategoryController] >> catVo : " + catVo.toString());
		System.out.println("[CategoryController] >> addCate success");
		return catVo;
	}
	
	@RequestMapping(value = "")
	public String deleteCate() {
		
		return "blog/admin/blog-admin-cate";
	}
}
