package com.prabu.serviceapi.customer;

import com.prabu.serviceapi.customer.model.CustomerDTO;
import com.prabu.serviceapi.customer.model.CustomerListDTO;
import com.prabu.serviceapi.customer.model.CustomerPage;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping( path = CustomerController.API_V_1_CUSTOMER)
public class CustomerController {

    public static final String API_V_1_CUSTOMER = "/api/v1/customer";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public ResponseEntity<CustomerListDTO> getCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getCustomers()),HttpStatus.OK);
    }

    @GetMapping("/grid")
    public ResponseEntity<Page<CustomerDTO>> getCustomerGrid(CustomerPage customerPage){
        return new ResponseEntity<>(customerService.getCustomerGrid(customerPage), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.saveCustomer(customerDTO),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
