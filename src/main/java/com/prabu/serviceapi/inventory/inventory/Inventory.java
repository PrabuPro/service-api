package com.prabu.serviceapi.inventory.inventory;

import com.prabu.serviceapi.inventory.category.Category;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String partName;
    @NotNull
    private String partNumber;
    @NotNull
    private BigDecimal purchasedPrice;
    @NotNull
    private BigDecimal salePrice;
    private Boolean warrentyStatus;
    private Integer warrantyDuration;

    @ManyToOne
    private Category category;


}
