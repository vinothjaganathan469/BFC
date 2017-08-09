package com.chatback.dao;

import java.util.List;

import com.chatback.model.Job;

public interface JobDao {
	void saveJob(Job job);
	List<Job> getAllJobs();
	Job getJobById(int id);
}
