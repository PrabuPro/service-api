package com.prabu.serviceapi.bootstrap;

import com.prabu.serviceapi.appuser.AppUser;
import com.prabu.serviceapi.appuser.AppUserRole;
import com.prabu.serviceapi.appuser.AppUserService;
import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.CustomerRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoad implements ApplicationListener<ContextRefreshedEvent> {

    private final AppUserService appUserService;
    private final CustomerRepository customerRepository;

    public DataLoad(AppUserService appUserService, CustomerRepository customerRepository) {
        this.appUserService = appUserService;
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        signUpUser();
        addCustomers();
    }

    private void addCustomers() {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email@email.com");
        customer.setMobileNumber("01155778");
        customer.setAddress("address");

        Customer customer2 = new Customer();
        customer2.setFirstName("firstName");
        customer2.setLastName("lastName");
        customer2.setEmail("email@email.com");
        customer2.setMobileNumber("01155778");
        customer2.setAddress("address");

        customerRepository.save(customer);
        customerRepository.save(customer2);
    }

    private void signUpUser() {
        AppUser user = new AppUser();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email@email.com");
        user.setPassword("password");
        user.setAppUserRole(AppUserRole.USER);

        appUserService.signUpUser(user);
    }
}
