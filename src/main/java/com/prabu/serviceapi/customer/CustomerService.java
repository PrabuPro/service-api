package com.prabu.serviceapi.customer;

import com.prabu.serviceapi.customer.model.CustomerDTO;
import com.prabu.serviceapi.customer.model.CustomerPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomers();

    Page<CustomerDTO> getCustomerGrid(CustomerPage customerPage);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id,CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}
