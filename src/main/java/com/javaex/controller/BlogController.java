package com.javaex.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {

	
	@Autowired
	private BlogService blogService;
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public String blogMain(@PathVariable("id") String id, Model model) {
//		System.out.println("BlogController id: " + id);
//		BlogVo blogVo = blogService.getBlog(id);
//		String url = "";
//		model.addAttribute("blogVo", blogVo);
//		
//		if(blogVo != null) {
//			System.out.println("ID exists!");
//			url = "blog/blog-main";
//		} else {
//			System.out.println("ID not found");
//			url = "main/index";
//		}
//		
//		return url;
//	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("BlogController id: " + id);
		BlogVo blogVo = blogService.getBlog(id);
		String url = "";
		
		if(blogVo != null) {
			System.out.println("ID exists!");
			model.addAttribute("blogVo", blogVo);
			url = "blog/blog-main";
		} else {
			System.out.println("ID not found");
			url = "main/index";
		}
		
		return url;
	}
	
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.GET)
	public String blogMgtForm(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}
	
	
	@RequestMapping(value = "/{id}/admin/basic/blogMgt", method = RequestMethod.GET)
	public String blogMgt(@PathVariable("id") String id, @ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file ) {
		
		System.out.println("[Blog Controller] blogMgt");
		
		blogService.blogMgt(blogVo, file);
		
		System.out.println("[Blog Controller] blogVo >>" + blogVo.toString());
		return "redirect:/{id}/admin/basic";
	}
}
