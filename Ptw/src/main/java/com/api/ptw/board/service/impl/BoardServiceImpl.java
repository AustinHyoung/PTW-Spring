package com.api.ptw.board.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ptw.board.dao.BoardDAO;
import com.api.ptw.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public String getBoardList() throws SQLException{
		return boardDAO.getBoardList();
	}
}
