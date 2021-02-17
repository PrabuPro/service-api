package com.prabu.serviceapi.customer;

import com.prabu.serviceapi.customer.model.CustomerDTO;
//import com.prabu.serviceapi.exception.RestResponceEntityExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.prabu.serviceapi.customer.AbstractRestController.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    public static final String FIRST_NAME = "firstName";
    public static final long ID = 1L;
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
//                .setControllerAdvice(new RestResponceEntityExceptionHandler())
                .build();
    }

    @Test
    void getCustomers() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("firstName");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("firstName2");

        List<CustomerDTO> customerDTOS = Arrays.asList(customer, customer2);

        when(customerService.getCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get(CustomerController.API_V_1_CUSTOMER + "/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void saveCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(FIRST_NAME);

        CustomerDTO customerReturn = new CustomerDTO();
        customerReturn.setId(ID);
        customerReturn.setFirstName(FIRST_NAME);

        when(customerService.saveCustomer(customer)).thenReturn(customerReturn);

        mockMvc.perform(post(CustomerController.API_V_1_CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.id", equalTo(1)));


    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(FIRST_NAME);

        CustomerDTO customerReturn = new CustomerDTO();
        customerReturn.setId(ID);
        customerReturn.setFirstName(FIRST_NAME);

        when(customerService.updateCustomer(ID,customer)).thenReturn(customerReturn);

        mockMvc.perform(put(CustomerController.API_V_1_CUSTOMER + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.id", equalTo(1)));


    }

    @Test
    void customerDelete() throws Exception {
        mockMvc.perform(delete(CustomerController.API_V_1_CUSTOMER + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}