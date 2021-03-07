package com.prabu.serviceapi.job;

import com.prabu.serviceapi.job.model.JobDTO;
import com.prabu.serviceapi.job.model.JobSaveDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(JobController.API_V_1_JOB)
public class JobController {
    public static final String API_V_1_JOB = "/api/v1/job";

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping()
    public ResponseEntity<JobDTO> saveJob(@Valid @RequestBody JobSaveDTO jobSaveDTO){
        return new ResponseEntity<>(jobService.saveJob(jobSaveDTO), HttpStatus.CREATED);
    }
}
