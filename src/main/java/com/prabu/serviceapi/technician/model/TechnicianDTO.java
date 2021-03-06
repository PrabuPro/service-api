package com.prabu.serviceapi.technician.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TechnicianDTO {

    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

}
