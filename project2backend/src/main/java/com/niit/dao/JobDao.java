package com.niit.dao;

import com.niit.models.Job;
import java.util.List;

public interface JobDao {
void saveJob(Job job);
List<Job>   getAllJobs();
}