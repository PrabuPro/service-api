package com.prabu.serviceapi.job.mapper;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.job.Job;
import com.prabu.serviceapi.job.model.JobDTO;
import com.prabu.serviceapi.job.model.JobSaveDTO;
import com.prabu.serviceapi.vehicle.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(target = "id", source = "jobSaveDTO.id")
    @Mapping(target = "jobId" , source = "jobSaveDTO.jobId")
    @Mapping(target = "pastMileage", source = "jobSaveDTO.pastMileage")
    @Mapping(target = "currentMileage", source = "jobSaveDTO.currentMileage")
    @Mapping(target = "nextServiceMileage", source = "jobSaveDTO.nextServiceMileage")
    @Mapping(target = "nextServiceDate", source = "jobSaveDTO.nextServiceDate")
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "vehicle", source = "vehicle")
    @Mapping(target = "pastJob", source = "job")
    Job jobSaveDTOToJob(JobSaveDTO jobSaveDTO, Customer customer, Vehicle vehicle, Job job);

    @Mapping(target = "id", source = "job.id")
    @Mapping(target = "jobId" , source = "job.jobId")
    @Mapping(target = "pastMileage", source = "job.pastMileage")
    @Mapping(target = "currentMileage", source = "job.currentMileage")
    @Mapping(target = "nextServiceMileage", source = "job.nextServiceMileage")
    @Mapping(target = "nextServiceDate", source = "job.nextServiceDate")
    @Mapping(target = "customer", source = "job.customer")
    @Mapping(target = "vehicle", source = "job.vehicle")
    @Mapping(target = "pastJob", source = "job.pastJob")
    JobDTO jobToJobDTO(Job job);

}
