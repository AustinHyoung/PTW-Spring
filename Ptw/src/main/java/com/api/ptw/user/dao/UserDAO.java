package com.api.ptw.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
}
