package com.api.ptw.board.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ptw.board.dao.BoardDAO;
import com.api.ptw.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<Map<String, Object>> getBoardList(Map<String, Object> paramMap) throws SQLException{
		return boardDAO.getBoardList(paramMap);
	}
	
	@Override
	public void createBoard(Map<String, Object> paramMap) throws SQLException {
		boardDAO.createBoard(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> getCardList(String param) throws SQLException{
		return boardDAO.getCardList(param);
	}
}
