package com.api.ptw.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apis")
public class UserController {

	
	@PostMapping(path = "/logout")
	public @ResponseBody Object doLogout(HttpSession session) { 
		
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
