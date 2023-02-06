package com.api.ptw.login.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface LoginService {
	
	public List<Map<String, Object>> getUser() throws SQLException;

}
