package com.prabu.serviceapi.customer;

import com.prabu.serviceapi.customer.model.CustomerDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomers();

    Page<CustomerDTO> getCustomerGrid(PaginationPage paginationPage);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id,CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}
