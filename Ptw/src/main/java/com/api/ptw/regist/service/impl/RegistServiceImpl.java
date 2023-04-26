package com.api.ptw.regist.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ptw.regist.dao.RegistDAO;
import com.api.ptw.regist.service.RegistService;

@Service
public class RegistServiceImpl implements RegistService {
	
	@Autowired
	private RegistDAO registDAO;
	
	
	@Override
	public void registUser(Map<String, Object> paramMap) throws SQLException {
		registDAO.registUser(paramMap);
	}
	
	@Override
	public int existUserCnt(String email) throws SQLException {
		return registDAO.existUserCnt(email);
	}
	
	@Override
	public Map<String, Object> findUser(Map<String, Object> paramMap) throws SQLException {
		return registDAO.findUser(paramMap);
	}
	
}
