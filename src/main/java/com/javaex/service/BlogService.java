package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	public BlogVo getBlog(String id) {
		
		return blogDao.getBlog(id);
		
	}
	
	public int blogMgt(BlogVo blogVo, MultipartFile file) {
		System.out.println("[Blog Service]");
		String saveDir = "C:\\Users\\aran0\\Desktop\\BIT\\Spring_mybatis\\upload";
		
		//오리지날 파일명  =>사용자가 준 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		//확장자  =>
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName :" + exName);
		
		
		//저장파일명  =>중북되지 않게 이름 만들어내는 로직 필요(어떠한 경우도 겹치면 안됨)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName :" + saveName);
		
		//파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
//		//파일사이즈
//		long fileSize = file.getSize();
//		System.out.println("fileSize :" + fileSize);
		
		blogVo.setLogoFile(filePath);
		System.out.println("[filePath]: " + filePath.toString());
		
		//다오 연결 DB저장
		
		
		//파일 서버 복사
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bout= new BufferedOutputStream(out); //buffer 껴주면 빠름
			
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("[Blog Service] blogVo >>" + blogVo.toString());
		
		return blogDao.blogMgt(blogVo);
		
		

		
		
	}
}
