package com.prabu.serviceapi.job;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.vehicle.Vehicle;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String jobId;

    @ManyToOne
    private Customer customer;

    @Nullable
    @OneToOne
    private Job pastJob;

    @ManyToOne
    private Vehicle vehicle;

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
