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
	private RegistDAO registDao;
	
	
	@Override
	public void registUser(Map<String, Object> paramMap) throws SQLException {
		registDao.registUser(paramMap);
	}
	
	@Override
	public int overlapUser(Map<String, Object> paramMap) throws SQLException {
		return registDao.overlapUser(paramMap);
	}
	
	@Override
	public Map<String, Object> findUser(Map<String, Object> paramMap) throws SQLException {
		return registDao.findUser(paramMap);
	}
	
}
