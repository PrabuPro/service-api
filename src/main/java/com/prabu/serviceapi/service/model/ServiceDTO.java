package com.prabu.serviceapi.service.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ServiceDTO {

    private Long id;
    @NotNull
    private String ServiceName;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal ServiceCharge;
}
