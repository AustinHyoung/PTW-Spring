package com.api.ptw.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.board.service.BoardService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/apis")
public class BoardController {
	
	@Resource
	private BoardService boardService;
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/boardlist")
	public Object getBoardList(HttpSession session) {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try {
			userInfo = (Map<String,Object>)session.getAttribute("session");
			List<Map<String, Object>> boardList = boardService.getBoardList(userInfo);
			System.out.println(boardList);
			return boardList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
