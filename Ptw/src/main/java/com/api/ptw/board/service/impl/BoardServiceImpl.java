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
	public void cardMissingPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardMissingPosition(paramMap);
	}
	
	@Override
	public void cardInsertPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardInsertPosition(paramMap);
	}
	
	@Override
	public void cardPushPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardPushPosition(paramMap);
	}
	
	@Override
	public void cardSamePosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardSamePosition(paramMap);
	}
	
	@Override
	public void cardHighSamePosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardHighSamePosition(paramMap);
	}
	
	@Override
	public void cardLowSamePosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardLowSamePosition(paramMap);
	}
	
	@Override
	public void deleteBoard(int boardNo) throws SQLException {
		boardDAO.deleteBoard(boardNo);
	}
	
	@Override
	public void addCardsList(Map<String, Object> paramMap) throws SQLException {
		boardDAO.addCardsList(paramMap);
	}
	
	@Override
	public void editCardsList(Map<String, Object> paramMap) throws SQLException {
		boardDAO.editCardsList(paramMap);
	}
	
	@Override
	public void deleteCardsListPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.deleteCardsListPosition(paramMap);
	}
	
	@Override
	public void deleteCardsList(Map<String, Object> paramMap) throws SQLException {
		boardDAO.deleteCardsList(paramMap);
	}
	
	@Override
	public void addCard(Map<String, Object> paramMap) throws SQLException {
		boardDAO.addCard(paramMap);
	}
	
	@Override
	public void cardsListInsertPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardsListInsertPosition(paramMap);
	}
	
	@Override
	public void cardsListHighPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardsListHighPosition(paramMap);
	}
	
	@Override
	public void cardsListLowPosition(Map<String, Object> paramMap) throws SQLException {
		boardDAO.cardsListLowPosition(paramMap);
	}
}
