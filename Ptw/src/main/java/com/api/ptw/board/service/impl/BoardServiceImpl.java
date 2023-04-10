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
	public List<Map<String, Object>> getCardList(Map<String, Object> param) throws SQLException{
		return boardDAO.getCardList(param);
	}
	
	@Override
	public List<Map<String, Object>> getCard(Map<String, Object> param) throws SQLException{
		return boardDAO.getCard(param);
	}
	
	@Override
	public void updateCard(Map<String, Object> paramMap) throws SQLException {
		boardDAO.updateCard(paramMap);
	}
	
	@Override
	public Map<String, Object> getLastBoard(Map<String, Object> paramMap) throws SQLException{
		return boardDAO.getLastBoard(paramMap);
	}
	
	@Override
	public Map<String, Object> getCount(Map<String, Object> paramMap) throws SQLException{
		return boardDAO.getCount(paramMap);
	}
	
	@Override
	public void setCount(Map<String, Object> paramMap) throws SQLException {
		boardDAO.setCount(paramMap);
	}
	
	@Override
	public void initialCardsList(List<Map<String, Object>> paramMap) throws SQLException {
		boardDAO.initialCardsList(paramMap);
	}
	
	@Override
	public Map<String, Object> getCardListNo(Map<String, Object> paramMap) throws SQLException{
		return boardDAO.getCardListNo(paramMap);
	}
	
	@Override
	public void initialCard(List<Map<String, Object>> paramMap) throws SQLException {
		boardDAO.initialCard(paramMap);
	}
	
	@Override
	public void setCardsListPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.setCardsListPosition(paramMap);
	}
}
