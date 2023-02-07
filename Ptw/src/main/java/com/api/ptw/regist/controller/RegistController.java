package com.api.ptw.regist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.regist.service.RegistService;

import jakarta.annotation.Resource;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apis")
public class RegistController {
	
	@Resource
	private RegistService registService;
	
	@PostMapping(path = "/regist")
	public @ResponseBody Object doRegist(@RequestBody Map<String, Object> paramMap) {
		
		try {
			Map<String, Object> resObj = new HashMap<String, Object>();
			
			
			
			registService.registUser(paramMap);
			
			resObj.put("code", HttpStatus.CREATED.value());
			
			return resObj;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
