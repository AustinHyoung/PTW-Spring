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
}
