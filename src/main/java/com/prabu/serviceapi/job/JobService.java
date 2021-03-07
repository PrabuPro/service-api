package com.prabu.serviceapi.job;

import com.prabu.serviceapi.job.model.JobDTO;
import com.prabu.serviceapi.job.model.JobSaveDTO;

public interface JobService {
    JobDTO saveJob(JobSaveDTO jobSaveDTO);
}
