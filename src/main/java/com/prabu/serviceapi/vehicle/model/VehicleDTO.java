package com.prabu.serviceapi.vehicle.model;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.model.CustomerDTO;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class VehicleDTO {

    private Long id;
    private String vehicleNumber;
    private Integer year;
    private String model;
    private String chassisNumber;
    private String EngineNumber;
    private CustomerDTO customer;
    private Double currentMilage;

}
