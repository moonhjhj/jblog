package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.GET)
	public String writeForm() {
		
		return "blog/admin/blog-admin-write";
	}
}
