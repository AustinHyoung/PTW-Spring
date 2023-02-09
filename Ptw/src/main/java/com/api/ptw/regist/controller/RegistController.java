package com.api.ptw.regist.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.mail.MailService;
import com.api.ptw.regist.service.RegistService;

import jakarta.annotation.Resource;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apis")
public class RegistController {
	
	private String authNum;
	
	@Resource
	private RegistService registService;
	
	@Resource
	private MailService mailService;
	
	@PostMapping(path = "/regist")
	public @ResponseBody Object doRegist(@RequestBody Map<String, Object> paramMap) {
		System.out.println(paramMap);
		
		try {
			Map<String, Object> resObj = new HashMap<String, Object>();
			
			
			//이메일 중복 체크
			int overlapUser = registService.overlapUser(paramMap);
			
			if(overlapUser == 1) {
				resObj.put("code", HttpStatus.CONFLICT.value());
				resObj.put("msg", "이미 존재하는 이메일 입니다.");
				
				return resObj;
			}
			
			registService.registUser(paramMap);
			
			resObj.put("code", HttpStatus.CREATED.value());
			
			return resObj;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping(path = "/mail/auth")
	public @ResponseBody Object mailAuth(@RequestBody Map<String, Object> email) {
		
		
		try {
			
			Map<String, Object> resObj = new HashMap<String, Object>();
			
			authNum = mailService.sendEmail(email.get("email").toString());
			System.out.println(authNum);
			
			resObj.put("code", HttpStatus.CREATED.value());
			resObj.put("msg", "인증번호 전송 완료");
			
			return resObj;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
