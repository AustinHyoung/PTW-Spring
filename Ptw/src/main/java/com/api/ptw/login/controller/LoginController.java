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
	public Object hi() {
		return "hhi12123123312hi";
	}
	
	@RequestMapping(path = "/get")
	public Object getUser() {
		try {
			List<Map<String, Object>> userList = loginService.getUser();
			
			return userList;
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		}
		
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
			session.setMaxInactiveInterval(60*60*2);
			
			resObj.put("session", session.getAttribute("user_session"));
			
			System.out.println("session ::::" + session.getAttribute("user_session"));
			
			return resObj;
			
		} catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	@PostMapping(path = "/kill")
	public @ResponseBody Object doKill(HttpSession session) { 
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			resObj.put("code", HttpStatus.CREATED.value());
			resObj.put("msg", "로그아웃 완료");
			
			session.removeAttribute("user_session");
			
			return resObj;
			
		} catch(Exception e) {
			resObj.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			return resObj;
		}
		
	}
}
