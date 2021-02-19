package com.prabu.serviceapi.customer;

import com.prabu.serviceapi.customer.mapper.CustomerMapper;
import com.prabu.serviceapi.customer.model.CustomerDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::CustomerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CustomerDTO> getCustomerGrid(PaginationPage paginationPage) {
        Sort sort = Sort.by(paginationPage.getSortDirection(), paginationPage.getSortBy());
        Pageable pageable = PageRequest.of(
                paginationPage.getPageNumber(),
                paginationPage.getPageSize(),
                sort);
        List<CustomerDTO> customerList = customerRepository.findAll(pageable)
                                        .stream()
                                        .map(customerMapper::CustomerToCustomerDTO)
                                        .collect(Collectors.toList());
        return new PageImpl<>(customerList);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        return customerMapper.CustomerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        customer.setId(id);
        return customerMapper.CustomerToCustomerDTO(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
