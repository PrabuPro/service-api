package com.prabu.serviceapi.customer.mapper;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO CustomerToCustomerDTO(Customer customer);
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);

}
