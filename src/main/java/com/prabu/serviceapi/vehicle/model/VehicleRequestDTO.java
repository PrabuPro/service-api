package com.prabu.serviceapi.vehicle.model;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.model.CustomerDTO;
import lombok.Data;

@Data
public class VehicleRequestDTO {

    private Long id;
    private String vehicleNumber;
    private Integer year;
    private String model;
    private String chassisNumber;
    private String engineNumber;
    private Long customer;
    private Double currentMileage;

}
