package com.api.ptw.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public List<Map<String, Object>> getBoardList(Map<String, Object> paramMap) throws SQLException {
		return sqlSession.selectList("BoardDAO.getBoardList", paramMap);
	}
	
	@Transactional
	public void createBoard(Map<String, Object> paramMap) throws SQLException {
		sqlSession.insert("BoardDAO.createBoard", paramMap);
	}
	
	@Transactional
	public List<Map<String, Object>> getCardList(Map<String, Object> param) throws SQLException {
		return sqlSession.selectList("BoardDAO.getCardList", param);
	}
	
	@Transactional
	public List<Map<String, Object>> getCard(Map<String, Object> param) throws SQLException {
		return sqlSession.selectList("BoardDAO.getCard", param);
	}
	
	@Transactional
	public void updateCard(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.updateCard", paramMap);
	}
	
	@Transactional
	public Map<String, Object> getLastBoard(Map<String, Object> paramMap) throws SQLException {
		return sqlSession.selectOne("BoardDAO.getLastBoard", paramMap);
	}
	
	@Transactional
	public Map<String, Object> getCount(Map<String, Object> paramMap) throws SQLException {
		return sqlSession.selectOne("BoardDAO.getCount", paramMap);
	}
	
	@Transactional
	public void setCount(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.setCount", paramMap);
	}
	
	@Transactional
	public void initialCardsList(List<Map<String, Object>> paramMap) throws SQLException  {
		sqlSession.insert("BoardDAO.initialCardsList", paramMap);
	}
	
	@Transactional
	public Map<String, Object> getCardListNo(Map<String, Object> paramMap) throws SQLException {
		return sqlSession.selectOne("BoardDAO.getCardListNo", paramMap);
	}
	
	@Transactional
	public void initialCard(List<Map<String, Object>> paramMap) throws SQLException  {
		sqlSession.insert("BoardDAO.initialCard", paramMap);
	}
	
	@Transactional
	public void setCardsListPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.setCardsListPosition", paramMap);
	}
	
	@Transactional
	public void cardMissingPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardMissingPosition", paramMap);
	}
	
	@Transactional
	public void cardInsertPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardInsertPosition", paramMap);
	}
	
	@Transactional
	public void cardPushPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardPushPosition", paramMap);
	}
	
	
}
