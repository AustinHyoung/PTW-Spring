package com.api.ptw.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BoardService {
	
	public List<Map<String, Object>> getBoardList(Map<String, Object> paramMap) throws SQLException;
	
	public void createBoard(Map<String, Object> paramMap) throws SQLException;
	
	public List<Map<String, Object>> getCardList(Map<String, Object> param) throws SQLException;
	
	public List<Map<String, Object>> getCard(Map<String, Object> param) throws SQLException;
	
	public void updateCard(Map<String, Object> paramMap) throws SQLException;
	
	public Map<String, Object> getLastBoard(Map<String, Object> paramMap) throws SQLException;
	
	public Map<String, Object> getCount(Map<String, Object> paramMap) throws SQLException;
	
	public void setCount(Map<String, Object> paramMap) throws SQLException;
	
	public void initialCardsList(List<Map<String, Object>> paramMap) throws SQLException;
	
	public Map<String, Object> getCardListNo(Map<String, Object> paramMap) throws SQLException;
	
	public void initialCard(List<Map<String, Object>> paramMap) throws SQLException;
}
