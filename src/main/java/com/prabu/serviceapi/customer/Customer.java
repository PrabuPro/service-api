package com.prabu.serviceapi.customer;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "first name cannot be empty")
    @NotEmpty(message = "first name cannot be empty")
    private String firstName;

    @NotNull(message = "last name cannot be empty")
    @NotEmpty(message = "last name cannot be empty")
    private String lastName;

    @Email(message = "email should be a valid email")
    @NotNull(message = "email cannot be empty")
    @NotEmpty(message = "email name cannot be empty")
    private String email;

    @Size(max = 15)
    @NotNull(message = "mobile number cannot be empty")
    @Pattern(message = "mobile number must be a number", regexp="^[0-9]*$")
    @NotEmpty(message = "mobile number cannot be empty")
    private String mobileNumber;

    private String Address;
}
