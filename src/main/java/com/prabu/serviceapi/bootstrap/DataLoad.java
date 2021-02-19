package com.prabu.serviceapi.bootstrap;

import com.prabu.serviceapi.appuser.AppUser;
import com.prabu.serviceapi.appuser.AppUserRole;
import com.prabu.serviceapi.appuser.AppUserService;
import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.CustomerRepository;
import com.prabu.serviceapi.vehicle.Vehicle;
import com.prabu.serviceapi.vehicle.VehicleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoad implements ApplicationListener<ContextRefreshedEvent> {

    private final AppUserService appUserService;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    public DataLoad(AppUserService appUserService, CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.appUserService = appUserService;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        signUpUser();
        addCustomers();
        addVehicles();
    }


    private void addCustomers() {
        Customer customer = new Customer();
        customer.setFirstName("firstName1");
        customer.setLastName("lastName");
        customer.setEmail("email@email.com");
        customer.setMobileNumber("01155778");
        customer.setAddress("address");

        Customer customer2 = new Customer();
        customer2.setFirstName("firstName2");
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

    private void addVehicles() {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber("123");
        vehicle.setYear(2000);
        vehicle.setModel("modle");
        vehicle.setChassisNumber("chassis");
        vehicle.setEngineNumber("engine");
        vehicle.setCurrentMilage(100.50);

        Customer customer = customerRepository.getByFirstName("firstName1");
        vehicle.setCustomer(customer);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleNumber("123");
        vehicle1.setYear(2000);
        vehicle1.setModel("model");
        vehicle1.setChassisNumber("chassis");
        vehicle1.setEngineNumber("engine");
        vehicle1.setCurrentMilage(100.50);

        Customer customer1 = customerRepository.getByFirstName("firstName2");
        vehicle1.setCustomer(customer1);

        vehicleRepository.save(vehicle);
        vehicleRepository.save(vehicle1);


    }
}
