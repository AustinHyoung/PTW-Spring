package com.api.ptw.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.login.service.LoginService;
import com.api.ptw.user.service.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apis")
public class UserController {
	
	@Resource
	private UserService userService;
	
	
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
	
	@PutMapping(path = "/nick/change")
	public @ResponseBody Object doNickChange(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			userService.updateNickname(paramMap);
			
			Map<String, Object> userNick = userService.getOneUser(paramMap);
			
			
			resObj.put("email", userNick.get("EMAIL"));
			resObj.put("nickname", userNick.get("NICKNAME"));
			
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(path = "/password/change")
	public @ResponseBody Object doPwChange(@RequestBody Map<String, Object> paramMap, HttpServletRequest request) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			Map<String, Object> password = userService.getOneUserPw(paramMap);
			System.out.println(password);
			if(!paramMap.get("password").equals(password.get("password"))) {
				resObj.put("msg", "현재 비밀번호가 일치 하지 않습니다.");
				resObj.put("code", HttpStatus.BAD_REQUEST.value());
				
				return resObj;
			}
			userService.updatePassword(paramMap);
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping(path = "/delete/user")
	public @ResponseBody Object deleteUser(@RequestBody Map<String, Object> paramMap, HttpServletRequest request) {
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			System.out.println(paramMap);
			userService.deleteUser(paramMap);
			resObj.put("code", HttpStatus.OK.value());
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}



























