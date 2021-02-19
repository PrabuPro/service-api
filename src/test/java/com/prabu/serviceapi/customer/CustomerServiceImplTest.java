package com.prabu.serviceapi.customer;

import com.prabu.serviceapi.customer.mapper.CustomerMapper;
import com.prabu.serviceapi.customer.model.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;



class CustomerServiceImplTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "john";
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);

    }

    @Test
    void getCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customers1 = customerService.getCustomers();

        assertEquals(2,customers1.size());

    }

    @Test
    void saveCustomer(){
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);

        Customer customerReturn = new Customer();
        customerReturn.setId(ID);
        customerReturn.setFirstName(FIRST_NAME);

        CustomerDTO customerReturn1 = new CustomerDTO();
        customerReturn1.setFirstName(FIRST_NAME);

        when(customerRepository.save(customer)).thenReturn(customerReturn);

        CustomerDTO savedCustomer = customerService.saveCustomer(customerReturn1);

        assertEquals(savedCustomer.getId(), ID);
        assertEquals(savedCustomer.getFirstName(), FIRST_NAME);

    }

    @Test
    void updateCustomer(){
        Customer customerReturn = new Customer();
        customerReturn.setId(ID);
        customerReturn.setFirstName(FIRST_NAME);

        CustomerDTO customerReturn1 = new CustomerDTO();
        customerReturn1.setFirstName(FIRST_NAME);

        when(customerRepository.save(any(Customer.class))).thenReturn(customerReturn);

        CustomerDTO savedCustomer = customerService.updateCustomer(ID,customerReturn1);

        assertEquals(savedCustomer.getId(), ID);
        assertEquals(savedCustomer.getFirstName(), FIRST_NAME);

    }

    @Test
    void deleteCustomer(){
        customerService.deleteCustomer(anyLong());
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}