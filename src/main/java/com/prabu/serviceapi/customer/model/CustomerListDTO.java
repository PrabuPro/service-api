package com.prabu.serviceapi.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerListDTO {

    List<CustomerDTO> customers;

}
