package com.prabu.serviceapi.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ServiceListDTO {

    private List<ServiceDTO> services;

}
