package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.customer.Customer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

}
