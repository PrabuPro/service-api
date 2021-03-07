package com.prabu.serviceapi.job;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.CustomerRepository;
import com.prabu.serviceapi.exception.CustomerNotFoundException;
import com.prabu.serviceapi.exception.ResourceNotFoundException;
import com.prabu.serviceapi.job.mapper.JobMapper;
import com.prabu.serviceapi.job.model.JobDTO;
import com.prabu.serviceapi.job.model.JobSaveDTO;
import com.prabu.serviceapi.vehicle.Vehicle;
import com.prabu.serviceapi.vehicle.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public JobDTO saveJob(JobSaveDTO jobSaveDTO) {
        Customer customer = customerRepository.findById(jobSaveDTO.getCustomer())
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
        Vehicle vehicle = vehicleRepository.findById(jobSaveDTO.getVehicle())
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found"));

        Job pastJob = null;
        if(jobSaveDTO.getPastJob() != null){
            pastJob = jobRepository.findById(jobSaveDTO.getPastJob())
                    .orElseThrow(() -> new ResourceNotFoundException("past Job not found"));
        }

        Job job = jobMapper.jobSaveDTOToJob(jobSaveDTO,customer, vehicle, pastJob);

        return jobMapper.jobToJobDTO(jobRepository.save(job));
    }
}
