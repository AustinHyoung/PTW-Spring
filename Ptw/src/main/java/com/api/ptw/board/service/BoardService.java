package com.api.ptw.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BoardService {
	
	public List<Map<String, Object>> getBoardList(Map<String, Object> paramMap) throws SQLException;
}
