package com.api.ptw.login.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public List<Map<String, Object>> getUser() throws SQLException {
		return sqlSession.selectList("LoginDAO.getUser");
	}
}
