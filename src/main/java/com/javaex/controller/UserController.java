package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		System.out.println("loginform");
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = userService.login(userVo);
		
		if(authUser != null) {
			
			session.setAttribute("authUser", authUser);
			System.out.println(authUser.toString());
			return "redirect:/"+authUser.getId();
			
		}else {

			return "redirect:/user/loginform?result=fail";
		}
		
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinform() {

		System.out.println("joinform");
		return "user/joinForm";
	}

	@RequestMapping(value = "/userjoin", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("userjoin");
		System.out.println(userVo.toString());
		userService.join(userVo);
		
		
		return "user/joinSuccess";
	}

	@ResponseBody
	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public String idCheck(@RequestParam("id") String id) {
		System.out.println("Controller idCheck id: " + id);

		return userService.idCheck(id);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	

}
