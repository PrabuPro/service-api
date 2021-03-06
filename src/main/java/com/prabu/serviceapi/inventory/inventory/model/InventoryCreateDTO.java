package com.prabu.serviceapi.inventory.inventory.model;

import com.prabu.serviceapi.inventory.category.Category;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class InventoryCreateDTO {

    private Long id;
    @NotNull
    private String partName;
    @NotNull
    private String partNumber;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal purchasedPrice;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal salePrice;
    private Boolean warrentyStatus;
    private Integer warrantyDuration;
    private Long categoryId;

}
