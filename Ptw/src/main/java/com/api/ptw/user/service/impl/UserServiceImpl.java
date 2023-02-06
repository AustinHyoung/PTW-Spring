package com.api.ptw.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.ptw.user.dao.UserDAO;
import com.api.ptw.user.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
}
