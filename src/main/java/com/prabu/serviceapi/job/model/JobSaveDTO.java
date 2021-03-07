package com.prabu.serviceapi.job.model;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.job.Job;
import com.prabu.serviceapi.vehicle.Vehicle;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class JobSaveDTO {

    private Long id;
    @NotNull
    private String jobId;
    @NotNull
    private Long customer;
    private Long pastJob;
    @NotNull
    private Long vehicle;
    @NotNull
    @DecimalMin(value = "500", inclusive = false)
    private Double pastMileage;
    @NotNull
    @DecimalMin(value = "500", inclusive = false)
    private Double currentMileage;
    @NotNull
    @DecimalMin(value = "500", inclusive = false)
    private Integer nextServiceMileage;
    private Date nextServiceDate;

}
