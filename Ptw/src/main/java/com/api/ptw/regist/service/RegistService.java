package com.api.ptw.regist.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface RegistService {

	public void registUser (Map<String, Object> paramMap) throws SQLException;
	
	public int existUserCnt (String email) throws SQLException;

	public Map<String, Object> findUser (Map<String, Object> paramMap) throws SQLException;
	
}
