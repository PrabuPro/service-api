package com.prabu.serviceapi.technician.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TechnicianListDTO {

    List<TechnicianDTO> technicians;

}
