package com.api.ptw.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apis")
public class UserController {

	
	@PostMapping(path = "/logout")
	public @ResponseBody Object doLogout(HttpServletRequest request) { 
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			resObj.put("code", HttpStatus.CREATED.value());
			resObj.put("msg", "로그아웃 완료");
			
			HttpSession session = request.getSession();
			
			
			session.removeAttribute("session");
			
			return resObj;
			
		} catch(Exception e) {
			resObj.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			return resObj;
		}
		
	}
	
	@PutMapping(path = "/put")
	public @ResponseBody Object doPut(@RequestBody Map<String, Object> param) {
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			System.out.println(param);
			
			return "풋 테스트 되나";
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
