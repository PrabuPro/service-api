package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.inventory.Inventory;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Inventory> inventories;

}
