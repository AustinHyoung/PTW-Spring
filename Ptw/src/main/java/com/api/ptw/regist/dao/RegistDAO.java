package com.api.ptw.regist.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegistDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public void registUser (Map<String, Object> paramMap) throws SQLException {
		sqlSession.insert("RegistDAO.registUser", paramMap);
	}
	
	@Transactional
	public int existUserCnt (String email) throws SQLException {
		return sqlSession.selectOne("RegistDAO.existUserCnt", email);
	}
	
	@Transactional
	public Map<String, Object> findUser (Map<String, Object> paramMap) throws SQLException {
		return sqlSession.selectOne("RegistDAO.findUser", paramMap);
	}
	
	@Transactional
	public Map<String, Object> existUser (Map<String, Object> paramMap) throws SQLException {
		return sqlSession.selectOne("RegistDAO.existUser", paramMap);
	}
}
