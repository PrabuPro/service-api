package com.prabu.serviceapi.customer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prabu.serviceapi.vehicle.Vehicle;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private List<VehicleDTO> vehicleDTOS = new ArrayList<>();


}
