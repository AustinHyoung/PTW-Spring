package com.api.ptw.regist.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		try {
			
			int existUser = registService.existUserCnt(paramMap.get("email").toString());
			
			//이메일 중복 체크
			
			if(existUser == 1) {
				resObj.put("code", HttpStatus.CONFLICT.value());
				resObj.put("msg", "이미 존재하는 이메일 입니다.");
				
				return resObj;
			}
			
			//인증번호 체크
			if (!paramMap.get("auth_num").equals(authNum)) {
				resObj.put("code", HttpStatus.UNAUTHORIZED.value());
				resObj.put("msg", "인증번호를 다시 확인해 주세요.");
				
				return resObj;
			}
			
			registService.registUser(paramMap);
			
			resObj.put("code", HttpStatus.CREATED.value());
			
			return resObj;
			
		} catch (Exception e) {
			e.printStackTrace();
			resObj.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			return null;
		}
	}
	
	@PostMapping(path = "/find/password")
	public @ResponseBody Object findPassword(@RequestBody Map<String, Object> email) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			String user_password = registService.findUser(email).get("password").toString();
			System.out.println(user_password);
			
			resObj.put("password", user_password);
			resObj.put("code", HttpStatus.CREATED.value());
			resObj.put("msg", "비밀번호 전송 완료");
			
			return resObj;
			
		} catch(Exception e) {
			e.printStackTrace();
			
			resObj.put("code", HttpStatus.NO_CONTENT.value());
			resObj.put("msg", "가입된 이메일이 아닙니다.");
			
			return resObj;
		}
	}
	
	@PostMapping(path = "/mail/auth")
	public @ResponseBody Object mailAuth(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			System.out.println(paramMap);
			
			authNum = mailService.sendEmail(paramMap.get("email").toString());
			System.out.println(authNum);
			
			resObj.put("code", HttpStatus.OK.value());
			resObj.put("msg", "인증번호 전송 완료");
			
			return resObj;
			
		} catch(Exception e) {
			e.printStackTrace();
			resObj.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			resObj.put("msg", "인증번호 전송 실패");
			
			
			return resObj;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
