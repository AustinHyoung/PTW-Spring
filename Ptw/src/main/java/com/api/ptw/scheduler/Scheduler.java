package com.api.ptw.scheduler;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	@Autowired
	private SchedulerDAO schedulerDAO;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void deleteOldUser() throws SQLException {
		schedulerDAO.deleteOldUser();
	}
}
