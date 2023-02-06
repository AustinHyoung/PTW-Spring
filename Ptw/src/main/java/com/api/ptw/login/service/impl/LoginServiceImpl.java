package com.api.ptw.login.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ptw.login.dao.LoginDAO;
import com.api.ptw.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAO loginDao;
	
	@Override
	public List<Map<String, Object>> getUser() throws SQLException {
		return loginDao.getUser();
	}
}
