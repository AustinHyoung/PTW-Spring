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
	private UserDAO userDAO;
	
	@Override
	public void updateNickname(Map<String, Object> paramMap) throws SQLException {
		userDAO.updateNickname(paramMap);
	}
	
	@Override
	public void deleteUser(Map<String, Object> paramMap) throws SQLException {
		userDAO.deleteUser(paramMap);
	}
	
	@Override
	public void updatePassword(Map<String, Object> paramMap) throws SQLException {
		userDAO.updatePassword(paramMap);
	}
	
	@Override
	public Map<String, Object> getOneUser(Map<String, Object> paramMap) throws SQLException {
		return userDAO.getOneUser(paramMap);
	}
	
	@Override
	public Map<String, Object> getOneUserPw(Map<String, Object> paramMap) throws SQLException {
		return userDAO.getOneUserPw(paramMap);
	}
}
