package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.job.Job;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String vehicleNumber;

    @NotNull
    private Integer year;

    @NotNull
    private String model;

    @NotNull
    private String chassisNumber;

    @NotNull
    private String EngineNumber;

    @ManyToOne
    private Customer customer;

    private Double currentMileage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private Set<Job> jobs;

}
