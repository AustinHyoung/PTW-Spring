package com.api.ptw.user.service;

import java.sql.SQLException;
import java.util.Map;

public interface UserService {
	
	public void updateNickname(Map<String, Object> paramMap) throws SQLException;
}
