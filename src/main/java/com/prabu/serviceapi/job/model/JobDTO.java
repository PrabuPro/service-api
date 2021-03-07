package com.prabu.serviceapi.job.model;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.model.CustomerDTO;
import com.prabu.serviceapi.job.Job;
import com.prabu.serviceapi.vehicle.Vehicle;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class JobDTO {

    private Long id;
    private String jobId;
    private CustomerDTO customer;
    private JobDTO pastJob;
    private VehicleDTO vehicle;
    private Double pastMileage;
    private Double currentMileage;
    private Integer nextServiceMileage;
    private Date nextServiceDate;

}
