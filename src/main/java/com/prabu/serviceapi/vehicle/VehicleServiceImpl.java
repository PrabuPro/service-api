package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.CustomerRepository;
import com.prabu.serviceapi.exception.ResourceNotFoundException;
import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.vehicle.mapper.VehicleMapper;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleRequestDTO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, CustomerRepository customerRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VehicleDTO> getVehicleGrid(PaginationPage paginationPage) {
        Sort sort = Sort.by(paginationPage.getSortDirection(),paginationPage.getSortBy());
        Pageable pageable = PageRequest.of(
                paginationPage.getPageNumber(),
                paginationPage.getPageSize(),
                sort);
        List<VehicleDTO> vehicleDTOList = vehicleRepository.findAll(pageable)
                .stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(vehicleDTOList);
    }

    @Override
    public VehicleDTO saveVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Customer customer = customerRepository.findById(vehicleRequestDTO.getCustomer()).orElseThrow(ResourceNotFoundException::new);
        Vehicle vehicle = vehicleMapper.vehicleRequestToVehicle(vehicleRequestDTO, customer);
        return vehicleMapper.vehicleToVehicleDTO(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO) {
        Customer customer = customerRepository.findById(vehicleRequestDTO.getCustomer()).orElseThrow(ResourceNotFoundException::new);
        Vehicle vehicle = vehicleMapper.vehicleRequestToVehicle(vehicleRequestDTO, customer);
        vehicle.setId(id);
        return vehicleMapper.vehicleToVehicleDTO(vehicleRepository.save(vehicle));
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> findAllByCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));

        return vehicleRepository.findAllByCustomer(customer)
                .stream()
                .map(vehicleMapper::vehicleToVehicleDTO)
                .collect(Collectors.toList());
    }
}
