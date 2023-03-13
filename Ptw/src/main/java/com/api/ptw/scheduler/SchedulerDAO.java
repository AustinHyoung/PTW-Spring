package com.api.ptw.scheduler;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SchedulerDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public void deleteOldUser() throws SQLException  {
		sqlSession.delete("UserDAO.deleteOldUser");
	}
}
