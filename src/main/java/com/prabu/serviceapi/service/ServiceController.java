package com.prabu.serviceapi.service;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.service.model.ServiceDTO;
import com.prabu.serviceapi.service.model.ServiceListDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ServiceController.API_V_1_SERVICE)
public class ServiceController {
    public static final String API_V_1_SERVICE = "/api/v1/service";

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/list")
    public ResponseEntity<ServiceListDTO> getServiceList(){
        return new ResponseEntity<>(new ServiceListDTO(serviceService.getServiceList()), HttpStatus.OK);
    }

    @GetMapping("/grid")
    public ResponseEntity<Page<ServiceDTO>> getServiceGrid(PaginationPage paginationPage){
        return new ResponseEntity<>(serviceService.getServiceGrid(paginationPage),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ServiceDTO> saveService(@RequestBody ServiceDTO serviceDTO){
        return new ResponseEntity<>(serviceService.saveService(serviceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO){
        return new ResponseEntity<>(serviceService.updateService(id, serviceDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id){
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
