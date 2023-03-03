package com.api.ptw.user.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public void updateNickname(Map<String, Object> paramMap) throws SQLException  {
		sqlSession.update("UserDAO.updateNickname", paramMap);
	}
	
	@Transactional
	public Map<String, Object> getOneUser(Map<String, Object> paramMap) throws SQLException {
		return sqlSession.selectOne("UserDAO.getOneUser", paramMap);
	}
}
