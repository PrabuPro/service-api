package com.prabu.serviceapi.job;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.CustomerRepository;
import com.prabu.serviceapi.job.mapper.JobMapper;
import com.prabu.serviceapi.job.model.JobDTO;
import com.prabu.serviceapi.job.model.JobSaveDTO;
import com.prabu.serviceapi.vehicle.Vehicle;
import com.prabu.serviceapi.vehicle.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class JobServiceImplTest {

    public static final long ID = 1L;
    public static final String JOB_ID = "00001";
    public static final long CUSTOMER_ID = 2L;
    public static final long VEHICLE_ID = 3L;
    public static final long PAST_JOB_ID = 4L;
    @Mock
    JobRepository jobRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    VehicleRepository vehicleRepository;


    JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jobService = new JobServiceImpl(jobRepository, customerRepository, vehicleRepository, JobMapper.INSTANCE);

    }

    @Test
    void saveJob() {
        Customer customer = new Customer();
        customer.setId(2L);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(VEHICLE_ID);
        Optional<Vehicle> optionalVehicle = Optional.of(vehicle);


        Job pastJob = new Job();
        pastJob.setId(4L);
        Optional<Job> optionalJob = Optional.of(pastJob);

        Job job = new Job();
        job.setId(ID);
        job.setJobId(JOB_ID);
        job.setCustomer(customer);
        job.setVehicle(vehicle);
        job.setPastJob(pastJob);

        when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);
        when(vehicleRepository.findById(anyLong())).thenReturn(optionalVehicle);
        when(jobRepository.findById(anyLong())).thenReturn(optionalJob);
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        JobSaveDTO jobSaveDTO = new JobSaveDTO();
        jobSaveDTO.setJobId(JOB_ID);
        jobSaveDTO.setCustomer(CUSTOMER_ID);
        jobSaveDTO.setVehicle(VEHICLE_ID);
        jobSaveDTO.setPastJob(PAST_JOB_ID);

        JobDTO savedJob = jobService.saveJob(jobSaveDTO);

        assertEquals(savedJob.getId(), 1);
        assertEquals(savedJob.getJobId(), JOB_ID);
        assertEquals(savedJob.getCustomer().getId(), CUSTOMER_ID);

    }
}