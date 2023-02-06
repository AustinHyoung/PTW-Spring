package com.api.ptw.login.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.login.service.LoginService;

import jakarta.annotation.Resource;

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
}
