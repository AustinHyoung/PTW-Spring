package com.api.ptw.board.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@GetMapping(path = "/boardlist")
	public String getBoardList(HttpSession session) {
		try {
			System.out.println(session.getAttribute("session"));
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
