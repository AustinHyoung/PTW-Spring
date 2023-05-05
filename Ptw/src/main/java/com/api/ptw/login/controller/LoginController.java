package com.api.ptw.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.login.service.LoginService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/apis")
public class LoginController {
	
	@Value("${test}")
	private String address;

	@Resource
	private LoginService loginService;
	
	@RequestMapping(path = "/hi")
	public Object hi(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("name", "hakyoung");
		System.out.println("api address :" + address);
		
		return "hhi12123123312hi";
	}
	
	@PostMapping(path = "/login")
	public @ResponseBody Object doLogin(HttpServletRequest request, @RequestBody Map<String, Object> paramMap) { 
		
		System.out.println(paramMap);
		try {
			Map<String, Object> resObj = new HashMap<String, Object>();
			Map<String, Object> userChk = loginService.getOneUser(paramMap);
			System.out.println("userChk ::: " + userChk);
			
			if(userChk == null) {	
				resObj.put("code", HttpStatus.BAD_REQUEST.value());
				resObj.put("msg", "아이디 또는 비밀번호를 확인해주세요.");
				
				return resObj;
			}
			resObj.put("id", userChk.get("member_no"));
			resObj.put("code", HttpStatus.OK.value());
			resObj.put("msg", "로그인 성공");
			
			HttpSession session = request.getSession();
			
			session.setAttribute("session", userChk);
			session.setMaxInactiveInterval(60*60*24);
			
			resObj.put("session", session.getId());
			resObj.put("email", userChk.get("email"));
			resObj.put("nickname", userChk.get("nickname"));
			
			System.out.println("session::" + session.getAttribute("session"));
			
			return resObj;
			
		} catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	
	
	
	
}
