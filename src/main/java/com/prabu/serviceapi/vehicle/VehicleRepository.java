package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByCustomer(Customer customer);
}
