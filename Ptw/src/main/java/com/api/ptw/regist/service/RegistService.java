package com.api.ptw.regist.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface RegistService {

	@Transactional
	public void registUser (Map<String, Object> paramMap) throws SQLException;
	
	@Transactional
	public int overlapUser (Map<String, Object> paramMap) throws SQLException;
}
