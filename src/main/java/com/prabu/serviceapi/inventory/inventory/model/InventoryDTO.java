package com.prabu.serviceapi.inventory.inventory.model;

import com.prabu.serviceapi.inventory.category.Category;
import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class InventoryDTO {

    private Long id;
    private String partName;
    private String partNumber;
    private BigDecimal purchasedPrice;
    private BigDecimal salePrice;
    private Boolean warrentyStatus;
    private Integer warrantyDuration;
    private CategoryDTO category;

}
