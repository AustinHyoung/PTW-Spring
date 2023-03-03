package com.api.ptw.user.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ptw.user.dao.UserDAO;
import com.api.ptw.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public void updateNickname(Map<String, Object> paramMap) throws SQLException {
		userDao.updateNickname(paramMap);
	}
	
	@Override
	public Map<String, Object> getOneUser(Map<String, Object> paramMap) throws SQLException {
		return userDao.getOneUser(paramMap);
	}
}
