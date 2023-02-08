package com.api.ptw.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.login.service.LoginService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apis")
public class LoginController {

	@Resource
	private LoginService loginService;
	
	@RequestMapping(path = "/hi")
	public Object hi(HttpSession session) {
		System.out.println(session);
		return "hhi12123123312hi";
	}
	
	@PostMapping(path = "/login")
	public @ResponseBody Object doLogin(HttpSession session, @RequestBody Map<String, Object> paramMap) { 
		
		System.out.println(paramMap);
		try {
			Map<String, Object> resObj = new HashMap<String, Object>();
			Map<String, Object> userChk = loginService.getOneUser(paramMap);
			System.out.println(userChk);
			
			if(userChk == null) {
				resObj.put("code", HttpStatus.NOT_FOUND.value());
				resObj.put("msg", "아이디 또는 비밀번호를 확인해주세요.");
				
				return resObj;
			}
			
			resObj.put("code", HttpStatus.OK.value());
			resObj.put("msg", "로그인 성공");
			
			session.setAttribute("user_session", userChk);
			session.setMaxInactiveInterval(1800);
			
			resObj.put("session", session.getId());
			
			System.out.println("session ::::" + session.getAttribute("user_session"));
			
			return resObj;
			
		} catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
}
