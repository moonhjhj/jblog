package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	public String idCheck(String id) {
		System.out.println("userService id: " + id);
		String count = userDao.idCheck(id);
		String result;
		
		if(count !=null) {
			result = "true";
		}else {
			result = "false";
		}
		System.out.println(result);
		return result;
	}
	
	
	public int join(UserVo userVo) {
		int joinResult = userDao.join(userVo);
		blogDao.makeBlog(userVo.getId());
		return joinResult;
	}
	
	
	public UserVo login(UserVo userVo) {
		
		return userDao.login(userVo);
	}
}
