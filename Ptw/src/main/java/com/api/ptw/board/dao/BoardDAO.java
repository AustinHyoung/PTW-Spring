package com.api.ptw.board.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public String getBoardList() throws SQLException {
		return sqlSession.selectList("BoardDAO.getBoardList");
	}
}
