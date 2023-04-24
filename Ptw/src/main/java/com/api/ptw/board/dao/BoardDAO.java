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
	
	@Transactional
	public void cardSamePosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardSamePosition", paramMap);
	}
	
	@Transactional
	public void cardHighSamePosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardHighSamePosition", paramMap);
	}
	
	@Transactional
	public void cardLowSamePosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardLowSamePosition", paramMap);
	}
	
	@Transactional
	public void deleteBoard(int boardNo) throws SQLException  {
		sqlSession.delete("BoardDAO.deleteBoard", boardNo);
	}
	
	@Transactional
	public void addCardsList(Map<String, Object> paramMap) throws SQLException {
		sqlSession.insert("BoardDAO.addCardsList", paramMap);
	}
	
	@Transactional
	public void editCardsList(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.editCardsList", paramMap);
	}
	
	@Transactional
	public void deleteCardsListPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.deleteCardsListPosition", paramMap);
	}
	
	@Transactional
	public void deleteCardsList(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.delete("BoardDAO.deleteCardsList", paramMap);
	}
	
	@Transactional
	public void addCard(Map<String, Object> paramMap) throws SQLException {
		sqlSession.insert("BoardDAO.addCard", paramMap);
	}
	
	@Transactional
	public void cardsListInsertPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardsListInsertPosition", paramMap);
	}
	
	@Transactional
	public void cardsListHighPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardsListHighPosition", paramMap);
	}
	
	@Transactional
	public void cardsListLowPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.cardsListLowPosition", paramMap);
	}
	
	@Transactional
	public void editCard(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.editCard", paramMap);
	}
	
	@Transactional
	public void deleteCardPosition(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.deleteCardPosition", paramMap);
	}
	
	@Transactional
	public void deleteCard(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.delete("BoardDAO.deleteCard", paramMap);
	}
	
	@Transactional
	public void editBoardTitle(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("BoardDAO.editBoardTitle", paramMap);
	}
	
	
}
